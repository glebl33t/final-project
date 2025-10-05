package api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseService {

    protected static final String BASE_URL = "https://hobbygames.by";
    protected static final Logger logger = LogManager.getLogger(BaseService.class);

    protected String getUrl(String path) {
        return BASE_URL + path;
    }

    protected Response sendGet(String path, Map<String, String> headers, Map<String, String> queryParams) {
        logger.info("Отправляем GET-запрос на URL: {}", getUrl(path));
        logger.info("Параметры: {}", queryParams);
        logger.info("Заголовки: {}", headers);

        Response response = given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(getUrl(path))
                .then()
                .extract().response();

        logger.info("Ответ: код {} \n{}", response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    protected Response sendPost(String path, Map<String, String> headers, String body, Map<String, String> queryParams) {
        logger.info("Отправляем POST-запрос на URL: {}", getUrl(path));
        logger.info("Тело запроса: {}", body);
        logger.info("Заголовки: {}", headers);
        logger.info("Параметры: {}", queryParams);

        Response response = given()
                .headers(headers)
                .body(body)
                .queryParams(queryParams)
                .when()
                .post(getUrl(path))
                .then()
                .extract().response();

        logger.info("Ответ: код {} \n{}", response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    protected String getBodyAsString(Response response) {
        return new String(response.getBody().asByteArray(), StandardCharsets.UTF_8);
    }
}
