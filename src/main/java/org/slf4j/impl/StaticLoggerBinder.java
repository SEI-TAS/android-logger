package org.slf4j.impl;

import android.util.Log;
import edu.cmu.sei.ams.log.android.LoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * User: jdroot
 * Date: 9/2/14
 * Time: 2:00 PM
 */
public class StaticLoggerBinder implements LoggerFactoryBinder
{
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    public static String REQUESTED_API_VERSION = "1.7.7";

    private final String loggerFactoryClsStr = LoggerFactory.class.getName();
    private final ILoggerFactory loggerFactory = new LoggerFactory();

    public static StaticLoggerBinder getSingleton()
    {
        return SINGLETON;
    }

    @Override
    public ILoggerFactory getLoggerFactory()
    {
        Log.v("LOGGER", "Returning the logger factory");
        return loggerFactory;
    }

    @Override
    public String getLoggerFactoryClassStr()
    {
        return loggerFactoryClsStr;
    }
}
