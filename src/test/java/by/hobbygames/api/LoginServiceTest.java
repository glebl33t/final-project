import by.hobbygames.api.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        service.doRequest("375444444444", "qwertyqwerty");

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }

    @Test
    @DisplayName("test #3")
    public void testLogin3() {
        LoginService service = new LoginService();
        service.doRequest("login=&password=&scenario=email");

        System.out.println(service.getBody());
        Assertions.assertEquals(200, service.getStatusCode());
    }
}
