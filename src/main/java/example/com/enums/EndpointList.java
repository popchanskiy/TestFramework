package example.com.enums;

import example.com.models.Endpoint;

public enum EndpointList {
    ADMIN_USERS(new Endpoint(HttpMethod.POST, "/admin/users")),
    AUTH_LOGIN(new Endpoint(HttpMethod.POST, "/auth/login")),
    ACCOUNTS(new Endpoint(HttpMethod.POST, "/accounts")),
    DEPOSIT(new Endpoint(HttpMethod.POST, "/accounts/deposit")),
    TRANSFER(new Endpoint(HttpMethod.POST, "/accounts/transfer"));

    private final Endpoint endpoint;

    EndpointList(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }
}
