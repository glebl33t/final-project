package by.hobbygames.api;

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
    @DisplayName("f2")
    public void testLogin2() {
        LoginService service = new LoginService();
        service.doRequest("test@test.by","12354");

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }

    @Test
    @DisplayName("f3")
    public void testLogin3() {
        LoginService service = new LoginService();
        service.doRequest("login=test@admin.com&password=qwerty&scenario=email");

        Assertions.assertEquals(200, service.getStatusCode());
    }
}
