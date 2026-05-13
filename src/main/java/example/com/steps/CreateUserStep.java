package example.com.steps;

import example.com.models.CreateUserRequestModel;
import example.com.models.CreatedUserResponseModel;
import example.com.models.Role;
import example.com.requests.Endpoint;
import example.com.requests.ValidatableHttpRequester;
import example.com.specs.RequestSpecification;
import example.com.specs.ResponseSpecification;
import example.com.utills.DataGenerator;

public class CreateUserStep {

public static CreatedUserRequestModel prepareRandomUser(){
    var user = DataGenerator.generate(CreateUserRequestModel.class);
    user.setRole(Role.USER);
    return user;
}

    ValidatableHttpRequester<CreatedUserResponseModel> requester = new ValidatableHttpRequester<>(
            RequestSpecification.getAdminRequestSpecification(),
            ResponseSpecification.EntityCreatedResponseSpecification(),
            Endpoint.ADMIN_USERS
    );
    return requester.post(user);
}


}
