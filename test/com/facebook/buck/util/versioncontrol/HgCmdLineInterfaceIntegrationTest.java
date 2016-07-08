import static org.hamcrest.Matchers.hasSize;
import com.google.common.collect.ImmutableSet;
import org.hamcrest.Matchers;
    assertThat(repoTwoCmdLine.changedFiles("."), hasSize(0));
    assertThat(repoThreeCmdLine.changedFiles("."), hasSize(Matchers.greaterThan(0)));
  @Test
  public void testChangedFilesFromHead()
      throws VersionControlCommandFailedException, InterruptedException {
    ImmutableSet<String> changedFiles = repoThreeCmdLine.changedFiles(".");
    assertThat(changedFiles, Matchers.contains("? local_change"));
  }

  @Test
  public void testChangedFilesFromCommonAncestor()
      throws VersionControlCommandFailedException, InterruptedException {
    ImmutableSet<String> changedFiles = repoThreeCmdLine.changedFiles("ancestor(., master3)");
    assertThat(changedFiles,
        Matchers.containsInAnyOrder("A change3", "A change3-2", "? local_change"));
  }

  @Test
  public void testDiffBetweenRevisions()
      throws VersionControlCommandFailedException, InterruptedException {
    assertEquals(
        "diff --git a/change2 b/change2new file mode 100644diff --git a/file3 b/file3deleted " +
            "file mode 100644",
        repoThreeCmdLine.diffBetweenRevisions("adf7a0", "2911b3"));
  }

  @Test
  public void testDiffBetweenTheSameRevision()
      throws VersionControlCommandFailedException, InterruptedException {
    assertEquals("", repoThreeCmdLine.diffBetweenRevisions("adf7a0", "adf7a0"));
  }

  @Test
  public void testDiffBetweenDiffsOfDifferentBranches()
      throws VersionControlCommandFailedException, InterruptedException {
    assertEquals(
        "diff --git a/change2 b/change2deleted file mode 100644diff --git a/change3 b/change3new " +
            "file mode 100644diff --git a/change3-2 b/change3-2new file mode 100644diff " +
            "--git a/file3 b/file3new file mode 100644",
        repoThreeCmdLine.diffBetweenRevisions("2911b3", "dee670"));
  }

  @Test
  public void testAllBookmarks()
      throws VersionControlCommandFailedException, InterruptedException {
    try {
      assertEquals(
          "{branch_from_master2=3:2911b3cab6b2, branch_from_master3=5:dee6702e3d5e, " +
              "master1=0:b870f77a2738, master2=1:b1fd7e5896af, master3=2:adf7a03ed6f1}",
          repoThreeCmdLine.allBookmarks().toString());
    } catch (VersionControlCommandFailedException e) {
      if (!e.getMessage().contains("option --all not recognized")) {
        throw new VersionControlCommandFailedException(e.getCause());
      }
    }
  }

  @Test
  public void testUntrackedFiles()
      throws VersionControlCommandFailedException, InterruptedException {
    assertEquals(ImmutableSet.of("? local_change"), repoThreeCmdLine.untrackedFiles());
  }

            new VersionControlBuckConfig(FakeBuckConfig.builder().build().getRawConfig()),
            new VersionControlBuckConfig(FakeBuckConfig.builder().build().getRawConfig()),