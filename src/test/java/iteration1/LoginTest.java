package iteration1;

import example.com.models.CreateUserRequestModel;
import example.com.models.CreatedUserResponseModel;
import example.com.models.LoginUserRequestModel;
import example.com.models.Role;
import example.com.requests.CreateUserRequest;
import example.com.requests.LoginUserRequest;
import example.com.specs.RequestSpecification;
import example.com.specs.ResponseSpecification;
import example.com.utills.DataGenerator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest{

    @Test
    public void adminGetAuthTokenTest() {
        given().contentType("application/json").
                baseUri("http://localhost:4111/api/v1/auth/login")
                .accept(ContentType.JSON).body("""
                        {
                          "username": "admin",
                          "password": "admin"
                        }""").post().
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                header("Authorization", "Basic YWRtaW46YWRtaW4=");

    }

    @Test
    public void userLoginTest() {
        // create user by admin
        CreateUserRequestModel userRequestModel = CreateUserRequestModel.builder()
                .username(DataGenerator.generateUsername())
                .password(DataGenerator.generatePassword())
                .role(Role.USER)
                .build();
        CreateUserRequest createUserRequest = new CreateUserRequest(RequestSpecification.getAdminRequestSpecification(),
                ResponseSpecification.EntityCreatedResponseSpecification());
        ValidatableResponse response = createUserRequest.post(userRequestModel);
        response.extract().as(CreatedUserResponseModel.class);

        //login user
        LoginUserRequestModel loginUserRequestModel = LoginUserRequestModel.builder()
                .username(userRequestModel.getUsername())
                .password(userRequestModel.getPassword()).build();
        LoginUserRequest loginUserRequest = new LoginUserRequest(RequestSpecification.getUnauthUserRequestSpecification(),
                ResponseSpecification.OK200ResponseSpecification());
        ValidatableResponse loginUserRequestResponse = loginUserRequest.post(loginUserRequestModel);
    }

}
