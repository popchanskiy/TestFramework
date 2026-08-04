package ui_tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import example.com.annotations.AuthorizedUser;
import example.com.annotations.Browser;
import example.com.enums.AuthorizedMode;
import example.com.enums.BrowserType;
import example.com.flows.LoginApi;
import example.com.models.AuthorizedSession;
import example.com.models.resp_models.LoginUserResponseModel;
import example.com.pages.AdminPanelPage;
import example.com.pages.UserDashBoardPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("REGRESSION")
@Tag("UI")
public class CreateAccountTest extends BaseUITest {
    @Test
    @Browser(BrowserType.CHROMIUM)
    @AuthorizedUser(mode = AuthorizedMode.CREATE_USER_AND_LOGIN)
    public void createAccountTest() {
        UserDashBoardPage userDashBoardPage = new UserDashBoardPage();
        userDashBoardPage
                .open()
                .checkDashBoardPlaceholderPresent()
                .createAccount()
                .assertAlertEquals("^✅ New Account Created! Account Number: ACC\\d+$");
    }

    @Test
    @AuthorizedUser(mode = AuthorizedMode.CREATE_USER_AND_LOGIN)
    public void createUserTest(AuthorizedSession authorizedSession) {
        LoginUserResponseModel withAdmin = LoginApi.loginWithAdmin();

        Selenide.open("/");
        Selenide.executeJavaScript(
                "window.localStorage.setItem('authToken', arguments[0]);",
                withAdmin.getToken()
        );

        AdminPanelPage adminPanelPage = new AdminPanelPage();
        SelenideElement userFromPage = adminPanelPage
                .open()
                .getConcreteUserFromPage(authorizedSession.username());

        Assertions.assertTrue(userFromPage.getText().contains(authorizedSession.username()));
    }

}
