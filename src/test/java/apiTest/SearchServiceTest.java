package apiTest;

import api.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.utils.Generators;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {
    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("Поиск по существующему товару — 'Монополия'")
    public void testSearchForExistingProduct() {

        logger.info("Выполняем тест 'Поиск по существующему товару — 'Монополия' '");
        SearchService service = new SearchService();
        String searchName = "Монополия";
        service.doRequest(searchName);

        int statusCode = service.getResponseStatusCode();
        String responseBody = service.getResponseBody();
        Document doc = Jsoup.parse(responseBody);

        String productCardNameText = "//div[@class='product-card-title']//a";
        Elements searchCardTitles = doc.selectXpath(productCardNameText);
        Element card1 = searchCardTitles.get(0);

        String actual = card1.text();
        actual = actual.substring(0, 9);

        logger.info(("Ищем в поисковой строке : " + searchName));
        Assertions.assertEquals(searchName, actual);
        assertEquals(200, statusCode, "Статус-код должен быть 200");

        // Перенести в SearchService
    }

    @Test
    @DisplayName("Поиск по несуществующему товару")
    public void testSearchForNonExistingProduct() {
        SearchService service = new SearchService();
        String search = Generators.generateRandomString(6);
        service.doRequest(search);

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
        String search = "";
        service.doRequest(search);

        int statusCode = service.getResponseStatusCode();
        String responseBody = service.getResponseBody();

        assertEquals(200, statusCode, "Статус-код должен быть 200");
        assertFalse(responseBody.isEmpty(), "Ответ не должен быть пустым");
    }
}