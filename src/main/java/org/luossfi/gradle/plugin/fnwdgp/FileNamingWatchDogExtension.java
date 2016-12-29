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

package org.luossfi.gradle.plugin.fnwdgp;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gradle.api.Project;

/**
 * The Class FileNamingWatchDogExtension is the POJO which defines the plugin's
 * configuration.
 *
 * @author Steff Lukas
 * @since 1.0
 */
public class FileNamingWatchDogExtension
{

  /** The naming convention definition file. */
  private File                definitionFile;

  /** The placeholder values. */
  private Map<String, String> placeholderValues = new HashMap<>();

  /** The source directories to scan. */
  private Set<File>           sourceDirs        = new HashSet<>();

  /** If a convention violation should fail the build. */
  private boolean             failBuild         = true;

  /**
   * Instantiates a new file naming watch dog extension.
   *
   * @param project the project
   */
  public FileNamingWatchDogExtension( Project project )
  {
    definitionFile = project.file( "./ConventionDefinition" );
  }

  /**
   * Gets the naming convention definition file.
   *
   * @return the naming convention definition file
   */
  public File getDefinitionFile()
  {
    return definitionFile;
  }

  /**
   * Sets a custom naming convention definition file.
   *
   * @param definitionFile the new definition file
   */
  public void setDefinitionFile( File definitionFile )
  {
    this.definitionFile = definitionFile;
  }

  /**
   * Gets the placeholder values to be used for the naming convention definition
   * file.
   *
   * @return the placeholder values
   */
  public Map<String, String> getPlaceholderValues()
  {
    return placeholderValues;
  }

  /**
   * Sets custom the placeholder values.
   *
   * @param placeholderValues the placeholder values
   */
  public void setPlaceholderValues( Map<String, String> placeholderValues )
  {
    this.placeholderValues = placeholderValues;
  }

  /**
   * Gets the source directories to scan with the watch dog.
   *
   * @return the source directories
   */
  public Set<File> getSourceDirs()
  {
    return sourceDirs;
  }

  /**
   * Sets custom source directories.
   *
   * @param sourceDirs the new source directories
   */
  public void setSourceDirs( Set<File> sourceDirs )
  {
    this.sourceDirs = sourceDirs;
  }

  /**
   * Checks if the build should fail if the watch dog finds a violation.
   *
   * @return true, if the build should fail, false otherwise
   */
  public boolean isFailBuild()
  {
    return failBuild;
  }

  /**
   * Sets if the build should fail if the watch dog finds a violation.
   *
   * @param failBuild true if the build should fail, false otherwise
   */
  public void setFailBuild( boolean failBuild )
  {
    this.failBuild = failBuild;
  }

}
