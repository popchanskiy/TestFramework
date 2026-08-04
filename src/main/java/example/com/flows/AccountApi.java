package example.com.flows;

import example.com.authorization.Authorization;
import example.com.clients.RestAssuredApiClient;
import example.com.enums.EndpointList;
import example.com.models.req_models.CreateAccountRequestModel;
import example.com.models.resp_models.CreateAccountResponseModel;
import example.com.requests.ApiRequest;
import example.com.specs.RequestSpec;
import example.com.specs.ResponseSpec;

import java.util.Map;

public class AccountApi {
    public static  CreateAccountResponseModel createAccountByUser(String token) {
        ApiRequest<CreateAccountRequestModel> request = new ApiRequest<>(
                EndpointList.ACCOUNTS.getEndpoint(),
                null,
                Map.of(),
                new Authorization(Map.of("Authorization",token))
        );

        return RestAssuredApiClient.executeAs(
                RequestSpec.getDefaultRequestSpecification(),
                ResponseSpec.EntityCreatedResponseSpecification(),
                request,
                CreateAccountResponseModel.class
        );
    }
}
