package org.slf4j.impl;

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
    private static final StaticLoggerBinder instance = new StaticLoggerBinder();

    private final String loggerFactoryClsStr = LoggerFactory.class.getName();
    private final ILoggerFactory loggerFactory = new LoggerFactory();

    public static StaticLoggerBinder getSingleton()
    {
        return instance;
    }

    @Override
    public ILoggerFactory getLoggerFactory()
    {
        return loggerFactory;
    }

    @Override
    public String getLoggerFactoryClassStr()
    {
        return loggerFactoryClsStr;
    }
}
