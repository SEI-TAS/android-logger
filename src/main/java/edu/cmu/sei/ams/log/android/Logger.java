/*
KVM-based Discoverable Cloudlet (KD-Cloudlet) 
Copyright (c) 2015 Carnegie Mellon University.
All Rights Reserved.

THIS SOFTWARE IS PROVIDED "AS IS," WITH NO WARRANTIES WHATSOEVER. CARNEGIE MELLON UNIVERSITY EXPRESSLY DISCLAIMS TO THE FULLEST EXTENT PERMITTEDBY LAW ALL EXPRESS, IMPLIED, AND STATUTORY WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND NON-INFRINGEMENT OF PROPRIETARY RIGHTS.

Released under a modified BSD license, please see license.txt for full terms.
DM-0002138

KD-Cloudlet includes and/or makes use of the following Third-Party Software subject to their own licenses:
MiniMongo
Copyright (c) 2010-2014, Steve Lacy 
All rights reserved. Released under BSD license.
https://github.com/MiniMongo/minimongo/blob/master/LICENSE

Bootstrap
Copyright (c) 2011-2015 Twitter, Inc.
Released under the MIT License
https://github.com/twbs/bootstrap/blob/master/LICENSE

jQuery JavaScript Library v1.11.0
http://jquery.com/
Includes Sizzle.js
http://sizzlejs.com/
Copyright 2005, 2014 jQuery Foundation, Inc. and other contributors
Released under the MIT license
http://jquery.org/license
*/
package edu.cmu.sei.ams.log.android;

import android.util.Log;
import edu.cmu.sei.ams.log.android.Config.LogLevel;
import org.slf4j.Marker;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * User: jdroot
 * Date: 9/2/14
 * Time: 2:09 PM
 */
public class Logger extends MarkerIgnoringBase implements LocationAwareLogger
{
    private final String name;
    private final Config config;
    private final String tag;

    public Logger(String name, Config config)
    {
        this.name = name;
        this.config = config;
        tag = config.getTagName();
    }

    private void log(LogLevel level, String msg, Throwable t)
    {
        switch (level)
        {
            case Verbose: Log.v(tag, msg, t); break;
            case Debug: Log.d(tag, msg, t); break;
            case Info: Log.i(tag, msg, t); break;
            case Warn: Log.w(tag, msg, t); break;
            case Error: Log.e(tag, msg, t); break;
            case Assert: Log.wtf(tag, msg, t); break;
        }
    }

    private void log(LogLevel level, String format, Object arg)
    {
        log(level, MessageFormatter.format(format, arg).getMessage());
    }

    private void log(LogLevel level, String format, Object arg1, Object arg2)
    {
        log(level, MessageFormatter.format(format, arg1, arg2).getMessage());
    }

    private void log(LogLevel level, String format, Object...args)
    {
        log(level, MessageFormatter.arrayFormat(format, args).getMessage(), (Throwable)null);
    }


    @Override
    public boolean isTraceEnabled()
    {
        return config.isEnabled(LogLevel.Verbose);
    }

    @Override
    public void trace(String msg)
    {
        log(LogLevel.Verbose, msg);
    }

    @Override
    public void trace(String format, Object arg)
    {
        log(LogLevel.Verbose, format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2)
    {
        log(LogLevel.Verbose, format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments)
    {
        log(LogLevel.Verbose, format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t)
    {
        log(LogLevel.Verbose, msg, t);
    }

    @Override
    public boolean isDebugEnabled()
    {
        return config.isEnabled(LogLevel.Debug);
    }

    @Override
    public void debug(String msg)
    {
        log(LogLevel.Debug, msg);
    }

    @Override
    public void debug(String format, Object arg)
    {
        log(LogLevel.Debug, format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2)
    {
        log(LogLevel.Debug, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments)
    {
        log(LogLevel.Debug, format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t)
    {
        log(LogLevel.Debug, msg, t);
    }

    @Override
    public boolean isInfoEnabled()
    {
        return config.isEnabled(LogLevel.Info);
    }

    @Override
    public void info(String msg)
    {
        log(LogLevel.Info, msg);
    }

    @Override
    public void info(String format, Object arg)
    {
        log(LogLevel.Info, format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2)
    {
        log(LogLevel.Info, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments)
    {
        log(LogLevel.Info, format, arguments);
    }

    @Override
    public void info(String msg, Throwable t)
    {
        log(LogLevel.Info, msg, t);
    }

    @Override
    public boolean isWarnEnabled()
    {
        return config.isEnabled(LogLevel.Warn);
    }

    @Override
    public void warn(String msg)
    {
        log(LogLevel.Warn, msg);
    }

    @Override
    public void warn(String format, Object arg)
    {
        log(LogLevel.Warn, format, arg);
    }

    @Override
    public void warn(String format, Object... arguments)
    {
        log(LogLevel.Warn, format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2)
    {
        log(LogLevel.Warn, format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t)
    {
        log(LogLevel.Warn, msg, t);
    }

    @Override
    public boolean isErrorEnabled()
    {
        return config.isEnabled(LogLevel.Error);
    }

    @Override
    public void error(String msg)
    {
        log(LogLevel.Error, msg);
    }

    @Override
    public void error(String format, Object arg)
    {
        log(LogLevel.Error, format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2)
    {
        log(LogLevel.Error, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments)
    {
        log(LogLevel.Error, format, arguments);
    }

    @Override
    public void error(String msg, Throwable t)
    {
        log(LogLevel.Error, msg, t);
    }

    /**
     * Printing method with support for location information.
     *
     * @param marker The marker to be used for this event, may be null. IGNORED
     * @param fqcn The fully qualified class name of the <b>logger instance</b>,
     * typically the logger class, logger bridge or a logger wrapper.
     * @param level One of the level integers defined in this interface
     * @param message The message for the log event
     * @param t Throwable associated with the log event, may be null.
     */
    @Override
    public void log(Marker marker, String fqcn, int level, String message, Object[] argArray, Throwable t)
    {
        StackTraceElement element = calcLocation(fqcn);

        String className = element.getClassName();
        int indx = className.lastIndexOf('.');
        if (indx >= 0)
            className = className.substring(indx + 1);

        String msg = className + "#" + element.getMethodName() + " - " + message;

        switch (level)
        {
            case TRACE_INT:
                if (t == null)
                    trace(marker, msg, argArray);
                else
                    trace(marker, msg, t);
                break;
            case DEBUG_INT:
                if (t == null)
                    debug(marker, msg, argArray);
                else
                    debug(marker, msg, t);
                break;
            case INFO_INT:
                if (t == null)
                    info(marker, msg, argArray);
                else
                    info(marker, msg, t);
                break;
            case WARN_INT:
                if (t == null)
                    info(marker, msg, argArray);
                else
                    info(marker, msg, t);
                break;
            case ERROR_INT:
                if (t == null)
                    error(marker, msg, argArray);
                else
                    error(marker, msg, t);
                break;
            default:
                break; // Do nothing
        }
    }

    private static StackTraceElement calcLocation(final String fqcnOfLogger) {
        if (fqcnOfLogger == null) {
            return null;
        }
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement last = null;
        for (int i = stackTrace.length - 1; i > 0; i--) {
            final String className = stackTrace[i].getClassName();
            if (fqcnOfLogger.equals(className)) {
                return last;
            }
            last = stackTrace[i];
        }
        return null;
    }
}
