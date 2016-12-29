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

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The error message translator is a helper class which can be used to translate
 * input keys into the JVM's language. It also replaces any placeholders inside
 * the translated message with the input arguments.
 *
 * @author Steff Lukas
 * @since 1.0
 */
public class MessageTranslator
{

  /** The error message bundle's name. */
  private static final String ERROR_MESSAGE_BUNDLE = "ErrorMessages";

  /** The log message bundle's name. */
  private static final String LOG_MESSAGE_BUNDLE   = "LogMessages";

  /**
   * Translate the input error message key and replace the placeholders inside
   * the translation with the values from arguments.
   *
   * @param messageKey the message key
   * @param arguments the arguments
   * @return the translated and replaced error message or the messageKey if no
   *         translation could be found or the message bundle could not be
   *         instanciated.
   */
  public static String translateError( String messageKey, Object... arguments )
  {
    return translate( ERROR_MESSAGE_BUNDLE, messageKey, arguments );
  }

  /**
   * Translate the input log message key and replace the placeholders inside the
   * translation with the values from arguments.
   *
   * @param messageKey the message key
   * @param arguments the arguments
   * @return the translated and replaced log message or the messageKey if no
   *         translation could be found or the message bundle could not be
   *         instanciated.
   */
  public static String translateLog( String messageKey, Object... arguments )
  {
    return translate( LOG_MESSAGE_BUNDLE, messageKey, arguments );
  }

  /**
   * Translate the input message key by using the specified message bundle name
   * and replace the placeholders inside the translation with the values from
   * arguments.
   *
   * @param messageBundleName the message bundle name to use for translating
   * @param messageKey the message key
   * @param arguments the arguments
   * @return the translated and replaced log message or the messageKey if no
   *         translation could be found or the message bundle could not be
   *         instanciated.
   */
  private static String translate( String messageBundleName, String messageKey, Object... arguments )
  {
    String translation;
    try
    {
      ResourceBundle bundle = ResourceBundle.getBundle( messageBundleName );
      translation = bundle.getString( messageKey );

      if ( arguments.length > 0 )
      {
        translation = MessageFormat.format( translation, arguments );
      }
    }
    catch ( MissingResourceException e )
    {
      translation = messageKey;
    }

    return translation;
  }
}
