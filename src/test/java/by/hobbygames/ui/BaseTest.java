package by.hobbygames.ui;

import by.hobbygames.ui.pages.HomePage;
import by.hobbygames.ui.utils.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected HomePage homePage;

    @BeforeEach
    public void openSite() {
        homePage = new HomePage();
        homePage.openSite().closeCookie();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }
}
