package example.com.models.req_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferRequestModel {
    private Long senderAccountId;
    private Long receiverAccountId;
    private double amount;
    private String description;
}
