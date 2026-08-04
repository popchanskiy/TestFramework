package example.com.specs;

import example.com.config.AppConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class RequestSpec {

    private RequestSpec() {
    }

    public static RequestSpecification getDefaultRequestSpecification() {

        return new RequestSpecBuilder().setBaseUri(AppConfig.getBaseUri())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).addFilters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter())).build();
    }

}
