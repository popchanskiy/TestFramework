package example.com.authorization;

import example.com.config.AppConfig;
import example.com.enums.AuthType;
import example.com.flows.LoginApi;
import example.com.models.resp_models.LoginUserResponseModel;

import java.util.Map;

public final class AuthFactory {
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_NODE = "api.token";


    private AuthFactory() {

    }

    public static Authorization resolve(AuthType authType) {
        return switch (authType) {
            case NONE -> new Authorization(Map.of());
            case ADMIN_BASIC -> adminBasic(AppConfig.get("admin.username"), AppConfig.get("admin.password"));
            case STATIC_TOKEN -> staticToken();
            case RUNTIME_TOKEN -> runtimeToken(AppConfig.get("runtime.token"));
        };
    }


    private static Authorization none() {
        return new Authorization(Map.of());
    }

    private static Authorization adminBasic(String username, String password) {
        LoginUserResponseModel responseModel = LoginApi.loginWithAdmin();
        return new Authorization(Map.of(AUTH_HEADER, "Basic " + responseModel.getToken()));
    }

    private static Authorization staticToken() {
        return new Authorization(Map.of(AUTH_HEADER, AppConfig.get(TOKEN_NODE)));
    }

    private static Authorization runtimeToken(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Runtime token is required");
        }

        return new Authorization(Map.of(AUTH_HEADER, token));
    }
}
