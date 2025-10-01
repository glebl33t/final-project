package uiTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.CartPage;

public class CartTest extends BaseTest {
    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        cartPage = new CartPage();
        cartPage.clickCatalogBoardsGames();
    }

    private void addFirstProductAndOpenCart() {
        cartPage.clickInputFirstItemBuy();
        cartPage.clickLinkIconCart();
    }

    private void addTwoProductsAndOpenCart() {
        cartPage.clickInputFirstItemBuy();
        cartPage.clickInputSecondItemBuy();
        cartPage.clickLinkIconCart();
    }

    @Test
    @DisplayName("Сравнение имен двух разных товаров: они должны быть разными")
    public void productNamesShouldNotBeEqual() {
        addTwoProductsAndOpenCart();
        String firstProductName = cartPage.getNameFirstProduct();
        String secondProductName = cartPage.getNameSecondProduct();

        Assertions.assertNotEquals(firstProductName, secondProductName);
    }

    @Test
    @DisplayName("Сравнение цен двух разных товаров: они должны быть разными")
    public void productPricesShouldNotBeEqual() {
        addTwoProductsAndOpenCart();
        String firstProductPrice = cartPage.getPriceFirstProduct();
        String secondProductPrice = cartPage.getPriceSecondProduct();

        Assertions.assertNotEquals(firstProductPrice, secondProductPrice);
    }

    @Test
    @DisplayName("Удаление товара из корзины должно обнулить итоговую сумму")
    public void removingProductShouldMakeCartFree() {
        addFirstProductAndOpenCart();
        cartPage.clickButtonIconRemove();
        String expectedText = "бесплатно";
        String actualText = cartPage.getEmptyCartPriceText(expectedText);

        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Добавление одного товара в корзину должно отображаться корректно")
    public void shouldDisplayCorrectTextWhenOneProductAdded() {
        addFirstProductAndOpenCart();
        String expectedText = "1 товар";
        String actualText = cartPage.getCartSummaryText(expectedText);

        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }

    @Test
    @DisplayName("Увеличение количества товара (+1) должно отразиться в тексте корзины")
    public void shouldDisplayCorrectTextAfterClickingPlusButton() {
        addFirstProductAndOpenCart();
        cartPage.clickButtonProductPlusCart();
        String expectedText = "2 товара";
        String actualText = cartPage.getCartSummaryText(expectedText);

        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }

    @Test
    @DisplayName("Уменьшение количества товара (-1) должно отразиться в тексте корзины")
    public void shouldDisplayCorrectTextAfterClickingMinusButton() {
        addFirstProductAndOpenCart();
        cartPage.clickButtonProductMinusCart();
        String expectedText = "0 товаров";
        String actualText = cartPage.getCartSummaryText(expectedText);

        Assertions.assertTrue(actualText.contains(expectedText),
                "Ожидалось, что текст содержит " + expectedText + " , но был: " + actualText);
    }
}
