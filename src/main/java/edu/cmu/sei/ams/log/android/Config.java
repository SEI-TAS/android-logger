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

/**
 * User: jdroot
 * Date: 9/2/14
 * Time: 2:53 PM
 */
public class Config
{
    public static enum LogLevel
    {
        Verbose(2),
        Debug(3),
        Info(4),
        Warn(5),
        Error(6),
        Assert(7);

        public static final LogLevel Default = Verbose;

        private final int level;
        private LogLevel(int level)
        {
            this.level = level;
        }

        public static LogLevel of(String value)
        {
            for (LogLevel ll : LogLevel.values())
            {
                if (ll.name().equalsIgnoreCase(value))
                    return ll;
            }
            return Default;
        }

        public boolean contains(LogLevel level)
        {
            return level != null && level.level > this.level;
//            if (level == null)
//                return false;
//            return level.level >= this.level;
        }
    }

    private final String tagName;
    private final LogLevel logLevel;

    public Config()
    {
        this("android-slf4j", LogLevel.Default);
    }

    public Config(String tagName, LogLevel level)
    {
        this.tagName = tagName;
        this.logLevel = level;
    }

    public boolean isEnabled(LogLevel level)
    {
        return logLevel.contains(level);
    }

    public String getTagName()
    {
        return tagName;
    }
}
