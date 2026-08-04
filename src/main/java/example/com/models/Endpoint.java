package example.com.models;

import example.com.enums.HttpMethod;

public record Endpoint(
        HttpMethod method,
        String path
) {
}
