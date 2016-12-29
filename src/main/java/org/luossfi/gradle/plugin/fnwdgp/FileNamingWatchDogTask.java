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
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;
import org.luossfi.exception.fnwd.WatchDogException;
import org.luossfi.gradle.helper.fnwdgp.ErrorMessageConstants;
import org.luossfi.gradle.helper.fnwdgp.LogMessageConstants;
import org.luossfi.gradle.helper.fnwdgp.MessageTranslator;
import org.luossfi.watchdog.fnwd.FileNamingWatchDog;

/**
 * The Class FileNamingWatchDogTask contains the tasks logic. This is some
 * configuration checking and a wrapper for the FileNamingWatchDog. It also logs
 * all findings in human readable form as errors.
 *
 * @author Steff Lukas
 * @since 1.0
 */
public class FileNamingWatchDogTask extends DefaultTask
{
  Logger log = Logging.getLogger( FileNamingWatchDogTask.class );

  /**
   * Checks the task's configuration and runs the File Naming Watch Dog.
   *
   * @throws InvalidUserDataException if the definition file is null
   * @throws TaskExecutionException if the watch dog execution fails
   * @throws GradleException if the watch dog found something and is configured
   *           for failure
   */
  @TaskAction
  public void runWatchDog()
  {
    Project project = getProject();

    FileNamingWatchDogExtension watchDogExtension = project.getExtensions().getByType( FileNamingWatchDogExtension.class );

    File definitionFile = watchDogExtension.getDefinitionFile();

    if ( definitionFile == null )
    {
      throw new InvalidUserDataException( MessageTranslator.translateError( ErrorMessageConstants.DEF_FILE_IS_NULL ) );
    }

    Set<File> sourceDirs = watchDogExtension.getSourceDirs();
    if ( sourceDirs == null || sourceDirs.isEmpty() )
    {
      sourceDirs = getMainSrcSetDirs();
    }

    if ( log.isDebugEnabled() )
    {
      sourceDirs.forEach( file -> log.debug( MessageTranslator.translateLog( LogMessageConstants.FOUND_SRC_DIR, file.getAbsolutePath() ) ) );
    }

    Map<String, String> placeholderValues = watchDogExtension.getPlaceholderValues();
    if ( placeholderValues == null )
    {
      placeholderValues = Collections.emptyMap();
    }

    if ( !sourceDirs.isEmpty() )
    {
      boolean failed = runWatchDog( definitionFile.toPath(), placeholderValues, sourceDirs );

      /*
       * Fail the build if the watch dog found something and is configured to
       * fail
       */
      if ( failed && watchDogExtension.isFailBuild() )
      {
        throw new GradleException();
      }
    }
    else
    {
      log.warn( MessageTranslator.translateLog( LogMessageConstants.NO_SRC_DIRS ) );
    }
  }

  /**
   * Configure and run the watch dog.
   *
   * @param definitionFile the naming convention definition file
   * @param placeholderValues the placeholder values
   * @param sourceDirs the source directories to scan
   * @return false if the watch dog finds convention violations, true otherwise
   */
  private boolean runWatchDog( Path definitionFile, Map<String, String> placeholderValues, Set<File> sourceDirs )
  {
    FileNamingWatchDog watchDog = new FileNamingWatchDog( definitionFile, placeholderValues );

    Set<Map<String, Set<String>>> findings = new HashSet<>();

    try
    {
      for ( File currentDir : sourceDirs )
      {
        if ( currentDir.exists() )
        {
          Path path = currentDir.toPath().normalize();
          log.info( MessageTranslator.translateLog( LogMessageConstants.SCANNING_SRC_DIR, path ) );
          findings.add( watchDog.check( currentDir.toPath() ) );
        }
      }
    }
    catch ( WatchDogException e )
    {
      throw new TaskExecutionException( this, e );
    }

    boolean hasFindings = false;

    for ( Map<String, Set<String>> finding : findings )
    {
      // Keep this value to 'true' once something was found
      hasFindings = hasFindings || !finding.isEmpty();

      for ( Entry<String, Set<String>> current : finding.entrySet() )
      {
        String packageName = current.getKey();
        if ( current.getValue().isEmpty() )
        {
          log.error( MessageTranslator.translateLog( LogMessageConstants.NONCOMPLIANT_PACKAGE, packageName ) );
        }
        else
        {
          for ( String fileName : current.getValue() )
          {
            log.error( MessageTranslator.translateLog( LogMessageConstants.NONCOMPLIANT_FILE, fileName, packageName ) );
          }
        }
      }
    }

    return hasFindings;
  }

  /**
   * Gets the main-source set's source directories from the project's sourceSets
   * property if it exists.
   *
   * @return the main-source set source directories if possible, an empty set
   *         otherwise
   */
  private Set<File> getMainSrcSetDirs()
  {
    Set<File> sourceDirs = null;
    SourceSetContainer sourceSetContainer = (SourceSetContainer) getProject().property( "sourceSets" );
    if ( sourceSetContainer != null )
    {
      SortedMap<String, SourceSet> sourceSets = sourceSetContainer.getAsMap();
      if ( sourceSets.containsKey( "main" ) )
      {
        SourceSet sourceSet = sourceSets.get( "main" );
        sourceDirs = sourceSet.getAllSource().getSrcDirs();
        sourceDirs.removeAll( sourceSet.getResources().getSrcDirs() );
      }
    }

    if ( sourceDirs == null )
    {
      sourceDirs = Collections.emptySet();
    }

    return sourceDirs;
  }
}
