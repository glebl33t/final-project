package by.hobbygames;

import by.hobbygames.pages.HomePage;
import by.hobbygames.utils.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected HomePage homePage;

    @BeforeEach
    public void openSite() {
        homePage = new HomePage();
        homePage.openSite().closeCookie();
        homePage.clickPersonalLink();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }
}
