package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties loadProperties(String filePath) {

        System.out.println("Loading: " + filePath);

        InputStream inputStream =
                PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);

        System.out.println("InputStream = " + inputStream);

        if (inputStream == null) {
            throw new RuntimeException("Could not find properties file: " + filePath);
        }

        Properties properties = new Properties();

        try {
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}