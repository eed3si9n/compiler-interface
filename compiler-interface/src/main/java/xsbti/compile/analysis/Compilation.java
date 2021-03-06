/*
 * Compiler Interface
 *
 * Copyright 2011 - 2019, Lightbend, Inc.
 * Copyright 2008 - 2010, Mark Harrah
 *
 * Licensed under Apache License 2.0
 * (http://www.apache.org/licenses/LICENSE-2.0).
 */

package xsbti.compile.analysis;

import xsbti.compile.Output;

import java.io.Serializable;

/**
 * Defines Zinc's compilation information.
 */
public interface Compilation extends Serializable {

    /**
     * Returns the milliseconds since the last epoch in which the compilation started.
     *
     * @return Compilation start time in milliseconds.
     */
    public long getStartTime();

    /**
     * Returns the instance of {@link Output} used by the Zinc compiler that tells the
     * user which directories are being used to store class files.
     *
     * @return An instance of {@link Output}.
     */
    public Output getOutput();
}
