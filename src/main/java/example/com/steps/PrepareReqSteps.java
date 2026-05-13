package example.com.steps;

import example.com.models.BaseModel;
import example.com.models.CreatedUserResponseModel;
import example.com.requests.Endpoint;
import example.com.requests.ValidatableHttpRequester;
import example.com.specs.RequestSpecification;
import example.com.specs.ResponseSpecification;

public class PrepareReqSteps {

    public static ValidatableHttpRequester<? extends BaseModel> prepareCreateUserReq() {
        ValidatableHttpRequester<CreatedUserResponseModel> requester = new ValidatableHttpRequester<>(
                RequestSpecification.getAdminRequestSpecification(),
                ResponseSpecification.EntityCreatedResponseSpecification(),
                Endpoint.ADMIN_USERS
        );
        return requester;
    }
}
