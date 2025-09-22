package uiTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.CartPage;

public class CartTest extends BaseTest {
    private CartPage cartPage;

    @BeforeEach
    public void setupPages() {
        cartPage = new CartPage();
        cartPage.clickCatalogBoardsGames();
    }

    @Test
    public void addTwoProductsToCart() {
        cartPage.clickInputFirstItemBuy();
        cartPage.clickInputSecondItemBuy();
        cartPage.clickInputCart();

        String actualFirstProductName = cartPage.getNameFirstProduct();

        String expectedFirstProductName = "Название первого товара";

        Assertions.assertEquals(expectedFirstProductName, actualFirstProductName,
                "Имя первого продукта не совпадает. Получено: " + actualFirstProductName);
    }
}
