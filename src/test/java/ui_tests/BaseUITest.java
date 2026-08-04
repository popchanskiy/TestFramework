package ui_tests;

import example.com.extensions.AuthorizedUserExtension;
import example.com.extensions.BrowserExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserExtension.class)
@ExtendWith(AuthorizedUserExtension.class)
public class BaseUITest {
    @BeforeAll
    static void setUp() {

    }


}
