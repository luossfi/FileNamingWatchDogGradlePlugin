/*
 * FileNamingWatchDogGradlePlugin, a plugin for the Gradle build system which
 * checks Java packages and source file names for compliance to naming
 * conventions.
 *
 * Copyright (C) 2016++ Steff Lukas <steff.lukas@luossfi.org>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.luossfi.gradle.helper.fnwdgp;

/**
 * This constants class is used to define the log message constants.
 *
 * @author Steff Lukas
 * @since 1.0
 */
public final class LogMessageConstants
{

  /** Scanning source directory "{0}". */
  public static final String SCANNING_SRC_DIR     = "fnwdgp.log.01";

  /** Found source directory: {0} */
  public static final String FOUND_SRC_DIR        = "fnwdgp.log.02";

  /**
   * No source directories defined, please define them by setting property
   * "fileNamingWatchDog.sourceDirs".
   */
  public static final String NO_SRC_DIRS          = "fnwdgp.log.03";

  /** The package "{0}" violates the naming conventions. */
  public static final String NONCOMPLIANT_PACKAGE = "fnwdgp.log.04";

  /** The file "{0}" in package "{1}" violates the naming conventions. */
  public static final String NONCOMPLIANT_FILE    = "fnwdgp.log.05";

  /**
   * Private constructor, this is a constants only class.
   */
  private LogMessageConstants()
  {
    super();
  }

}
