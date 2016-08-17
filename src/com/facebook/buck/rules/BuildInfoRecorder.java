/*
 * Copyright 2013-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.rules;

import com.facebook.buck.artifact_cache.ArtifactCache;
import com.facebook.buck.artifact_cache.ArtifactInfo;
import com.facebook.buck.artifact_cache.CacheResult;
import com.facebook.buck.event.ArtifactCompressionEvent;
import com.facebook.buck.event.BuckEventBus;
import com.facebook.buck.event.ConsoleEvent;
import com.facebook.buck.io.BorrowablePath;
import com.facebook.buck.io.LazyPath;
import com.facebook.buck.io.MoreFiles;
import com.facebook.buck.io.MorePaths;
import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.log.Logger;
import com.facebook.buck.model.BuildId;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.timing.Clock;
import com.facebook.buck.util.cache.FileHashCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

/**
 * Utility for recording the paths to the output files generated by a build rule, as well as any
 * metadata about those output files. This data will be packaged up into an artifact that will be
 * stored in the cache. The metadata will also be written to disk so it can be read on a subsequent
 * build by an {@link OnDiskBuildInfo}.
 */
public class BuildInfoRecorder {

  private static final Logger LOG = Logger.get(BuildRuleResolver.class);

  @VisibleForTesting
  static final String ABSOLUTE_PATH_ERROR_FORMAT =
      "Error! '%s' is trying to record artifacts with absolute path: '%s'.";

  private static final String BUCK_CACHE_DATA_ENV_VAR = "BUCK_CACHE_DATA";

  private final BuildTarget buildTarget;
  private final Path pathToMetadataDirectory;
  private final ProjectFilesystem projectFilesystem;
  private final Clock clock;
  private final BuildId buildId;
  private final ObjectMapper objectMapper;
  private final ImmutableMap<String, String> artifactExtraData;
  private final Map<String, String> metadataToWrite;
  private final Map<String, String> buildMetadata;
  private final AtomicBoolean warnedUserOfCacheStoreFailure;

  /**
   * Every value in this set is a path relative to the project root.
   */
  private final Set<Path> pathsToOutputs;

  BuildInfoRecorder(BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      Clock clock,
      BuildId buildId,
      ObjectMapper objectMapper,
      ImmutableMap<String, String> environment) {
    this.buildTarget = buildTarget;
    this.pathToMetadataDirectory =
        BuildInfo.getPathToMetadataDirectory(buildTarget, projectFilesystem);
    this.projectFilesystem = projectFilesystem;
    this.clock = clock;
    this.buildId = buildId;
    this.objectMapper = objectMapper;

    this.artifactExtraData =
        ImmutableMap.<String, String>builder()
            .put(
                "artifact_data",
                Optional.fromNullable(environment.get(BUCK_CACHE_DATA_ENV_VAR)).or("null"))
            .build();

    this.metadataToWrite = Maps.newLinkedHashMap();
    this.buildMetadata = Maps.newLinkedHashMap();
    this.pathsToOutputs = Sets.newHashSet();
    this.warnedUserOfCacheStoreFailure = new AtomicBoolean(false);
  }

  private String toJson(Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String formatAdditionalArtifactInfo(Map<String, String> entries) {
    StringBuilder builder = new StringBuilder();
    for (Map.Entry<String, String> entry : entries.entrySet()) {
      builder.append(entry.getKey());
      builder.append('=');
      builder.append(entry.getValue());
      builder.append('\n');
    }
    return builder.toString();
  }

  private ImmutableMap<String, String> getBuildMetadata() throws IOException {
    return ImmutableMap.<String, String>builder()
        .put(
            BuildInfo.METADATA_KEY_FOR_ADDITIONAL_INFO,
            formatAdditionalArtifactInfo(
                ImmutableMap.<String, String>builder()
                    .put("build_id", buildId.toString())
                    .put(
                        "timestamp",
                        String.valueOf(
                            TimeUnit.MILLISECONDS.toSeconds(clock.currentTimeMillis())))
                    .putAll(artifactExtraData)
                    .build()))
        .putAll(buildMetadata)
        .build();
  }

  /**
   * Writes the metadata currently stored in memory to the directory returned by
   * {@link BuildInfo#getPathToMetadataDirectory(BuildTarget, ProjectFilesystem)}.
   */
  public void writeMetadataToDisk(boolean clearExistingMetadata) throws IOException {
    if (clearExistingMetadata) {
      projectFilesystem.deleteRecursivelyIfExists(pathToMetadataDirectory);
    }
    projectFilesystem.mkdirs(pathToMetadataDirectory);

    for (Map.Entry<String, String> entry :
         Iterables.concat(metadataToWrite.entrySet(), getBuildMetadata().entrySet())) {
      projectFilesystem.writeContentsToPath(
          entry.getValue(),
          pathToMetadataDirectory.resolve(entry.getKey()));
    }
  }

  /**
   * Used by the build engine to record metadata describing the build (e.g. rule key, build UUID).
   */
  public BuildInfoRecorder addBuildMetadata(String key, String value) {
    buildMetadata.put(key, value);
    return this;
  }

  public BuildInfoRecorder addBuildMetadata(String key, ImmutableList<String> value) {
    return addBuildMetadata(key, toJson(value));
  }

  public BuildInfoRecorder addBuildMetadata(String key, ImmutableMap<String, String> value) {
    return addBuildMetadata(key, toJson(value));
  }

  /**
   * This key/value pair is stored in memory until {@link #writeMetadataToDisk(boolean)} is invoked.
   */
  public void addMetadata(String key, String value) {
    metadataToWrite.put(key, value);
  }

  public void addMetadata(String key, ImmutableList<String> value) {
    addMetadata(key, toJson(value));
  }

  private ImmutableSortedSet<Path> getRecordedMetadataFiles() {
    return FluentIterable.from(metadataToWrite.keySet())
        .transform(MorePaths.TO_PATH)
        .transform(
            new Function<Path, Path>() {
              @Override
              public Path apply(Path input) {
                return pathToMetadataDirectory.resolve(input);
              }
            })
        .toSortedSet(Ordering.natural());
  }

  private ImmutableSortedSet<Path> getRecordedOutputDirsAndFiles() throws IOException {
    final ImmutableSortedSet.Builder<Path> paths = ImmutableSortedSet.naturalOrder();

    // Add files from output directories.
    for (final Path output : pathsToOutputs) {
      projectFilesystem.walkRelativeFileTree(
          output,
          new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(
                Path file,
                BasicFileAttributes attrs)
                throws IOException {
              paths.add(file);
              return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(
                Path dir,
                BasicFileAttributes attrs)
                throws IOException {
              paths.add(dir);
              return FileVisitResult.CONTINUE;
            }
          });
    }

    return paths.build();
  }

  private ImmutableSortedSet<Path> getRecordedDirsAndFiles() throws IOException {
    return ImmutableSortedSet.<Path>naturalOrder()
        .addAll(getRecordedMetadataFiles())
        .addAll(getRecordedOutputDirsAndFiles())
        .build();
  }

  /**
   * @return the outputs paths as recorded by the rule.
   */
  public ImmutableSortedSet<Path> getOutputPaths() {
    return ImmutableSortedSet.copyOf(pathsToOutputs);
  }

  public ImmutableSortedSet<Path> getRecordedPaths() throws IOException {
    return ImmutableSortedSet.<Path>naturalOrder()
        .addAll(getRecordedMetadataFiles())
        .addAll(pathsToOutputs)
        .build();
  }

  public HashCode getOutputHash(FileHashCache fileHashCache) throws IOException {
    Hasher hasher = Hashing.md5().newHasher();
    for (Path path : getRecordedPaths()) {
      hasher.putBytes(fileHashCache.get(projectFilesystem.resolve(path)).asBytes());
    }
    return hasher.hash();
  }

  public long getOutputSize() throws IOException {
    long size = 0;
    for (Path path : getRecordedDirsAndFiles()) {
      if (projectFilesystem.isFile(path)) {
        size += projectFilesystem.getFileSize(path);
      }
    }
    return size;
  }

  /**
   * Creates a zip file of the metadata and recorded artifacts and stores it in the artifact cache.
   */
  public void performUploadToArtifactCache(
      final ImmutableSet<RuleKey> ruleKeys,
      ArtifactCache artifactCache,
      final BuckEventBus eventBus)
      throws InterruptedException {

    // Skip all of this if caching is disabled. Although artifactCache.store() will be a noop,
    // building up the zip is wasted I/O.
    if (!artifactCache.isStoreSupported()) {
      return;
    }

    ArtifactCompressionEvent.Started started = ArtifactCompressionEvent.started(
        ArtifactCompressionEvent.Operation.COMPRESS,
        ruleKeys);
    eventBus.post(started);

    final Path zip;
    ImmutableSet<Path> pathsToIncludeInZip = ImmutableSet.of();
    ImmutableMap<String, String> buildMetadata;
    try {
      pathsToIncludeInZip = getRecordedDirsAndFiles();
      zip = Files.createTempFile(
          "buck_artifact_" + MoreFiles.sanitize(buildTarget.getShortName()),
          ".zip");
      buildMetadata = getBuildMetadata();
      projectFilesystem.createZip(pathsToIncludeInZip, zip, ImmutableMap.<Path, String>of());
    } catch (IOException e) {
      eventBus.post(ConsoleEvent.info("Failed to create zip for %s containing:\n%s",
          buildTarget,
          Joiner.on('\n').join(ImmutableSortedSet.copyOf(pathsToIncludeInZip))));
      e.printStackTrace();
      return;
    } finally {
      eventBus.post(ArtifactCompressionEvent.finished(started));
    }

    // Store the artifact, including any additional metadata.
    ListenableFuture<Void> storeFuture = artifactCache.store(
        ArtifactInfo.builder().setRuleKeys(ruleKeys).setMetadata(buildMetadata).build(),
        BorrowablePath.notBorrowablePath(zip));
    Futures.addCallback(
        storeFuture,
        new FutureCallback<Void>() {
          @Override
          public void onSuccess(Void result) {
            onCompletion();
          }

          @Override
          public void onFailure(Throwable t) {
            onCompletion();
            LOG.info(t, "Failed storing RuleKeys %s to the cache.", ruleKeys);
            if (warnedUserOfCacheStoreFailure.compareAndSet(false, true)) {
              eventBus.post(
                  ConsoleEvent.severe("Failed storing an artifact to the cache," +
                      "see log for details."));
            }
          }

          private void onCompletion() {
            try {
              Files.deleteIfExists(zip);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        });
  }

  /**
   * Fetches the artifact associated with the {@link #buildTarget} for this class and writes it to
   * the specified {@code outputFile}.
   */
  public CacheResult fetchArtifactForBuildable(
      RuleKey ruleKey,
      LazyPath outputFile,
      ArtifactCache artifactCache)
      throws InterruptedException {
    try {
      return artifactCache.fetch(ruleKey, outputFile);
    } catch (Throwable t) {
      LOG.error(t, "Buck internal error when downloading from the cache, will build locally.");
      return CacheResult.error("unknown", t.getMessage());
    }
  }

  /**
   * @param pathToArtifact Relative path to the project root.
   */
  public void recordArtifact(Path pathToArtifact) {
    Preconditions.checkArgument(
        !pathToArtifact.isAbsolute(),
        ABSOLUTE_PATH_ERROR_FORMAT,
        buildTarget,
        pathToArtifact);
    pathsToOutputs.add(pathToArtifact);
  }

  @Nullable
  @VisibleForTesting
  String getMetadataFor(String key) {
    return metadataToWrite.get(key);
  }
}
