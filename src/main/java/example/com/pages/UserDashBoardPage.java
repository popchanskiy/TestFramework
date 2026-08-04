package example.com.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class UserDashBoardPage extends BasePage<UserDashBoardPage> {
    private SelenideElement dashBoardPanelPlaceholder = $(Selectors.byText("User Dashboard"));
    private SelenideElement createAccountButton = $(Selectors.byText("➕ Create New Account"));

    @Override
    public String getUrl() {
        return "/dashboard";
    }

    public UserDashBoardPage checkDashBoardPlaceholderPresent() {
        dashBoardPanelPlaceholder.shouldBe(Condition.visible);
        return this;
    }

    public UserDashBoardPage createAccount() {
        createAccountButton.click();
        return this;
    }


}
