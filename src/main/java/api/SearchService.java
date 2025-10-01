package api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchService extends BaseService {
    private static final Logger logger = LogManager.getLogger(SearchService.class);

    private final String URL = getBASE_URL() + "/catalog/search";
    private Response response;

    public void doRequest(String search) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("keyword", search);

        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");

        logger.info("Отправляем GET запрос на URL: {}", URL);
        logger.info("Параметры запроса: {}", queryParams);
        logger.info("Заголовки запроса: {}", headers);

        response = given().queryParams(queryParams)
                .headers(headers)
                .when()
                .get(URL)
                .then().extract().response();

        logger.info("Получен ответ со статусом: {}", response.getStatusCode());
        logger.debug("Тело ответа:\n{}", response.getBody().asPrettyString());
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asPrettyString();
    }
}
