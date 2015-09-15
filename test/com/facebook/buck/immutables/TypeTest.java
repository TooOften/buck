/*
 * Copyright 2015-present Facebook, Inc.
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

package com.facebook.buck.immutables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.lang.reflect.Modifier;

/**
 * Unit test for generated {@link Type} class.
 */
public class TypeTest {
  @Test
  public void testBuilder() {
    Type t = Type.builder()
        .setName("Jenny")
        .addPhoneNumbers(8675309L)
        .build();

    assertEquals("Jenny", t.getName());
    assertEquals(ImmutableList.of(8675309L), t.getPhoneNumbers());
    assertFalse(t.getDescription().isPresent());
  }

  @Test
  public void generatedClassIsPublic() {
    assertEquals(Modifier.PUBLIC, Type.class.getModifiers() & Modifier.PUBLIC);
  }

  @Test
  public void generatedClassIsFinal() {
    assertEquals(Modifier.FINAL, Type.class.getModifiers() & Modifier.FINAL);
  }

  @Test
  public void generatedClassImplementsAbstractType() {
    assertTrue(AbstractType.class.isAssignableFrom(Type.class));
  }
}