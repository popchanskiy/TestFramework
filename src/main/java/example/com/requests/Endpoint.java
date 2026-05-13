package example.com.requests;

import example.com.models.*;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoint {

    ADMIN_USERS("/admin/users", CreateUserRequestModel.class, CreatedUserResponseModel.class),

    ACCOUNTS("/accounts", CreateAccountRequestModel.class, CreateAccountResponseModel.class),

    AUTH_LOGIN("/auth/login", LoginUserRequestModel.class, LoginUserResponseModel.class);
    private String path;
    private Class<? extends BaseModel> responseModel;
    private Class<? extends BaseModel> requestModel;
}
