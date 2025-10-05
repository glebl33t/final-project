package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class SearchService extends BaseService {

    private final String PATH = "/catalog/search";
    private Response response;

    public void doRequest(String search) {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("keyword", search);

        response = sendGet(PATH, headers, queryParams);
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return getBodyAsString(response);
    }
}
