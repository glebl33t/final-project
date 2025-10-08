package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import ui.utils.Driver;

public class HomePage {
    private final String URL = "https://hobbygames.by/";
    private final String PERSONAL_LINK = "//a[@href='/login']";
    private final String CLOSE_COOKIE = "//div[@class='cookie-banner__button']//button";
    private static final Logger logger = LogManager.getLogger();
    private WebDriver driver;

    public HomePage() {
        driver = Driver.getDriver();
    }

    public HomePage openSite() {
        Driver.getDriver().get(URL);
        return this;
    }

    public HomePage closeCookie() {
        try {
            WebElement cookieButton = Driver.getDriver().findElement(By.xpath(CLOSE_COOKIE));

            if (cookieButton.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cookieButton);
                cookieButton.click();
                logger.info("Cookie-баннер был найден и закрыт.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("Cookie-баннер не появился. Продолжаем тест.");
        } catch (ElementClickInterceptedException e) {
            logger.warn("Не удалось кликнуть по баннеру — элемент перекрыт.");
        } catch (Exception e) {
            logger.error("Ошибка при попытке закрыть cookie-баннер: {}", e.getMessage());
        }
        return this;
    }

    public void clickPersonalLink() {
        Driver.click(PERSONAL_LINK);
    }
}
