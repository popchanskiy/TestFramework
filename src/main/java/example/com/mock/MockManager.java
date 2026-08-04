package example.com.mock;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class MockManager {
    private final String adminBaseUrl;


    public MockManager(String adminBaseUrl) {
        this.adminBaseUrl = adminBaseUrl;
    }

    public void resetAllMocks() {
        given()
                .baseUri(adminBaseUrl)
                .post("/__admin/reset")
                .then()
                .statusCode(200);
    }

    public void registerNewStub(String mappingJson) {
        given()
                .baseUri(adminBaseUrl)
                .contentType(ContentType.JSON)
                .body(mappingJson)
                .post("/__admin/mappings")
                .then()
                .statusCode(201);
    }
}
