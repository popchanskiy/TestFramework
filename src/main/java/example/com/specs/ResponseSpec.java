package example.com.specs;

import io.restassured.builder.ResponseSpecBuilder;
import org.apache.http.HttpStatus;

public class ResponseSpec {
    private ResponseSpec() {
    }

    private static ResponseSpecBuilder defaultResponseSpecification() {
        return new ResponseSpecBuilder();
    }

    public static io.restassured.specification.ResponseSpecification EntityCreatedResponseSpecification() {
       return defaultResponseSpecification().expectStatusCode(HttpStatus.SC_CREATED).build();
    }


    public static io.restassured.specification.ResponseSpecification OK200ResponseSpecification() {
        return defaultResponseSpecification().expectStatusCode(HttpStatus.SC_OK).build();
    }
}
