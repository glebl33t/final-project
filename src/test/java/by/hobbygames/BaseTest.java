package by.hobbygames;

import by.hobbygames.pages.HomePage;
import by.hobbygames.utils.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void openSiteAndCloseBannerAndCloseCookie() {
        HomePage homePage = new HomePage();
        homePage.openSite();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }
}
