package example.com.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import java.util.List;

public class RequestSpecification {
    private static final String ADMIN_BEARER = "Basic YWRtaW46YWRtaW4=";
    private static final String AUTH_HEADER_NAME = "Authorization";

    private RequestSpecification() {
    }

    private static RequestSpecBuilder getDefaultRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri("http://localhost:4111")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).addFilters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()));
    }


    public static io.restassured.specification.RequestSpecification getAdminRequestSpecification() {
        return getDefaultRequestSpecification().addHeader(AUTH_HEADER_NAME, ADMIN_BEARER).build();
    }

    public static io.restassured.specification.RequestSpecification getUnauthUserRequestSpecification() {
        return getDefaultRequestSpecification().build();
    }

    public static io.restassured.specification.RequestSpecification getAuthtorizedUserRequestSpecification(String authToken) {
        return getDefaultRequestSpecification().addHeader(AUTH_HEADER_NAME, authToken).build();
    }


}
