package apiTest;

import api.LoginService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ui.utils.Generators;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private static final Logger logger = LogManager.getLogger(LoginServiceTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Авторизация с некорректными данными")
    public void loginWithInvalidCredentials() throws Exception {
        LoginService service = new LoginService();
        String email = Generators.generateEmail();
        String password = Generators.generateRandomPassword(10);
        service.doRequest(email, password);

        String responseBody = service.getResponseBody();
        logger.info("Response body: {}", responseBody);

        JsonNode json = mapper.readTree(responseBody);

        assertFalse(json.path("success").asBoolean(), "Авторизация прошла успешно с некорректными данными!");
        assertTrue(json.has("errors"), "Должны быть ошибки при некорректной авторизации");
    }

    @Test
    @DisplayName("Авторизация с логином и пустым паролем")
    public void loginWithEmptyPassword() throws Exception {
        LoginService service = new LoginService();
        String email = Generators.generateEmail();
        String password = "";
        service.doRequest(email, password);

        String responseBody = service.getResponseBody();
        logger.info("Response body: {}", responseBody);

        JsonNode json = mapper.readTree(responseBody);

        assertFalse(json.path("success").asBoolean(), "Авторизация прошла успешно с пустым паролем!");
        assertTrue(json.has("errors"), "Должны быть ошибки при пустом пароле");
    }

    @Test
    @DisplayName("Авторизация без логина и пароля")
    public void loginWithoutCredentials() throws Exception {
        LoginService service = new LoginService();
        service.doRequest("", "");

        String responseBody = service.getResponseBody();
        logger.info("Response body: {}", responseBody);

        JsonNode json = mapper.readTree(responseBody);

        assertFalse(json.path("success").asBoolean(), "Авторизация прошла успешно без логина и пароля!");
        assertTrue(json.has("errors"), "Должны быть ошибки при отсутствии данных");
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void successfulLogin() throws Exception {
        LoginService service = new LoginService();
        String email = "glebtolst1k@mail.ru";
        String password = "G2x4fOft";
        service.doRequest(email, password);

        String responseBody = service.getResponseBody();
        logger.info("Response body: {}", responseBody);

        JsonNode json = mapper.readTree(responseBody);

        assertEquals("text_success", json.path("success").asText(), "Логин не выполнен успешно");
    }
}
