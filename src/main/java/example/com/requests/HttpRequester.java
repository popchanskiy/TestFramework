package example.com.requests;

import example.com.models.BaseModel;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class HttpRequester extends AbstractHttpRequest implements CrudRepository {
    public HttpRequester(io.restassured.specification.RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification, endpoint);
    }

    @Override
    public ValidatableResponse post(BaseModel baseModel) {
        var body = baseModel == null ? "": baseModel;
        return given().spec(this.requestSpecification).body(body).post(endpoint.getPath())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public Object update() {
        return null;
    }

    @Override
    public Object delete() {
        return null;
    }
}
