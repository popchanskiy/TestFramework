package example.com.requests;

import example.com.models.BaseModel;
import example.com.models.CreateUserRequestModel;
import example.com.models.CreatedUserResponseModel;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CreateUserRequest extends Request<CreateUserRequestModel> {
    public CreateUserRequest(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(CreateUserRequestModel model) {
        return given().spec(requestSpecification).body(model).post("/api/v1/admin/users")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}

