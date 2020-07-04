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

import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a mapping of several outputs depending on the source directory.
 *
 * <p>This option is used only by the Scala compiler.
 */
public interface MultipleOutput extends Output {
  /**
   * Return an array of the existent output groups.
   *
   * <p>Incremental compilation manages the class files in these directories, so don't play with
   * them out of the Zinc API. Zinc already takes care of deleting classes before every compilation
   * run.
   */
  public OutputGroup[] getOutputGroups();

  @Override
  public default Optional<Path> getSingleOutput() {
    return Optional.empty();
  }

  @Override
  public default Optional<OutputGroup[]> getMultipleOutput() {
    return Optional.of(getOutputGroups());
  }
}
