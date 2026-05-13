package example.com.requests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

abstract class AbstractHttpRequest {
    protected io.restassured.specification.RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;
    protected Endpoint endpoint;

    public AbstractHttpRequest(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, Endpoint endpoint) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
        this.endpoint = endpoint;
    }


}
