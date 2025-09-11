package by.hobbygames.ui;

import by.hobbygames.ui.pages.SearchPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    private static final String SEARCH_TERM_EXISTING = "Space Marine Attack Bike";
    private static final String SEARCH_TERM_PARTIAL = "уточка";
    private static final String SEARCH_TERM_INVALID = "asfsaf";
    private static final String NO_RESULTS_MESSAGE = "Ничего не найдено.";

    @BeforeEach
    public void setup() {
        searchPage = new SearchPage();
    }

    private void fillSearch(String searchText) {
        searchPage.sandKeysSearch(searchText);
        searchPage.startSearch();
    }

    @Test
    @DisplayName("Поиск конкретного товара")
    public void searchSpecificProduct() {
        fillSearch(SEARCH_TERM_EXISTING);
        String actualTitle = searchPage.getSearchResultFirstItemTitleText();

        Assertions.assertEquals(SEARCH_TERM_EXISTING, actualTitle,
                "Название первого товара не совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Поиск по ключевому слову 'уточка'")
    public void searchAllProductsByName() {
        fillSearch(SEARCH_TERM_PARTIAL);
        List<String> searchResults = searchPage.getSearchResultAllItemsTitleText();

        SoftAssertions softAssertions = new SoftAssertions();
        for (String result : searchResults) {
            softAssertions.assertThat(result.toLowerCase().contains(SEARCH_TERM_PARTIAL))
                    .withFailMessage("Результат не содержит искомое слово: %s", result)
                    .isTrue();
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Поиск по несуществующему слову")
    public void searchReturnNoResult() {
        fillSearch(SEARCH_TERM_INVALID);

        Assertions.assertEquals(NO_RESULTS_MESSAGE, searchPage.getNotFountTitle(),
                "Сообщение об отсутствии результатов некорректное");
    }
}
