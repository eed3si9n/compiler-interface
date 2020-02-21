/*
 * Scala compiler interface
 *
 * Copyright Lightbend, Inc. and Mark Harrah
 *
 * Licensed under Apache License 2.0
 * (http://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package scala.tools.sci.compile;

import java.io.File;

/**
 * A Scala instance encapsulates all the information that is bound to a concrete Scala version, like
 * the ClassLoader or all the JARs required for Scala compilation: library jar, compiler jar and
 * others.
 *
 * <p>Both a `ClassLoader` and the jars are required because the compiler's boot classpath requires
 * the location of the library and compiler jar on the classpath to compile any Scala program and
 * macros.
 *
 * <p><b>NOTE</b>: A "jar" can actually be any valid classpath entry.
 */
public interface ScalaInstance {
  /**
   * Scala version for this {@link ScalaInstance}.
   *
   * <p>It need not to be unique and can be dynamic (e.g. 2.10.0-SNAPSHOT).
   */
  String version();

  /**
   * A class loader providing access to the classes and resources in all the jars of this Scala
   * instance.
   */
  ClassLoader loader();

  /**
   * A class loader providing access to the classes and resources in the library jars of this Scala
   * instance.
   */
  ClassLoader loaderLibraryOnly();

  /** Classpath entries that stores the Scala library classes. */
  File[] libraryJars();

  /** @deprecated Use `libraryJars` instead (since 1.3.0). */
  @Deprecated
  default File libraryJar() {
    return libraryJars()[0];
  }

  /** Classpath entry that stores the Scala compiler classes. */
  File compilerJar();

  /** All the jars except `libraryJars` and `compilerJar`. */
  File[] otherJars();

  /** Classpath entries for the `loader`. */
  File[] allJars();

  /**
   * The unique identifier for this Scala instance, usually obtained (but not necessarily) from
   * `compiler.properties` files.
   */
  String actualVersion();
}
