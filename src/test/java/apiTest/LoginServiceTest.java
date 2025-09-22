package apiTest;

import api.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.utils.Generators;

public class LoginServiceTest {

    @Test
    @DisplayName("test #1")
    public void testLogin() {
        LoginService service = new LoginService();
        service.doRequest();

        Assertions.assertEquals(200, service.getStatusCode());
    }

    @Test
    @DisplayName("test #2")
    void checkLoginSuccessField() {
        LoginService service = new LoginService();
        String email = Generators.generateEmail();
        String password = Generators.generateRandomPassword(10);
        service.doRequest(email, password);

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }

    @Test
    @DisplayName("test #3")
    public void testLogin3() {
        LoginService service = new LoginService();
        String email = Generators.generateEmail();
        String password = "";
        service.doRequest(email, password);

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }
}
