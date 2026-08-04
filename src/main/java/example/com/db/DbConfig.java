package example.com.db;

import example.com.config.AppConfig;

public record DbConfig(
        String url,
        String username,
        String password
) {
    public static DbConfig initConfig() {
        return new DbConfig(
                AppConfig.get("db.url"),
                AppConfig.get("db.username"),
                AppConfig.get("db.password")
        );
    }}

