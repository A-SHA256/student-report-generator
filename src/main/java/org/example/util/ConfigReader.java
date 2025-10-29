package org.example.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();
    static{
        try(InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Reading of config is null: ");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Reading of config unsuccessful: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
