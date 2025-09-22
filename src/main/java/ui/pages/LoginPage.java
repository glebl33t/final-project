package ui.pages;

import ui.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final String USER_LOGIN = "//input[@name='login']";
    private final String USER_PASSWORD = "//input[@name='password']";
    private final String INPUT_SUBMIT = "//input[@type='submit']";
    private final String ERROR_PASSWORD_TEXT = "//label[@class='password-group']//div[@class='error']";
    private final String ERROR_LOGIN_TEXT = "//label[@data-scenario='login']//div[@class='error']";
    private final String PERSONAL_ACCOUNT_NAME_TEXT = "//div[@class='pa-user-name']";
    private final String BUTTON_PERSONAL_ACCOUNT = "//div[@class='user-profile-icon']";
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public void clickInputSubmit() {
        Driver.clickElement(INPUT_SUBMIT);
    }

    public String getTextErrorPassword() {
        try {
            return Driver.getTextFromElement(ERROR_PASSWORD_TEXT);
        } catch (Exception e) {
            //logger
            return "";
        }
    }

    public String getTextErrorLogin() {
        try {
            return Driver.getTextFromElement(ERROR_LOGIN_TEXT);
        } catch (Exception e) {
            //logger
            return "";
        }
    }

    public void fillLoginFormStep(String login, String password) {
        sendKeysUserLogin(login);
        sendKeysUserPassword(password);
        clickInputSubmit();
    }

    private void sendKeysUserPassword(String password) {
        Driver.sendKeysToElement(USER_PASSWORD, password);
    }

    private void sendKeysUserLogin(String login) {
        Driver.sendKeysToElement(USER_LOGIN, login);
    }

    public void clickPersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='global-loader' and contains(@style, 'display: block')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_PERSONAL_ACCOUNT))).click();
    }

    public String getTextNamePersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PERSONAL_ACCOUNT_NAME_TEXT))).getText();
    }
}