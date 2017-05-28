<!---
FileNamingWatchDogGradlePlugin, a plugin for the Gradle build system which
checks Java packages and source file names for compliance to naming
conventions.

Copyright (C) 2016++ Steff Lukas <steff.lukas@luossfi.org>

This program is free software: you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation, either version 3 of the License, or (at your option) any
later version.s

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.

You should have received a copy of the GNU Lesser General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
--->

# File Naming Watch Dog - Gradle Plugin

**File Naming Watch Dog - Gradle Plugin** is a plugin for the Gradle build system
which checks if Java project sources are compliant to naming conventions by using
the [File Naming Watch Dog Library](https://github.com/luossfi/FileNamingWatchDog).

## Adding The Plugin to A Build Script
### All Gradle versions:
```groovy
buildscript {
  repositories {
    maven {
      url 'https://plugins.gradle.org/m2/'
    }
  }
  dependencies {
    classpath 'org.luossfi.gradle:FileNamingWatchDogGradlePlugin:1.1'
  }
}

apply plugin: 'org.luossfi.file.naming.watch.dog'
```
### Since Gradle Version 2.1:
```
plugins {
  id 'org.luossfi.file.naming.watch.dog' version '1.1'
}
```

See also the [plugin's page](https://plugins.gradle.org/plugin/org.luossfi.file.naming.watch.dog)
on the Gradle Plugin Portal.

## Configuring The Plugin
```groovy
runWatchDog {
  definitionFiles = [ file( 'Path to Convention Definition File' ), file( 'Path to Another Convention Defintion File' ) ]
  placeholderValues = [ 'PLACEHOLDER': 'value' ]
  failBuild = true
  sourceDirs = sourceSets.main.java.srcDirs
}
```
* **definitionFiles (default: `[ file( './ConventionDefinition' ) ]`)**   
  Property of type `java.lang.List` containing the paths (`java.io.File`) to the convention definition files to be used for
  checking. See [File Naming Watch Dog Documentation](https://github.com/luossfi/FileNamingWatchDog/blob/master/doc/FileNamingWatchDog.md)
  for details on how to define the naming conventions.
* **definitionFile**   
  Convenience setter of type `java.io.File` for setting up a single convention definition file.
  Same as `definitionFiles = [ file( 'single file' ) ]`.
* **addDefinitionFile**   
  Adds another path (type `java.io.File`) to a definition file at the end of the already existing definition file paths.
* **placeholderValues (default: empty map)**   
  Property of type `java.util.Map` mapping the placeholder names (key; `java.lang.String`) 
  to their values (value; `java.lang.String`).
* **sourceDirs (default: `sourceSets.main.allSource.srcDirs - sourceSets.main.resources.srcDirs` 
  or `[]` if sourceSets.main does not exist)**   
  Property of type `java.util.Set` containing the source directories (`java.io.File`) 
  which should be checked by the watch dog plugin.
* **failBuild (default: `true`)**   
  Property of type `boolean` defining whether the build should fail if the watch dog 
  finds something or not.
## Define Additional `runWatchDog` tasks
```groovy
// Define new task from original FileNamingWatchDogTask
task runOtherWatchDog( type: org.luossfi.gradle.plugin.fnwdgp.FileNamingWatchDogTask ) {
  definitionFile = file( "other convention definition file" )
  placeholderValues = [ 'PLACEHOLDER': 'value' ]
  failBuild = false
  sourceDirs = [ file( 'bar' ) ]
}
```
## Migration to Version 1.1
Replace all occurrences of `fileNamingWatchDog` with `runWatchDog`.
## Todos

- [ ] Write Unit Tests
