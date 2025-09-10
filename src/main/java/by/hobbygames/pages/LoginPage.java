package by.hobbygames.pages;

import by.hobbygames.utils.WebDriver;

public class LoginPage {
    private final String USER_LOGIN = "//input[@name='login']";
    private final String USER_PASSWORD = "//input[@name='password']";
    private final String INPUT_SUBMIT = "//input[@type='submit']";
    private final String ERROR_PASSWORD_TEXT = "//label[@class='password-group']//div[@class='error']";
    private final String ERROR_LOGIN_TEXT = "//label[@data-scenario='login']//div[@class='error']";
    private org.openqa.selenium.WebDriver driver;

    public LoginPage() {
        driver = WebDriver.getDriver();

    }

    public void clickInputLogin() {
        WebDriver.clickElement(USER_LOGIN);
    }

    public void clickInputPassword() {
        WebDriver.clickElement(USER_PASSWORD);
    }

    public void clickInputSubmit() {
        WebDriver.clickElement(INPUT_SUBMIT);
    }

    public String getTextErrorPassword() {
        return WebDriver.getTextFromElement(ERROR_PASSWORD_TEXT);
    }

    public String getTextErrorLogin() {
        return WebDriver.getTextFromElement(ERROR_LOGIN_TEXT);
    }

    public void fillLoginFormStep(String login, String password) {
        sendKeysUserLogin(login);
        sendKeysUserPassword(password);
        clickInputSubmit();
    }

    private void clickButtonLogin() {
    }

    private void sendKeysUserPassword(String password) {
        WebDriver.sandKeysToElement(USER_PASSWORD, password);
    }

    private void sendKeysUserLogin(String login) {
        WebDriver.sandKeysToElement(USER_LOGIN, login);
    }
}
