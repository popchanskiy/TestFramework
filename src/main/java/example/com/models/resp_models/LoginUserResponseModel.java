package example.com.models.resp_models;

import example.com.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserResponseModel {
    private String username;
    private Role role;
    private String token;
}
