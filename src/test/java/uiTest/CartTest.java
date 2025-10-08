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

        log.info("Первый товар: '{}', Второй товар: '{}'", firstProductName, secondProductName);
        Assertions.assertNotEquals(firstProductName, secondProductName,
                "Имена товаров не должны совпадать");
    }

    @Test
    @DisplayName("Сравнение цен двух разных товаров")
    @Description("Проверяем, что цены двух добавленных товаров не совпадают")
    @Severity(SeverityLevel.CRITICAL)
    public void productPricesShouldNotBeEqual() {
        addTwoProductsAndOpenCart();
        String firstProductPrice = cartPage.getPriceFirstProduct();
        String secondProductPrice = cartPage.getPriceSecondProduct();

        log.info("Цена первого товара: '{}', Цена второго товара: '{}'", firstProductPrice, secondProductPrice);
        Assertions.assertNotEquals(firstProductPrice, secondProductPrice,
                "Цены товаров не должны совпадать");
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    @Description("После удаления товара из корзины итоговая сумма должна обнулиться")
    @Severity(SeverityLevel.NORMAL)
    public void removingProductShouldMakeCartFree() {
        addFirstProductAndOpenCart();
        log.info("Удаляем товар из корзины");
        cartPage.clickButtonIconRemove();

        log.info("Ожидаем, что итоговая сумма станет 'Бесплатно'");
        boolean isTextDisplayed = cartPage.waitForEmptyCartPriceText("Бесплатно", 5);
        log.info("Результат ожидания текста: {}", isTextDisplayed);

        Assertions.assertTrue(isTextDisplayed, "Ожидалось, что текст содержит 'Бесплатно', но это не произошло");
    }

    @Test
    @DisplayName("Добавление одного товара в корзину")
    @Description("Проверяем корректное отображение текста корзины при добавлении одного товара")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDisplayCorrectTextWhenOneProductAdded() {
        addFirstProductAndOpenCart();
        log.info("Проверяем, что в корзине отображается '1 товар'");
        boolean isTextDisplayed = cartPage.waitForCartSummaryText("1 товар", 5);
        log.info("Результат ожидания текста: {}", isTextDisplayed);

        Assertions.assertTrue(isTextDisplayed, "Ожидалось, что текст содержит '1 товар', но это не произошло");
    }

    @Test
    @DisplayName("Увеличение количества товара (+1)")
    @Description("Проверяем, что после нажатия +1 количество товара обновляется корректно")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayCorrectTextAfterClickingPlusButton() {
        addFirstProductAndOpenCart();
        log.info("Увеличиваем количество товара на +1");
        cartPage.clickButtonProductPlusCart();

        log.info("Ожидаем, что в корзине отображается '2 товара'");
        boolean isTextDisplayed = cartPage.waitForCartSummaryText("2 товара", 5);
        log.info("Результат ожидания текста: {}", isTextDisplayed);

        Assertions.assertTrue(isTextDisplayed, "Ожидалось, что текст содержит '2 товара', но это не произошло");
    }

    @Test
    @DisplayName("Уменьшение количества товара (-1)")
    @Description("Проверяем, что после нажатия -1 количество товара обновляется корректно или корзина становится пустой")
    @Severity(SeverityLevel.MINOR)
    public void shouldDisplayCorrectTextAfterClickingMinusButton() {
        addFirstProductAndOpenCart();
        log.info("Уменьшаем количество товара на -1");
        cartPage.clickButtonProductMinusCart();

        log.info("Ожидаем, что в корзине отображается '0 товаров' или 'Бесплатно'");
        boolean isZeroItems = cartPage.waitForCartSummaryText("0 товаров", 5);
        boolean isCartFree = cartPage.waitForEmptyCartPriceText("Бесплатно", 5);

        log.info("Результаты ожидания: '0 товаров' = {}, 'Бесплатно' = {}", isZeroItems, isCartFree);

        Assertions.assertTrue(isZeroItems || isCartFree, "Ожидалось, что текст содержит '0 товаров' или 'Бесплатно', но это не произошло");
    }

}
