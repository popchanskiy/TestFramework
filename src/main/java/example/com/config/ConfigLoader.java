package example.com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = loadProps();
    private static final String PROP_FILE = "config.properties";

    private ConfigLoader(){

    }
    private static Properties loadProps() {
        Properties properties = new Properties();
        try (InputStream input = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(PROP_FILE)) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            properties.load(input);
           return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

}
