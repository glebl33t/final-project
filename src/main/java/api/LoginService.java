package api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginService extends BaseService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private final String URL = getBASE_URL() + "/";
    private final String BODY_DEFAULT = "login=375444444444&password=421421412&scenario=email";
    private final String BODY_TEMPLATE = "login=%s&password=%s&scenario=email";

    private Response response;

    public void doRequest() {
        doRequestWithBody(BODY_DEFAULT);
    }

    public void doRequest(String email, String password) {
        String body = String.format(BODY_TEMPLATE, email, password);
        doRequestWithBody(body);
    }

    private void doRequestWithBody(String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("accept", "application/json, text/javascript, */*; q=0.01");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("route", "account/login/modalAjax");

        logger.info("Отправляем POST-запрос на URL: {}", URL);
        logger.info("Тело запроса: {}", body);
        logger.info("Заголовки: {}", headers);
        logger.info("Параметры запроса: {}", queryParams);

        response = given()
                .headers(headers)
                .body(body)
                .queryParams(queryParams)
                .when()
                .post(URL);

        logger.info("Получен ответ с кодом: {}", response.getStatusCode());
        logger.info("Тело ответа: {}", response.getBody().asPrettyString());
    }

    public String getResponseBody() {
        return response.getBody().asPrettyString();
    }
}
