package edu.cmu.sei.ams.log.android;

import org.slf4j.ILoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jdroot
 * Date: 9/2/14
 * Time: 2:06 PM
 */
public class LoggerFactory implements ILoggerFactory
{
    private static final Map<String, org.slf4j.Logger> CACHE = new HashMap<String, org.slf4j.Logger>();

    @Override
    public org.slf4j.Logger getLogger(String name)
    {
        synchronized (CACHE)
        {
            org.slf4j.Logger ret = CACHE.get(name);
            if (ret == null)
            {
                ret = new Logger(name, ConfigHandler.getConfig(name));
                CACHE.put(name, ret);
            }
            return ret;
        }
    }
}
