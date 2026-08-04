package example.com.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class BasePage<T extends BasePage> {
    protected final SelenideElement userNameInput = $(Selectors.byAttribute("placeholder","Username"));
    protected final SelenideElement userPasswordInput = $(Selectors.byAttribute("placeholder","Password"));
    public abstract String getUrl();

    public T open() {
        return Selenide.open(getUrl(),(Class<T>)this.getClass());
    }

    public <PAGE extends BasePage> PAGE getPage(Class<PAGE> pageClass) {
        return Selenide.page(pageClass);
    }

    public T assertAlertEquals(String regex) {
        Alert alert = switchTo().alert();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(alert.getText());
        alert.accept();
        return (T) this;
    }

}
