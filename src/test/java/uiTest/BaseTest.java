package uiTest;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pages.HomePage;
import ui.utils.Driver;

public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    protected HomePage homePage;
    protected WebDriver driver;

    @BeforeEach
    @Step("Открыть сайт и закрыть баннер с cookie")
    public void openSite() {
        log.info("Инициализация страницы HomePage и открытие сайта");
        homePage = new HomePage();
        homePage.openSite().closeCookie();
    }

    @AfterEach
    @Step("Закрыть браузер после теста")
    public void tearDown() {
        log.info("Закрываем браузер");
        Driver.quit();
    }
}
