package example.com.flows;

import example.com.authorization.AuthFactory;
import example.com.clients.RestAssuredApiClient;
import example.com.config.AppConfig;
import example.com.enums.AuthType;
import example.com.enums.EndpointList;
import example.com.models.req_models.LoginUserRequestModel;
import example.com.models.resp_models.LoginUserResponseModel;
import example.com.requests.ApiRequest;
import example.com.specs.RequestSpec;
import example.com.specs.ResponseSpec;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class LoginApi {


    public static LoginUserResponseModel loginWithUser(LoginUserRequestModel body) {
        String token = null;
        ApiRequest<LoginUserRequestModel> request = new ApiRequest<>(
                EndpointList.AUTH_LOGIN.getEndpoint(),
                body,
                Map.of(),
                AuthFactory.resolve(AuthType.NONE)
        );
        ValidatableResponse response = RestAssuredApiClient.executeRaw(RequestSpec.getDefaultRequestSpecification(),
                ResponseSpec.OK200ResponseSpecification(),
                request);
        token = response.extract().header("Authorization");

        LoginUserResponseModel responseModel = response.extract().body().as(LoginUserResponseModel.class);
        responseModel.setToken(token);
        return responseModel;
    }

    public static LoginUserResponseModel loginWithAdmin() {
        String token = null;
        LoginUserRequestModel body = new LoginUserRequestModel(AppConfig.get("admin.username") ,AppConfig.get("admin.password"));
        ApiRequest<LoginUserRequestModel> request = new ApiRequest<>(
                EndpointList.AUTH_LOGIN.getEndpoint(),
                body,
                Map.of(),
                AuthFactory.resolve(AuthType.NONE)
        );
        ValidatableResponse response = RestAssuredApiClient.executeRaw(RequestSpec.getDefaultRequestSpecification(),
                ResponseSpec.OK200ResponseSpecification(),
                request);
        token = response.extract().header("Authorization");

        LoginUserResponseModel responseModel = response.extract().body().as(LoginUserResponseModel.class);
        responseModel.setToken(token);
        return responseModel;
    }
}

