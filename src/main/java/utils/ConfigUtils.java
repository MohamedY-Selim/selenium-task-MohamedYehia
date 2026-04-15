package utils;

import java.io.File;
import java.net.URL;
import java.util.Properties;

public class ConfigUtils {

    private final Properties properties;
    private static ConfigUtils configUtils;
    private final String fileName;

    private ConfigUtils() {
        String env = System.getProperty("env", "TESTING");

        fileName = switch (env) {
            case "TESTING" -> "testing.properties";
            default -> throw new RuntimeException("Environment isn't supported");
        };

        properties = PropertiesUtils.loadProperties(fileName);
    }

    public static synchronized ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    public String getFileUrl(String key) {
        try {
            String relativePath = getProperty(key);
            File file = new File(relativePath);
            return file.toURI().toString();
        } catch (Exception e) {
            throw new RuntimeException("Error converting file path to URL for key: " + key, e);
        }
    }
}