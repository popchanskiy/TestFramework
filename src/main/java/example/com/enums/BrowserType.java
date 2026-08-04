package example.com.enums;

public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    CHROMIUM("chromium");

    private final String browserName;

    BrowserType(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static BrowserType getBrowserTypeByString(String value) {
        for (BrowserType type : values()) {
            if (type.name().equalsIgnoreCase(value)
                    || type.browserName.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported browser: " + value);
    }

}
