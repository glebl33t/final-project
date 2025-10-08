package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.Driver;

import java.time.Duration;

public class LoginPage {
    private final String USER_LOGIN = "//input[@name='login']";
    private final String USER_PASSWORD = "//input[@name='password']";
    private final String INPUT_SUBMIT = "//input[@type='submit']";
    private final String ERROR_PASSWORD_TEXT = "//label[@class='password-group']//div[@class='error']";
    private final String ERROR_LOGIN_TEXT = "//label[@data-scenario='login']//div[@class='error']";
    private final String PERSONAL_ACCOUNT_NAME_TEXT = "//div[@class='pa-user-name']";
    private final String BUTTON_PERSONAL_ACCOUNT = "//div[@class='user-profile-icon']";

    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public void clickInputSubmit() {
        logger.info("Нажатие на кнопку Submit");
        Driver.click(INPUT_SUBMIT);
    }

    public String getTextErrorPassword() {
        try {
            String text = Driver.getText(ERROR_PASSWORD_TEXT);
            logger.info("Ошибка пароля получена: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Не удалось получить текст ошибки пароля", e);
            return "";
        }
    }

    public String getTextErrorLogin() {
        try {
            String text = Driver.getText(ERROR_LOGIN_TEXT);
            logger.info("Ошибка логина получена: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Не удалось получить текст ошибки логина", e);
            return "";
        }
    }

    public void fillLoginFormStep(String login, String password) {
        logger.info("Заполнение формы логина: login={}, password={}", login, "*скрыт*");
        sendKeysUserLogin(login);
        sendKeysUserPassword(password);
        clickInputSubmit();
    }

    private void sendKeysUserPassword(String password) {
        logger.info("Ввод пароля");
        Driver.sandKeys(USER_PASSWORD, password);
    }

    private void sendKeysUserLogin(String login) {
        logger.info("Ввод логина: {}", login);
        Driver.sandKeys(USER_LOGIN, login);
    }

    public void clickPersonalAccount() {
        logger.info("Нажатие на иконку личного кабинета");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='global-loader' and contains(@style, 'display: block')]")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("vex-overlay")));

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_PERSONAL_ACCOUNT)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public String getTextNamePersonalAccount() {
        logger.info("Получение имени пользователя из личного кабинета");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PERSONAL_ACCOUNT_NAME_TEXT))).getText();
        logger.info("Имя пользователя получено: {}", name);
        return name;
    }
}
