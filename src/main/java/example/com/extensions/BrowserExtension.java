package example.com.extensions;

import com.codeborne.selenide.Configuration;
import example.com.annotations.Browser;
import example.com.config.AppConfig;
import example.com.enums.BrowserType;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BrowserExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        BrowserType browserType = resolveBrowser(context);
        Configuration.remote = AppConfig.get("remote");
        switch (browserType) {
            case CHROME, CHROMIUM -> Configuration.browser = "chrome";
            case FIREFOX -> Configuration.browser = "firefox";
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
        Configuration.baseUrl = AppConfig.get("baseUrl");
        Configuration.browserSize = AppConfig.get("browserSize");
    }

    private BrowserType resolveBrowser(ExtensionContext context) {
        Browser browserOnMethod = context.getRequiredTestMethod().getAnnotation(Browser.class);
        if (browserOnMethod != null) {
            return browserOnMethod.value();
        }

        Browser browserOnClass = context.getRequiredTestClass().getAnnotation(Browser.class);
        if (browserOnClass != null) {
            return browserOnClass.value();
        }
//default
        return BrowserType.getBrowserTypeByString(AppConfig.get("browser"));
    }
}
