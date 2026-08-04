package example.com.requests;

import example.com.authorization.Authorization;
import example.com.models.Endpoint;

import java.util.HashMap;
import java.util.Map;

public final class ApiRequest<T> {
    private final Endpoint endpoint;
    private final T body;
    private final Map<String, String> headers;
    private final Authorization authorization;

    public ApiRequest(
            Endpoint endpoint,
            T body,
            Map<String, String> headers,
            Authorization authorization
    ) {
        this.endpoint = endpoint;
        this.body = body;
        this.headers = headers == null ? Map.of() : Map.copyOf(headers);
        this.authorization = authorization == null ? new Authorization(Map.of()) : authorization;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public T getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public Map<String, String> getAllHeaders() {
        Map<String, String> merged = new HashMap<>(authorization.getHeaders());
        merged.putAll(headers);
        return Map.copyOf(merged);
    }
}
