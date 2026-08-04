package example.com.flows;

import example.com.authorization.Authorization;
import example.com.clients.RestAssuredApiClient;
import example.com.enums.EndpointList;
import example.com.enums.Role;
import example.com.models.req_models.CreateUserRequestModel;
import example.com.models.resp_models.CreatedUserResponseModel;
import example.com.requests.ApiRequest;
import example.com.specs.RequestSpec;
import example.com.specs.ResponseSpec;

import java.util.Map;

public class UserApi {

    public static CreatedUserResponseModel createByAdmin(CreateUserRequestModel body, String token, Role role) {
        body.setRole(role);
        ApiRequest<CreateUserRequestModel> request = new ApiRequest<>(
                EndpointList.ADMIN_USERS.getEndpoint(),
                body,
                Map.of(),
                new Authorization(Map.of("Authorization", token))
        );

        return RestAssuredApiClient.executeAs(
                RequestSpec.getDefaultRequestSpecification(),
                ResponseSpec.EntityCreatedResponseSpecification(),
                request,
                CreatedUserResponseModel.class
        );
    }

}
