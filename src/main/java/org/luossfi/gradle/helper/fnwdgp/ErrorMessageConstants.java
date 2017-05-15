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
 * This constants class is used to define the error message constants.
 *
 * @author Steff Lukas
 * @since 1.0
 */
public final class ErrorMessageConstants
{

  /**
   * The value of property "definitionFiles" must not be null, please set it to
   * a list of valid files!
   */
  public static final String DEF_FILES_IS_NULL = "fnwdgp.error.01";

  /**
   * Private constructor, this is a constants only class
   */
  private ErrorMessageConstants()
  {
    super();
  }

}
