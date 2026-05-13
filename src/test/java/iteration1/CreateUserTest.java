package iteration1;

import example.com.models.CreateUserRequestModel;
import example.com.models.CreatedUserResponseModel;
import example.com.models.Role;
import example.com.requests.CreateUserRequest;
import example.com.specs.RequestSpecification;
import example.com.specs.ResponseSpecification;
import example.com.utills.DataGenerator;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseTest{
    @Test
    public void createUserTest() {
        CreateUserRequestModel userRequestModel = CreateUserRequestModel.builder()
                .username(DataGenerator.generateUsername())
                .password(DataGenerator.generatePassword())
                .role(Role.USER)
                .build();
        CreateUserRequest createUserRequest = new CreateUserRequest(RequestSpecification.getAdminRequestSpecification(),
                ResponseSpecification.EntityCreatedResponseSpecification());
        ValidatableResponse response = createUserRequest.post(userRequestModel);
        response.extract().as(CreatedUserResponseModel.class);
    }
}
