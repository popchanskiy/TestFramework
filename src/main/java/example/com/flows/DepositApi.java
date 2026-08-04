package example.com.flows;

import example.com.authorization.Authorization;
import example.com.clients.RestAssuredApiClient;
import example.com.enums.EndpointList;
import example.com.models.req_models.DepositRequestModel;
import example.com.requests.ApiRequest;
import example.com.specs.RequestSpec;
import example.com.specs.ResponseSpec;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class DepositApi {
    public static ValidatableResponse depositToAccount(DepositRequestModel depositRequestModel, String token) {
        ApiRequest<DepositRequestModel> request = new ApiRequest<>(
                EndpointList.DEPOSIT.getEndpoint(),
                depositRequestModel,
                Map.of(),
                new Authorization(Map.of("Authorization", token))
        );

        return RestAssuredApiClient.executeRaw(
                RequestSpec.getDefaultRequestSpecification(),
                ResponseSpec.OK200ResponseSpecification(),
                request
        );
    }
}
