package example.com.models.req_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositRequestModel {
    private Long accountId;
    private double amount;
    private String description;

}
