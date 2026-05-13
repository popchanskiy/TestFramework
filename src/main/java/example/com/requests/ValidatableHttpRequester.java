package example.com.requests;

import example.com.models.BaseModel;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ValidatableHttpRequester <T extends BaseModel> extends AbstractHttpRequest implements CrudRepository{
    private HttpRequester httpRequester;
    public ValidatableHttpRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, Endpoint endpoint) {
        super(requestSpecification, responseSpecification, endpoint);
        this.httpRequester = new HttpRequester(requestSpecification,endpoint,responseSpecification);
    }

    @Override
    public T post(BaseModel model) {
        return (T)httpRequester.post(model).extract().as(endpoint.getResponseModel());
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
