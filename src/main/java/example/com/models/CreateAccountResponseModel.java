package example.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAccountResponseModel extends BaseModel {
    @JsonProperty("id")
    private Long accountId;
    private String accountNumber;
    private Double balance;
    private List<String> transactions;
}
