package uiTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pages.LoginPage;

import static ui.utils.Generators.generateEmail;
import static ui.utils.Generators.generateRandomPassword;

@Epic("UI Tests")
@Feature("Login Tests")
public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    private LoginPage loginPage;

    @BeforeEach
    public void setupPages() {
        log.info("Инициализация страниц для теста авторизации");
        loginPage = new LoginPage();
        homePage.clickPersonalLink();
    }

    @Test
    @Story("Авторизация со всеми параметрами")
    @DisplayName("Авторизация со всеми параметрами")
    public void loginTestWithFullArgs() {
        String email = generateEmail();
        String password = generateRandomPassword(10);
        fillLoginForm(email, password);

        checkLoginAndPasswordErrors();
    }

    @Test
    @Story("Авторизация с логином и пустым паролем")
    @DisplayName("Авторизация с логином и пустым паролем")
    public void loginTestWithoutPassword() {
        String email = generateEmail();
        String password = "";
        fillLoginForm(email, password);

        checkLoginAndPasswordErrors();
    }

    @Test
    @Story("Авторизация без логина и пароля")
    @DisplayName("Авторизация без логина и пароля")
    public void loginTestWithoutArgs() {
        String email = "";
        String password = "";
        fillLoginForm(email, password);

        checkLoginAndPasswordErrors();
    }

//    @Disabled("Временно отключен из-за блокировки аккаунта")
    @Test
    @Story("Успешная авторизация")
    @DisplayName("Успешная авторизация")
    public void successfulLogin() {
        String email = "glebtolst1k@mail.ru";
        String password = "G2x4fOft";
        fillLoginForm(email, password);

        loginPage.clickPersonalAccount();
        Assertions.assertEquals("Толстик Глеб", loginPage.getTextNamePersonalAccount());
        log.info("Успешная авторизация прошла для пользователя {}", email);
    }

    @Step("Заполнить форму логина с email: {email} и password: {password}")
    private void fillLoginForm(String email, String password) {
        log.info("Заполняем форму логина: email={}, password={}", email, password);
        loginPage.fillLoginFormStep(email, password);
    }

    @Step("Проверяем ошибки логина и пароля")
    private void checkLoginAndPasswordErrors() {
        String loginError = loginPage.getTextErrorLogin();
        String passwordError = loginPage.getTextErrorPassword();

        log.info("Ошибка логина: {}, Ошибка пароля: {}", loginError, passwordError);

        Assertions.assertTrue(
                loginError.equals("Неверный телефон/e-mail") ||
                        loginError.equals("Введённые данные некорректны") ||
                        loginError.equals("Введите телефон или электронную почту"),
                "Ошибка логина некорректна: " + loginError
        );

        Assertions.assertTrue(
                passwordError.isEmpty() || passwordError.equals("Неверный пароль") || passwordError.equals("Введите пароль"),
                "Ошибка пароля некорректна: " + passwordError
        );
    }
}
