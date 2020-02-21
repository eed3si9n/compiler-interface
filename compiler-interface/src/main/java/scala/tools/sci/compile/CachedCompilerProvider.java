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

import scala.tools.sci.Logger;
import scala.tools.sci.Reporter;

/** Represent a provider that creates cached Scala compilers from a Scala instance. */
public interface CachedCompilerProvider {
  /** Return the Scala instance used to provide cached compilers. */
  ScalaInstance scalaInstance();

  /** Return a new cached compiler from the usual compiler input. */
  CachedCompiler newCachedCompiler(
      String[] arguments, Output output, Logger log, Reporter reporter);
}
