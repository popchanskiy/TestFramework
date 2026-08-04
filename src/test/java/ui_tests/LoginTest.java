package ui_tests;

import example.com.annotations.AuthorizedUser;
import example.com.annotations.Browser;
import example.com.config.AppConfig;
import example.com.enums.AuthorizedMode;
import example.com.enums.BrowserType;
import example.com.models.AuthorizedSession;
import example.com.models.req_models.LoginUserRequestModel;
import example.com.pages.AdminPanelPage;
import example.com.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Slf4j
@Browser(BrowserType.CHROME)
@Tag("REGRESSION")
@Tag("UI")
public class LoginTest extends BaseUITest {

    @Test
    @Browser(BrowserType.FIREFOX)
    @AuthorizedUser(mode = AuthorizedMode.LOGIN_AS_ADMIN)
    void loginAdminTest() {
        LoginUserRequestModel adminUser = new LoginUserRequestModel(AppConfig.get("admin.username"), AppConfig.get("admin.password"));
        LoginPage loginPage = new LoginPage();

        loginPage.open()
                .login(adminUser.getUsername(), adminUser.getPassword())
                .getPage(AdminPanelPage.class)
                .CheckAdminPanelPresent();
    }

    @Test
    @AuthorizedUser(mode = AuthorizedMode.CREATE_USER_AND_LOGIN)
    void loginUserTest(AuthorizedSession authorizedSession) {
        LoginPage loginPage = new LoginPage();
        loginPage.open().login(authorizedSession.username(), authorizedSession.password());
    }

}
