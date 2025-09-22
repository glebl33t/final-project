package uiTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ui.pages.HomePage;
import ui.utils.Driver;

public class BaseTest {

    protected HomePage homePage;
    protected WebDriver driver;

    @BeforeEach
    public void openSite() {
        homePage = new HomePage();
        homePage.openSite().closeCookie();
    }

    @AfterEach
    public void tearDown() {
        Driver.quit();
    }
}
