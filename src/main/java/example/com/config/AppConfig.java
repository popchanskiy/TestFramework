package example.com.config;

import java.util.Locale;
import java.util.Properties;

public final class AppConfig {
    private static final Properties FILE_PROPS = ConfigLoader.getProperties();

    private AppConfig() {
    }

    public static String get(String key) {
        String systemPropertyValue = normalize(System.getProperty(key));
        if (systemPropertyValue != null) {
            return systemPropertyValue;
        }

        String envValue = getEnvValue(key);
        if (envValue != null) {
            return envValue;
        }

        String fileValue = normalize(FILE_PROPS.getProperty(key));
        if (fileValue == null) {
            throw new IllegalStateException("Missing config key: " + key);
        }

        return fileValue;
    }

    public static String getBaseUri() {
        return "%s:%s/api/v%s".formatted(
                get("host"),
                get("port"),
                get("api.version")
        );
    }

    private static String getEnvValue(String key) {
        String normalizedEnvKey = key
                .replace('.', '_')
                .replace('-', '_')
                .toUpperCase(Locale.ROOT);
        String envValue = normalize(System.getenv(normalizedEnvKey));
        if (envValue != null) {
            return envValue;
        }

        String snakeCaseEnvKey = key
                .replaceAll("([a-z0-9])([A-Z])", "$1_$2")
                .replace('.', '_')
                .replace('-', '_')
                .toUpperCase(Locale.ROOT);
        return normalize(System.getenv(snakeCaseEnvKey));
    }

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();
        return trimmedValue.isEmpty() ? null : trimmedValue;
    }
}
