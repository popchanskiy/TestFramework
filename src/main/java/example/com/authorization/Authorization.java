package example.com.authorization;

import java.util.Map;

public class Authorization {
    private final Map<String, String> headers;

    public Authorization(Map<String, String> headers) {
        this.headers = headers == null||headers.isEmpty() ? Map.of() : Map.copyOf(headers);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
