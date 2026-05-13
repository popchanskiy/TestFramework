package example.com.models;

import example.com.annotations.GenerateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestModel extends BaseModel {
    @GenerateRule("^[A-Za-z0-9]{3,15}$")
    private String username;

    @GenerateRule("^[A-Z]{3}[a-z]{4}[0-9]{3}[$%&*()]{2}$")
    private String password;

    private Role role;
}
