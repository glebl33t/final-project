package by.hobbygames.ui.pages;

import by.hobbygames.ui.utils.WebDriver;

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

    public void clickInputSubmit() {
        WebDriver.clickElement(INPUT_SUBMIT);
    }

    public String getTextErrorPassword() {
        try {
            return WebDriver.getTextFromElement(ERROR_PASSWORD_TEXT);
        } catch (Exception e) {
            return "";
        }
    }

    public String getTextErrorLogin() {
        try {
            return WebDriver.getTextFromElement(ERROR_LOGIN_TEXT);
        } catch (Exception e) {
            return "";
        }
    }

    public void fillLoginFormStep(String login, String password) {
        sendKeysUserLogin(login);
        sendKeysUserPassword(password);
        clickInputSubmit();
    }

    private void sendKeysUserPassword(String password) {
        WebDriver.sendKeysToElement(USER_PASSWORD, password);
    }

    private void sendKeysUserLogin(String login) {
        WebDriver.sendKeysToElement(USER_LOGIN, login);
    }
}