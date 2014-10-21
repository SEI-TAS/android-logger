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
