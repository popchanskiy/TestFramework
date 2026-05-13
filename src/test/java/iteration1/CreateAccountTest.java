package iteration1;

import example.com.steps.CreateUserStep;
import example.com.models.*;
import example.com.requests.*;
import example.com.specs.RequestSpecification;
import example.com.specs.ResponseSpecification;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateAccountTest extends BaseTest {

    @Test
    public void createUserTest() {
        // user create req
        CreatedUserResponseModel responseModel = CreateUserStep.createUser();
        softly.assertThat(responseModel.getUserId()).isNotNull();

        softly.assertThat(responseModel.getUserName())
                .isEqualTo(user.getUsername());

        softly.assertThat(response.getRole())
                .isEqualTo(user.getRole());

        softly.assertThat(response.getPassword())
                .isNotEqualTo(user.getPassword());

        //login user
        LoginUserRequestModel loginUserRequestModel = LoginUserRequestModel.builder()
                .username(userRequestModel.getUsername())
                .password(userRequestModel.getPassword()).build();
        LoginUserRequest loginUserRequest = new LoginUserRequest(RequestSpecification.getUnauthUserRequestSpecification(),
                ResponseSpecification.OK200ResponseSpecification());
        ValidatableResponse loginUserRequestResponse = loginUserRequest.post(loginUserRequestModel);
        loginUserRequestResponse.assertThat().header("Authorization", Matchers.notNullValue());

        LoginUserResponseModel loginUserResponseModel = loginUserRequestResponse.extract().as(LoginUserResponseModel.class);

        assertThat(loginUserResponseModel.getRole(), Matchers.is(Role.USER));
        assertThat(loginUserResponseModel.getUsername(), Matchers.is(userRequestModel.getUsername()));

        String token = loginUserRequestResponse.extract().header("Authorization");

        //create account user
        CreateAccountRequestModel accountRequestModel = new CreateAccountRequestModel();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(RequestSpecification.getAuthtorizedUserRequestSpecification(token),
                ResponseSpecification.EntityCreatedResponseSpecification());
        ValidatableResponse responseCreateAccountRequest = createAccountRequest.post(accountRequestModel);
        CreateAccountResponseModel createAccountResponseModel = responseCreateAccountRequest.extract().as(CreateAccountResponseModel.class);

    }
}
