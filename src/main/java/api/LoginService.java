package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginService extends BaseService {

    private final String PATH = "/";
    private final String BODY_TEMPLATE = "login=%s&password=%s&scenario=email";
    private Response response;

    public void doRequest(String email, String password) {
        String body = String.format(BODY_TEMPLATE, email, password);

        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("accept", "application/json, text/javascript, */*; q=0.01");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("route", "account/login/modalAjax");

        response = sendPost(PATH, headers, body, queryParams);
    }

    public String getResponseBody() {
        return getBodyAsString(response);
    }
}
