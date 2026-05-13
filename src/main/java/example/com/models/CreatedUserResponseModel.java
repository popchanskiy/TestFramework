package example.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedUserResponseModel extends BaseModel{
    @JsonProperty("id")
    Long userId;
    @JsonProperty("username")
    String userName;
    String password;
    String name;
    Role role;
    List<String> accounts;
}
