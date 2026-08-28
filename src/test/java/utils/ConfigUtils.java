package utils;

import java.util.Properties;

public class ConfigUtils {

    private static ConfigUtils instance;

    private final Properties properties;

    private ConfigUtils() {

        String env = System.getProperty("env", "production").toLowerCase();

        switch (env) {

            case "production":
                properties = PropertiesUtils.loadProperties(
                        "config/production.properties");
                break;

            case "local":
                properties = PropertiesUtils.loadProperties(
                        "config/local.properties");
                break;

            default:
                throw new RuntimeException("Environment not supported: " + env);
        }
    }

    public static ConfigUtils getInstance() {

        if (instance == null) {
            instance = new ConfigUtils();
        }

        return instance;
    }

    public String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public String getEmail() {
        return getProperty("email");
    }

    public String getPassword() {
        return getProperty("password");
    }

    private String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new RuntimeException(key + " not found in properties file");
        }

        return value;
    }
}