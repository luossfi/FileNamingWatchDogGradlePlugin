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
```groovy
buildscript {
  repositories {
    //TODO: Define afer publishing
  }
  
  dependencies {
    classpath "org.luossfi.gradle:FileNamingWatchDogGradlePlugin:1.0"
  }
}

apply plugin: 'org.luossfi.file.naming.watch.dog'
```

## Configuring The Plugin
```groovy
fileNamingWatchDog {
  definitionFile = file("Path to Convention Definition File")
  placeholderValues = ["PLACEHOLDER":"value"]
  failBuild = true
  sourceDirs = sourceSets.main.java.srcDirs
}
```
* **definitionFile (default: `file( "./ConventionDefinition" )`)**   
  Property of type `java.io.File` defining where the naming convention definition file 
  can be found. See [File Naming Watch Dog Documentation](https://github.com/luossfi/FileNamingWatchDog/blob/master/doc/FileNamingWatchDog.md)
  for details on how to define the naming conventions.
* **placeholderValues (default: `[]`)**   
  Property of type `java.util.Map` mapping the placeholder names (key; `java.lang.String`) 
  to their values (value; `java.lang.String`).
* **sourceDirs (default: `sourceSets.main.allSource.srcDirs - sourceSets.main.resources.srcDirs` 
  or `[]` if sourceSets.main does not exist)**   
  Property of type `java.util.Set` containing the source directories (`java.io.File`) 
  which should be checked by the watch dog plugin.
* **failBuild (default: `true`)**   
  Property of type `boolean` defining whether the build should fail if the watch dog 
  finds something or not.

## Todos

- [ ] Write Unit Tests
