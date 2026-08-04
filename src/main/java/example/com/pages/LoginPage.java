package example.com.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage<LoginPage> {
    private final SelenideElement logInButton =  $("button");

    @Override
    public String getUrl() {
        return "/";
    }

    public LoginPage login(String username, String password) {
        userNameInput.setValue(username);
        userPasswordInput.setValue(password);
        logInButton.click();
        return this;
    }

}
