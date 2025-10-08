package apiTest;

import api.LoginService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.utils.Generators;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private static final Logger logger = LogManager.getLogger(LoginServiceTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Step("Выполнение запроса авторизации с email: {email} и паролем: {password}")
    private JsonNode performLogin(String email, String password) throws Exception {
        LoginService service = new LoginService();
        service.doRequest(email, password);

        String responseBody = service.getResponseBody();
        logger.info("Response body: {}", responseBody);
        attachResponse(responseBody);

        return mapper.readTree(responseBody);
    }

    @Attachment(value = "Response body", type = "application/json")
    private String attachResponse(String response) {
        return response;
    }

    @Test
    @DisplayName("Авторизация с некорректными данными")
    public void loginWithInvalidCredentials() throws Exception {
        String email = Generators.generateEmail();
        String password = Generators.generateRandomPassword(10);

        JsonNode json = performLogin(email, password);

        assertFalse(json.path("success").asBoolean(), "Авторизация прошла успешно с некорректными данными!");
        assertTrue(json.has("errors"), "Должны быть ошибки при некорректной авторизации");
    }

    @Test
    @DisplayName("Авторизация с логином и пустым паролем")
    public void loginWithEmptyPassword() throws Exception {
        String email = Generators.generateEmail();
        String password = "";

        JsonNode json = performLogin(email, password);

        assertFalse(json.path("success").asBoolean(), "Авторизация прошла успешно с пустым паролем!");
        assertTrue(json.has("errors"), "Должны быть ошибки при пустом пароле");
    }

    @Test
    @DisplayName("Авторизация без логина и пароля")
    public void loginWithoutCredentials() throws Exception {
        final String empty = "";
        JsonNode json = performLogin(empty, empty);

        assertFalse(json.path("success").asBoolean(), "Авторизация прошла успешно без логина и пароля!");
        assertTrue(json.has("errors"), "Должны быть ошибки при отсутствии данных");
    }

    @Test
    @Disabled("Отключен")
    @DisplayName("Успешная авторизация")
    public void successfulLogin() throws Exception {
        String email = "glebtolst1k@mail.ru";
        String password = "quasoupuness";

        JsonNode json = performLogin(email, password);

        assertEquals("text_success", json.path("success").asText(), "Логин не выполнен успешно");
    }
}
