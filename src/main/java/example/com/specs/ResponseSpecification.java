package example.com.specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseLogSpecification;
import org.apache.http.HttpStatus;

public class ResponseSpecification {
    private  ResponseSpecification() {
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
