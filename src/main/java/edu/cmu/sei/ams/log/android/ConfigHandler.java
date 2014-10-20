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
        try
        {
            Properties props = new Properties();
            props.load(is);
            for (String key : props.stringPropertyNames())
            {
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

                        if (tagName.length() <= 23)
                        {
                            configMap.put(name, new Config(tagName, Config.LogLevel.of(level)));
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("AndroidLogger", "Unable to load android-logging.properties");
        }
        if (!configMap.containsKey("root"))
            configMap.put("root", new Config());
    }

    private Config lookupConfig(String name)
    {
        if (name == null)
            return configMap.get("root");

        String deepestKey = "root";
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

        return configMap.get(deepestKey);
    }

    public static Config getConfig(String name)
    {
        return config.lookupConfig(name);
    }
}
