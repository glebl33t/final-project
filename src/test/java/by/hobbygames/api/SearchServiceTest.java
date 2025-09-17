import by.hobbygames.api.SearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {

    @Test
    @DisplayName("Поиск по существующему товару — 'Монополия'")
    public void testSearchForExistingProduct() {
        SearchService service = new SearchService();
        service.doRequest("Монополия");

        int statusCode = service.getResponseStatusCode();
        String responseBody = service.getResponseBody();

        assertEquals(200, statusCode, "Статус-код должен быть 200");
        assertTrue(responseBody.contains("Монополия"), "Ответ должен содержать 'Монополия'");
    }

    @Test
    @DisplayName("Поиск по несуществующему товару — '123'")
    public void testSearchForNonExistingProduct() {
        SearchService service = new SearchService();
        service.doRequest("123");

        int statusCode = service.getResponseStatusCode();
        String responseBody = service.getResponseBody();

        assertEquals(200, statusCode, "Статус-код должен быть 200");
        assertTrue(responseBody.contains("ничего не найдено") ||
                responseBody.contains("0 товаров") ||
                responseBody.toLowerCase().contains("ничего"), "Ожидается сообщение об отсутствии результатов");
    }

    @Test
    @DisplayName("Поиск с пустой строкой")
    public void testSearchWithEmptyString() {
        SearchService service = new SearchService();
        service.doRequest("");

        int statusCode = service.getResponseStatusCode();
        String responseBody = service.getResponseBody();

        assertEquals(200, statusCode, "Статус-код должен быть 200");
        assertFalse(responseBody.isEmpty(), "Ответ не должен быть пустым");
    }
}