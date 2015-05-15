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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: jdroot
 * Date: 9/2/14
 * Time: 2:48 PM
 *
 * Will match logging entries of the format:
 * logger.package=level:tag
 */
public class ConfigHandler
{
    private static final Pattern NAME_REGEX = Pattern.compile("^logger\\.(.*)$");
    private static final Pattern VALUE_REGEX = Pattern.compile("(.*):(.*)");

    private static final ConfigHandler config = new ConfigHandler();

    private final Map<String, Config> configMap = new HashMap<String, Config>();

    private ConfigHandler()
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("android-logging.properties");
        if(is == null)
        {
            Log.w("android-slf4j", "Unable to find android-logging.properties");
        }
        else
        {
            try
            {
                Properties props = new Properties();
                props.load(is);
                for (String key : props.stringPropertyNames()) {
                    Matcher nameMatcher = NAME_REGEX.matcher(key);
                    if (nameMatcher.matches()) // Ignore bad names
                    {
                        String name = nameMatcher.group(1);
                        String value = props.getProperty(key);
                        Matcher valueMatcher = VALUE_REGEX.matcher(value);
                        if (valueMatcher.matches()) // Ignore bad values
                        {
                            String level = valueMatcher.group(1);
                            String tagName = valueMatcher.group(2);

                            if (tagName.length() <= 23) {
                                configMap.put(name, new Config(tagName, Config.LogLevel.of(level)));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("android-slf4j", "Unable to load android-logging.properties");
            }
        }

        if (!configMap.containsKey("root"))
            configMap.put("root", new Config());
    }

    private Config lookupConfig(String name)
    {
        if (name == null)
            return configMap.get("root");

        String deepestKey = "";
        for (String key : configMap.keySet())
        {
            if (key.equals(name))
            {
                deepestKey = key;
                break;
            }

            if (name.startsWith(key) && key.length() > deepestKey.length())
            {
                deepestKey = key;
            }
        }
        if (deepestKey.length() == 0)
            deepestKey = "root";

        return configMap.get(deepestKey);
    }

    public static Config getConfig(String name)
    {
        return config.lookupConfig(name);
    }
}
