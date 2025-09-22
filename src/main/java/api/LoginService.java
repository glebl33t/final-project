package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginService extends BaseService {
    private String URL = getBASE_URL() + "/";
    private final String BODY_DEFAULT = "login=375444444444&password=421421412&scenario=email";
    private final String BODY_TEMPLATE = "login=%s&password=%s&scenario=email";
    private Response response;

    private void sandRequest(String body) {
        response = given()
                .headers(getHeaders())
                .body(body)
                .queryParams(getQueryParams())
                .when().post(URL);
    }

    public void doRequest() {
        sandRequest(BODY_DEFAULT);
    }

    public void doRequest(String email, String password) {
        sandRequest(getBody(email, password));
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("accept", "application/json, text/javascript, */*; q=0.01");
        return headers;
    }

    private Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("route", "account/login/modalAjax");
        return queryParams;
    }

    private String getBody(String email, String password) {
        return String.format(BODY_TEMPLATE, email, password);
    }

    public String getBody() {
        return response.getBody().asPrettyString();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}
