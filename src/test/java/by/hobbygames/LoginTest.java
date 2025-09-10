package by.hobbygames;

import by.hobbygames.pages.HomePage;
import by.hobbygames.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Авторизация со всеми параметрами")
    public void loginTestWithFullArgs() {
        HomePage homePage = new HomePage();
        homePage.clickPersonalLink();

        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginFormStep("+375447774466","123445123");

        Assertions.assertEquals("Неверный телефон/e-mail",loginPage.getTextErrorLogin());
        Assertions.assertEquals("Неверный пароль",loginPage.getTextErrorPassword());
    }

    @Test
    @DisplayName("Авторизация с логином, но без пароля")
    public void loginTestWithoutPassword() {
        HomePage homePage = new HomePage();
        homePage.clickPersonalLink();

        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginFormStep("+37544777436","");

        Assertions.assertEquals("Неверный телефон/e-mail",loginPage.getTextErrorLogin());
        Assertions.assertEquals("Введите пароль",loginPage.getTextErrorPassword());
    }

    @Test
    @DisplayName("Авторизация с логином, но короткий пароль")
    public void loginTestWithShortPassword() {
        HomePage homePage = new HomePage();
        homePage.clickPersonalLink();

        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginFormStep("+375447774456","12");

        Assertions.assertEquals("Неверный телефон/e-mail",loginPage.getTextErrorLogin());
        Assertions.assertEquals("Неверный пароль",loginPage.getTextErrorPassword());
    }

    @Test
    @DisplayName("Авторизация без логина и пароля")
    public void loginTestWithZeroArgs() {
        HomePage homePage = new HomePage();
        homePage.clickPersonalLink();

        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginFormStep("","");

        Assertions.assertEquals("Введите телефон или электронную почту",loginPage.getTextErrorLogin());
        Assertions.assertEquals("Введите пароль",loginPage.getTextErrorPassword());
    }
}
