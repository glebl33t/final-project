package uiTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ui.pages.SearchPage;
import ui.utils.Driver;
import ui.utils.Generators;

import java.util.List;

public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeEach
    public void setup() {
        searchPage = new SearchPage();
    }

    private void performSearch(String searchText) {
        searchPage.sendKeysSearch(searchText);
        searchPage.startSearch();
    }

    @Test
    @DisplayName("Поиск конкретного товара")
    public void searchSpecificProduct() {
        String search = "Space Marine Attack Bike";
        performSearch(search);
        String actualTitle = searchPage.getSearchResultFirstItemTitleText();

        Assertions.assertEquals(search, actualTitle,
                "Название первого товара не совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Поиск по ключевому слову")
    public void searchAllProductsByName() {
        String search = "монополия";
        performSearch(search);
        List<String> searchResults = searchPage.getSearchResultAllItemsTitleText();

        Assertions.assertAll("Каждый результат должен содержать слово " + search,
                searchResults.stream()
                        .map(result -> (Executable) () -> Assertions.assertTrue(
                                result.toLowerCase().contains(search),
                                "Результат не содержит искомое слово: " + result))
                        .toArray(Executable[]::new)
        );
    }

    @Test
    @DisplayName("Поиск по несуществующему слову")
    public void searchReturnNoResult() {
        String search = Generators.generateRandomString(6);
        performSearch(search);

        String expectedMessage = searchPage.getNotFoundTitle();
        String actualMessage = Driver.getText("//div[@class='result']");

        Assertions.assertEquals(expectedMessage, actualMessage,
                "Некорректное сообщение об отсутствии результатов. Ожидалось: '" + expectedMessage + "', но было: '" + actualMessage + "'");
    }
}
