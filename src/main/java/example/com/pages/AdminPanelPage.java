package example.com.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AdminPanelPage extends BasePage<AdminPanelPage> {
    private SelenideElement adminPanelText = $(Selectors.byText("Admin Panel"));
    private SelenideElement allUsersTable = $(Selectors.byTagAndText("h2", "All Users"));

    @Override
    public String getUrl() {
        return "/admin";
    }

    public AdminPanelPage CheckAdminPanelPresent() {
        adminPanelText.shouldBe(Condition.visible);
        return this;
    }

    public ElementsCollection getAllUsersTable() {
        return allUsersTable
                .shouldBe(Condition.visible)
                .parent()
                .findAll("li");

    }

    public SelenideElement getConcreteUserFromPage(String username) {
        ElementsCollection allUsersTable1 = getAllUsersTable();
        return getAllUsersTable().findBy(Condition.text(username)).shouldBe(Condition.visible);
    }


}
