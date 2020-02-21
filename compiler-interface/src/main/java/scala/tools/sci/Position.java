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

package scala.tools.sci;

import java.io.File;
import java.util.Optional;

public interface Position {
  Optional<Integer> line();

  String lineContent();

  Optional<Integer> offset();

  // pointer to the column position of the error/warning
  Optional<Integer> pointer();

  Optional<String> pointerSpace();

  Optional<String> sourcePath();

  Optional<File> sourceFile();

  // Default values to avoid breaking binary compatibility
  default Optional<Integer> startOffset() {
    return Optional.empty();
  }

  default Optional<Integer> endOffset() {
    return Optional.empty();
  }

  default Optional<Integer> startLine() {
    return Optional.empty();
  }

  default Optional<Integer> startColumn() {
    return Optional.empty();
  }

  default Optional<Integer> endLine() {
    return Optional.empty();
  }

  default Optional<Integer> endColumn() {
    return Optional.empty();
  }
}
