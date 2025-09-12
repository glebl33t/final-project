package by.hobbygames.ui;

import by.hobbygames.ui.pages.SearchPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeEach
    public void setup() {
        searchPage = new SearchPage();
    }

    private void fillSearch(String searchText) {
        searchPage.sendKeysSearch(searchText);
        searchPage.startSearch();
    }

    @Test
    @DisplayName("Поиск конкретного товара")
    public void searchSpecificProduct() {
        fillSearch("Space Marine Attack Bike");
        String actualTitle = searchPage.getSearchResultFirstItemTitleText();

        Assertions.assertEquals("Space Marine Attack Bike", actualTitle,
                "Название первого товара не совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Поиск по ключевому слову 'уточка'")
    public void searchAllProductsByName() {
        fillSearch("уточка");
        List<String> searchResults = searchPage.getSearchResultAllItemsTitleText();

        SoftAssertions softAssertions = new SoftAssertions();
        for (String result : searchResults) {
            softAssertions.assertThat(result.toLowerCase().contains("уточка"))
                    .withFailMessage("Результат не содержит искомое слово: %s", result)
                    .isTrue();
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Поиск по несуществующему слову")
    public void searchReturnNoResult() {
        fillSearch("asfsaf");

        Assertions.assertEquals("Ничего не найдено.", searchPage.getNotFountTitle(),
                "Сообщение об отсутствии результатов некорректное");
    }
}
