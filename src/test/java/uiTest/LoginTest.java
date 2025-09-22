package uiTest;

import org.junit.jupiter.api.*;
import ui.pages.LoginPage;

import static ui.utils.Generators.generateEmail;
import static ui.utils.Generators.generateRandomPassword;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void setupPages() {
        loginPage = new LoginPage();
        homePage.clickPersonalLink();
    }

    @Test
    @DisplayName("Авторизация со всеми параметрами")
    public void loginTestWithFullArgs() {
        String email = generateEmail();
        String password = generateRandomPassword(10);
        loginPage.fillLoginFormStep(email, password);

        String loginError = loginPage.getTextErrorLogin();
        String passwordError = loginPage.getTextErrorPassword();

        Assertions.assertTrue(loginError.equals("Неверный телефон/e-mail") || loginError.equals("Введённые данные некорректны"),
                "Ошибка логина должна быть либо 'Неверный телефон/e-mail', либо 'Введённые данные некорректны', но была: " + loginError
        );
        Assertions.assertTrue(
                passwordError.isEmpty() || passwordError.equals("Неверный пароль"),
                "Ошибка пароля должна быть пустой или 'Введите пароль', но была: " + passwordError
        );

    }

    @Test
    @DisplayName("Авторизация с логином и пустым паролем")
    public void loginTestWithoutPassword() {
        String email = generateEmail();
        String password = "";
        loginPage.fillLoginFormStep(email, password);

        String loginError = loginPage.getTextErrorLogin();
        String passwordError = loginPage.getTextErrorPassword();

        Assertions.assertTrue(loginError.equals("Неверный телефон/e-mail") || loginError.equals("Введённые данные некорректны"),
                "Ошибка логина должна быть либо 'Неверный телефон/e-mail', либо 'Введённые данные некорректны', но была: " + loginError
        );
        Assertions.assertTrue(
                passwordError.isEmpty() || passwordError.equals("Введите пароль"),
                "Ошибка пароля должна быть пустой или 'Введите пароль', но была: " + passwordError
        );
    }

    @Test
    @DisplayName("Авторизация без логина и пароля")
    public void loginTestWithoutArgs() {
        String email = "";
        String password = "";
        loginPage.fillLoginFormStep(email, password);

        String loginError = loginPage.getTextErrorLogin();
        String passwordError = loginPage.getTextErrorPassword();

        Assertions.assertTrue(loginError.equals("Неверный телефон/e-mail") || loginError.equals("Введённые данные некорректны") || loginError.equals("Введите телефон или электронную почту"),
                "Ошибка логина должна быть либо 'Неверный телефон/e-mail', либо 'Введённые данные некорректны', но была: " + loginError
        );
        Assertions.assertTrue(
                passwordError.isEmpty() || passwordError.equals("Введите пароль"),
                "Ошибка пароля должна быть пустой или 'Введите пароль', но была: " + passwordError
        );
    }

    @Disabled("Отключен")
    @Test
    @DisplayName("Успешная авторизация")
    public void successfulLogin() {
        String email = "glebtolst1k@mail.ru";
        String password = "G2x4fOft";

        loginPage.fillLoginFormStep(email, password);

        loginPage.clickPersonalAccount();

        Assertions.assertEquals("Толстик Глеб", loginPage.getTextNamePersonalAccount());
    }
}
