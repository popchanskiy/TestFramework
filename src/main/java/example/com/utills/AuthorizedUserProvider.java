package example.com.utills;

import example.com.config.AppConfig;
import example.com.enums.Role;
import example.com.flows.LoginApi;
import example.com.flows.UserApi;
import example.com.models.AuthorizedSession;
import example.com.models.req_models.CreateUserRequestModel;
import example.com.models.req_models.LoginUserRequestModel;
import example.com.models.resp_models.CreatedUserResponseModel;
import example.com.models.resp_models.LoginUserResponseModel;

public class AuthorizedUserProvider {
    private static final String ADMIN_NAME = AppConfig.get("admin.username");
    private static final String ADMIN_PASSWORD = AppConfig.get("admin.password");

    public static AuthorizedSession createAndLoginUser(Role role) {
        LoginUserResponseModel adminLogin = LoginApi.loginWithAdmin();

        CreateUserRequestModel user = DataGenerator.generate(CreateUserRequestModel.class);
        CreatedUserResponseModel createdUser = UserApi.createByAdmin(user, adminLogin.getToken(), role);

        LoginUserResponseModel userLogin = LoginApi.loginWithUser(
                new LoginUserRequestModel(user.getUsername(), user.getPassword())
        );

        return new AuthorizedSession(
                createdUser.getUserId(),
                user.getUsername(),
                user.getPassword(),
                userLogin.getToken(),
                role
        );
    }

    public static AuthorizedSession loginAdmin() {
        LoginUserResponseModel adminLogin = LoginApi.loginWithAdmin();

        return new AuthorizedSession(
                null,
                ADMIN_NAME,
                ADMIN_PASSWORD,
                adminLogin.getToken(),
                Role.ADMIN
        );
    }
}
