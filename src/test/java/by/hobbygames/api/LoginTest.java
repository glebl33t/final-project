package by.hobbygames.api;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    @DisplayName("f1")
    public void testLogin() {
        LoginService service = new LoginService();
        service.doRequest();

        Assertions.assertEquals(200, service.getStatusCode());
    }

    @Test
    void checkLoginSuccessField() {
        LoginService service = new LoginService();
        service.doRequest("375444444444", "неверныйПароль");

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }

    @Test
    @DisplayName("f3")
    public void testLogin3() {
        LoginService service = new LoginService();
        service.doRequest("login=&password=&scenario=email");

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }
}
