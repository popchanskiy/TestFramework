package example.com.flows;

import example.com.authorization.Authorization;
import example.com.clients.RestAssuredApiClient;
import example.com.enums.EndpointList;
import example.com.models.req_models.TransferRequestModel;
import example.com.models.resp_models.TransferResponseModel;
import example.com.requests.ApiRequest;
import example.com.specs.RequestSpec;
import example.com.specs.ResponseSpec;

import java.util.Map;

public class TransferApi {
    public static TransferResponseModel transfer(TransferRequestModel transferRequestModel, String token) {
        ApiRequest<TransferRequestModel> request = new ApiRequest<>(
                EndpointList.TRANSFER.getEndpoint(),
                transferRequestModel,
                Map.of(),
                new Authorization(Map.of("Authorization", token))
        );

        return RestAssuredApiClient.executeAs(
                RequestSpec.getDefaultRequestSpecification(),
                ResponseSpec.OK200ResponseSpecification(),
                request,
                TransferResponseModel.class
        );
    }
}
