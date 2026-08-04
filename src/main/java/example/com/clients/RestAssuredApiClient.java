package example.com.clients;

import example.com.requests.ApiRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredApiClient {

    private static RequestSpecification initRequest(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, ApiRequest apiRequest) {
        var sender = given().spec(requestSpecification);
        if (apiRequest.getBody() != null) {
            sender.body(apiRequest.getBody());
        }

        sender.headers(apiRequest.getAllHeaders());

        return sender;
    }

    public static Response execute(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, ApiRequest apiRequest) {
        var sender = initRequest(requestSpecification, responseSpecification, apiRequest);
        var method = apiRequest.getEndpoint().method();
        var path = apiRequest.getEndpoint().path();

        return switch (method) {
            case POST -> sender.post(path);

            case GET -> sender.get(path);

            case PUT -> sender.put(path);

            case DELETE -> sender.delete(path);

            case PATCH -> sender.patch(path);
        };
    }

    public static <T> T executeAs(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, ApiRequest apiRequest, Class<T> responseType) {
        Response validatableResponse = execute(requestSpecification, responseSpecification, apiRequest);
        return validatableResponse.then()
                .spec(responseSpecification)
                .extract()
                .as(responseType);
    }

    public static ValidatableResponse executeRaw(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, ApiRequest apiRequest) {
        Response validatableResponse = execute(requestSpecification, responseSpecification, apiRequest);
        return validatableResponse.then()
                .spec(responseSpecification);
    }

}

