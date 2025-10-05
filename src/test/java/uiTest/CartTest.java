package uiTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pages.CartPage;

@Epic("Cart functionality")
@Feature("Cart operations")
public class CartTest extends BaseTest {
    private CartPage cartPage;
    private static final Logger log = LoggerFactory.getLogger(CartTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Запуск теста: инициализация страницы корзины");
        cartPage = new CartPage();
        cartPage.clickCatalogBoardsGames();
    }

    private void addFirstProductAndOpenCart() {
        log.info("Добавление первого продукта и открытие корзины");
        cartPage.clickInputFirstItemBuy();
        cartPage.clickLinkIconCart();
    }

    private void addTwoProductsAndOpenCart() {
        log.info("Добавление двух продуктов и открытие корзины");
        cartPage.clickInputFirstItemBuy();
        cartPage.clickInputSecondItemBuy();
        cartPage.clickLinkIconCart();
    }

    @Test
    @DisplayName("Сравнение имен двух разных товаров")
    @Description("Проверяем, что имена двух добавленных товаров не совпадают")
    @Severity(SeverityLevel.CRITICAL)
    public void productNamesShouldNotBeEqual() {
        addTwoProductsAndOpenCart();
        String firstProductName = cartPage.getNameFirstProduct();
        String secondProductName = cartPage.getNameSecondProduct();

        log.info("Первый товар: {}, Второй товар: {}", firstProductName, secondProductName);
        Assertions.assertNotEquals(firstProductName, secondProductName);
    }

    @Test
    @DisplayName("Сравнение цен двух разных товаров")
    @Description("Проверяем, что цены двух добавленных товаров не совпадают")
    @Severity(SeverityLevel.CRITICAL)
    public void productPricesShouldNotBeEqual() {
        addTwoProductsAndOpenCart();
        String firstProductPrice = cartPage.getPriceFirstProduct();
        String secondProductPrice = cartPage.getPriceSecondProduct();

        log.info("Цена первого товара: {}, Цена второго товара: {}", firstProductPrice, secondProductPrice);
        Assertions.assertNotEquals(firstProductPrice, secondProductPrice);
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    @Description("После удаления товара из корзины итоговая сумма должна обнулиться")
    @Severity(SeverityLevel.NORMAL)
    public void removingProductShouldMakeCartFree() {
        addFirstProductAndOpenCart();
        cartPage.clickButtonIconRemove();
        String expectedText = "Бесплатно";
        String actualText = cartPage.getEmptyCartPriceText();

        log.info("Ожидаемый текст: {}, Фактический текст: {}", expectedText, actualText);
        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }

    @Test
    @DisplayName("Добавление одного товара в корзину")
    @Description("Проверяем корректное отображение текста корзины при добавлении одного товара")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDisplayCorrectTextWhenOneProductAdded() {
        addFirstProductAndOpenCart();
        String expectedText = "1 товар";
        String actualText = cartPage.getCartSummaryText(expectedText);

        log.info("Ожидаемый текст: {}, Фактический текст: {}", expectedText, actualText);
        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }

    @Test
    @DisplayName("Увеличение количества товара (+1)")
    @Description("Проверяем, что после нажатия +1 количество товара обновляется корректно")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayCorrectTextAfterClickingPlusButton() {
        addFirstProductAndOpenCart();
        cartPage.clickButtonProductPlusCart();
        String expectedText = "2 товара";
        String actualText = cartPage.getCartSummaryText(expectedText);

        log.info("Ожидаемый текст: {}, Фактический текст: {}", expectedText, actualText);
        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }

    @Test
    @DisplayName("Уменьшение количества товара (-1)")
    @Description("Проверяем, что после нажатия -1 количество товара обновляется корректно")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayCorrectTextAfterClickingMinusButton() {
        addFirstProductAndOpenCart();
        cartPage.clickButtonProductMinusCart();
        String expectedText = "0 товаров";
        String actualText = cartPage.getCartSummaryText(expectedText);

        log.info("Ожидаемый текст: {}, Фактический текст: {}", expectedText, actualText);
        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }
}
