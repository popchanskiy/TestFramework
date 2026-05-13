package example.com.requests;

import example.com.models.BaseModel;
import example.com.models.CreateAccountRequestModel;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CreateAccountRequest extends Request<CreateAccountRequestModel> {
    public CreateAccountRequest(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(CreateAccountRequestModel model) {
        return given().spec(requestSpecification)
                .body("")
                .post("/api/v1/accounts")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
