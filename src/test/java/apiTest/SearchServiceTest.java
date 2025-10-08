package apiTest;

import api.SearchService;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("API тесты")
@Feature("Поиск товаров")
public class SearchServiceTest {

    private static final Logger logger = LogManager.getLogger(SearchServiceTest.class);

    @Test
    @Story("Поиск существующего товара")
    @DisplayName("Поиск по существующему товару — 'Монополия'")
    public void testSearchForExistingProduct() {
        Allure.step("Запускаем тест: Поиск по существующему товару — 'Монополия'", () -> {
            SearchService service = new SearchService();
            String searchName = "Монополия";

            service.doRequest(searchName);

            int statusCode = service.getResponseStatusCode();
            String responseBody = service.getResponseBody();

            Document doc = Jsoup.parse(responseBody, "UTF-8");
            String productCardNameXpath = "//div[@class='product-card-title']//a";
            Elements searchCardTitles = doc.selectXpath(productCardNameXpath);
            Element firstCard = searchCardTitles.first();

            assertNotNull(firstCard, "Должна быть хотя бы одна карточка товара");
            String actual = firstCard.text().substring(0, Math.min(9, firstCard.text().length()));

            logger.info("Ожидаемый товар: '{}', Найденный товар в ответе: '{}'", searchName, actual);

            assertEquals(searchName, actual, "Название товара не совпадает");
            assertEquals(200, statusCode, "Статус-код должен быть 200");
        });
    }

    @Test
    @Story("Поиск несуществующего товара")
    @DisplayName("Поиск по несуществующему товару")
    public void testSearchForNonExistingProduct() {
        Allure.step("Поиск по несуществующему товару", () -> {
            SearchService service = new SearchService();
            String search = "weqfafas";

            service.doRequest(search);

            int statusCode = service.getResponseStatusCode();
            String responseBody = service.getResponseBody();

            Document doc = Jsoup.parse(responseBody, "UTF-8");
            String title = doc.title();
            Elements productCards = doc.select("div.product-card-title a");

            logger.info("Ответ сервера: статус = {}, заголовок страницы = '{}', количество карточек товара = {}",
                    statusCode, title, productCards.size());

            assertEquals(200, statusCode, "Статус-код должен быть 200");
            assertTrue(productCards.isEmpty(), "Ожидается отсутствие результатов поиска");
        });
    }

    @Test
    @Story("Поиск с пустой строкой")
    @DisplayName("Поиск с пустой строкой")
    public void testSearchWithEmptyString() {
        Allure.step("Поиск с пустой строкой", () -> {
            SearchService service = new SearchService();
            String search = "";

            service.doRequest(search);

            int statusCode = service.getResponseStatusCode();

            logger.info("Ответ сервера status={}", statusCode);

            assertEquals(200, statusCode, "Статус-код должен быть 200");
            assertFalse(service.getResponseBody().isEmpty(), "Ответ не должен быть пустым");
        });
    }
}
