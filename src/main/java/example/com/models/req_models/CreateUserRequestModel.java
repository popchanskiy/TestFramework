package example.com.models.req_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.com.annotations.GenerateRule;
import example.com.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestModel {
    @GenerateRule("^[A-Za-z0-9]{3,15}$")
    private String username;

    @GenerateRule("[A-Z][a-z]\\d[!@#$%^&*][A-Za-z\\d!@#$%^&]{4,8}")
    private String password;
@JsonProperty()
    private Role role;
}
