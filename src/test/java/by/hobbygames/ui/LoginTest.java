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
        homePage.clickPersonalLink();
    }

    private void loginAndCheckErrors(String login, String password) {
        loginPage.fillLoginFormStep(login, password);
        String loginError = loginPage.getTextErrorLogin();
        String passwordError = loginPage.getTextErrorPassword();

        if (login.isEmpty()) {
            Assertions.assertTrue(loginError.contains("Введите телефон")
                            || loginError.contains("Введите электронную почту")
                            || loginError.contains("Введённые данные некорректны"),
                    "Ожидалась ошибка при пустом логине, но было: " + loginError);
        }

        if (password.isEmpty()) {
            if (!passwordError.isEmpty()) {
                Assertions.assertTrue(passwordError.contains("Введите пароль"), "Ошибка под паролем неверная: " + passwordError);
            }
        } else {
            if (!passwordError.isEmpty()) {
                Assertions.assertTrue(passwordError.contains("Неверный пароль"), "Ошибка под паролем неверная: " + passwordError);
            }
        }
    }

    @Test
    @DisplayName("Авторизация со всеми параметрами")
    public void loginTestWithFullArgs() {
        loginAndCheckErrors("+375447774466", "123445123");
    }

    @Test
    @DisplayName("Авторизация с логином, но без пароля")
    public void loginTestWithoutPassword() {
        loginAndCheckErrors("+37544777436", "");
    }

    @Test
    @DisplayName("Авторизация с логином, но короткий пароль")
    public void loginTestWithShortPassword() {
        loginAndCheckErrors("+375447774456", "12");
    }

    @Test
    @DisplayName("Авторизация без логина и пароля")
    public void loginTestWithEmptyArgs() {
        loginAndCheckErrors("", "");
    }
}