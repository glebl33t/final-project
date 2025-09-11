package by.hobbygames.ui;

import by.hobbygames.ui.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void setupPages() {
        loginPage = new LoginPage();
    }

    private void loginAndCheckErrors(String login, String password, String expectedLoginError, String expectedPasswordError) {
        loginPage.fillLoginFormStep(login, password);
        Assertions.assertEquals(expectedLoginError, loginPage.getTextErrorLogin(), "Login errors");
        Assertions.assertEquals(expectedPasswordError, loginPage.getTextErrorPassword(), "Password errors");
    }

    @Test
    @DisplayName("Авторизация со всеми параметрами")
    public void loginTestWithFullArgs() {
        loginAndCheckErrors("+375447774466", "123445123", "Неверный телефон/e-mail", "Неверный пароль");
    }

    @Test
    @DisplayName("Авторизация с логином, но без пароля")
    public void loginTestWithoutPassword() {
        loginAndCheckErrors("+37544777436", "", "Неверный телефон/e-mail", "Введите пароль");
    }

    @Test
    @DisplayName("Авторизация с логином, но короткий пароль")
    public void loginTestWithShortPassword() {
        loginAndCheckErrors("+375447774456", "12", "Неверный телефон/e-mail", "Неверный пароль");
    }

    @Test
    @DisplayName("Авторизация без логина и пароля")
    public void loginTestWithEmptyArgs() {
        loginAndCheckErrors("", "", "Введите телефон или электронную почту", "Введите пароль");
    }
}