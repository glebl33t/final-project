package uiTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pages.CartPage;

@Epic("UI Tests")
@Feature("Cart Tests")
public class CartTest extends BaseTest {

    private CartPage cartPage;
    private static final Logger log = LoggerFactory.getLogger(CartTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Запуск теста: инициализация страницы корзины");
        cartPage = new CartPage();
        cartPage.clickCatalogBoardsGames();
    }

    @Step("Добавляем {count} товаров в корзину и открываем её")
    private void addProductsToCart(int count) {
        if (count >= 1) cartPage.clickInputFirstItemBuy();
        if (count >= 2) cartPage.clickInputSecondItemBuy();
        cartPage.clickLinkIconCart();
        log.info("{} товар(ов) добавлено и корзина открыта", count);
    }

    @Test
    @DisplayName("Сравнение имен двух разных товаров")
    @Description("Проверяем, что имена двух добавленных товаров не совпадают")
    @Severity(SeverityLevel.CRITICAL)
    public void productNamesShouldNotBeEqual() {
        addProductsToCart(2);

        Assertions.assertNotEquals(cartPage.getNameFirstProduct(), cartPage.getNameSecondProduct(),
                "Имена товаров не должны совпадать");
    }

    @Test
    @DisplayName("Сравнение цен двух разных товаров")
    @Description("Проверяем, что цены двух добавленных товаров не совпадают")
    @Severity(SeverityLevel.CRITICAL)
    public void productPricesShouldNotBeEqual() {
        addProductsToCart(2);

        Assertions.assertNotEquals(cartPage.getPriceFirstProduct(), cartPage.getPriceSecondProduct(),
                "Цены товаров не должны совпадать");
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    @Description("После удаления товара итоговая сумма должна быть 'Бесплатно'")
    @Severity(SeverityLevel.NORMAL)
    public void removingProductShouldMakeCartFree() {
        addProductsToCart(1);
        cartPage.clickButtonIconRemove();

        Assertions.assertTrue(cartPage.waitForEmptyCartPriceText("Бесплатно", 5),
                "Ожидалось, что текст содержит 'Бесплатно'");
    }

    @Test
    @DisplayName("Добавление одного товара в корзину")
    @Description("Проверяем корректное отображение текста корзины при добавлении одного товара")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDisplayCorrectTextWhenOneProductAdded() {
        addProductsToCart(1);

        Assertions.assertTrue(cartPage.waitForCartSummaryText("1 товар", 5),
                "Ожидалось, что текст содержит '1 товар'");
    }

    @Test
    @DisplayName("Увеличение количества товара (+1)")
    @Description("Проверяем, что после нажатия +1 количество товара обновляется корректно")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayCorrectTextAfterClickingPlusButton() {
        addProductsToCart(1);
        cartPage.clickButtonProductPlusCart();

        Assertions.assertTrue(cartPage.waitForCartSummaryText("2 товара", 5),
                "Ожидалось, что текст содержит '2 товара'");
    }

    @Test
    @DisplayName("Уменьшение количества товара (-1)")
    @Description("Проверяем, что после нажатия -1 количество товара обновляется корректно или корзина становится пустой")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayCorrectTextAfterClickingMinusButton() {
        addProductsToCart(1);
        cartPage.clickButtonProductMinusCart();

        Assertions.assertTrue(
                cartPage.waitForCartSummaryText("0 товаров", 5) || cartPage.waitForEmptyCartPriceText("Бесплатно", 5),
                "Ожидалось, что текст содержит '0 товаров' или 'Бесплатно'");
    }

}
