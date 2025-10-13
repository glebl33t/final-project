package uiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pages.SearchPage;
import ui.utils.Driver;

import java.util.List;

@Epic("UI Tests")
@Feature("Search Tests")
public class SearchTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(SearchTest.class);

    private SearchPage searchPage;

    @BeforeEach
    public void setup() {
        log.info("Инициализация страницы поиска");
        searchPage = new SearchPage();
    }

    @Step("Выполнить поиск по тексту: {searchText}")
    private void performSearch(String searchText) {
        log.info("Ищем: {}", searchText);
        searchPage.sendKeysSearch(searchText);
        searchPage.startSearch();
    }

    @Test
    @Story("Поиск существующего товара")
    @DisplayName("Поиск по конкретному товару")
    public void searchSpecificProduct() {
        String search = "Space Marine Attack Bike";
        performSearch(search);

        String actualTitle = searchPage.getSearchResultFirstItemTitleText();
        log.info("Результат поиска: {}", actualTitle);

        Assertions.assertEquals(search, actualTitle,
                "Название первого товара не совпадает с ожидаемым");
    }

    @Test
    @Story("Поиск по ключевому слову")
    @DisplayName("Поиск всех товаров с ключевым словом")
    public void searchAllProductsByName() {
        String search = "монополия";
        performSearch(search);

        List<String> searchResults = searchPage.getSearchResultAllItemsTitleText();
        log.info("Количество найденных результатов: {}", searchResults.size());

        Assertions.assertAll("Каждый результат должен содержать слово " + search,
                searchResults.stream()
                        .map(result -> (Executable) () -> {
                            log.info("Проверяем результат: {}", result);
                            Assertions.assertTrue(
                                    result.toLowerCase().contains(search),
                                    "Результат не содержит искомое слово: " + result
                            );
                        })
                        .toArray(Executable[]::new)
        );
    }

    @Test
    @Story("Поиск по несуществующему слову")
    @DisplayName("Проверка отображения сообщения об отсутствии результатов")
    public void searchReturnNoResult() {
        String search = "weqfafas";
        performSearch(search);

        String expectedMessage = searchPage.getNotFoundTitle();
        String actualMessage = Driver.getText("//div[@class='result']");

        log.info("Ожидаемое сообщение: '{}', фактическое сообщение: '{}'", expectedMessage, actualMessage);

        Assertions.assertEquals(expectedMessage, actualMessage,
                "Некорректное сообщение об отсутствии результатов");
    }
}
