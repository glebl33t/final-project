package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.Driver;

import java.time.Duration;

public class CartPage {
    private final String CATALOG_BOARD_GAMES = "/html/body/div[1]/div[4]/div[2]/nav/ul/li[1]/a";
    private final String LINK_ICON_CART = "//a[@class='with-icon cart-status cart-column cart-count']";
    private final String INPUT_FIRST_ITEM_BUY = "(//a[@data-block-type='main-block'])[1]";
    private final String INPUT_SECOND_ITEM_BUY = "(//a[@data-block-type='main-block'])[2]";
    private final String TITLE_FIRST_PRODUCT_NAME = "(//tr[@data-id]//td[contains(@class, 'item-name')]//a)[1]";
    private final String TITLE_SECOND_PRODUCT_NAME = "(//tr[@data-id]//td[contains(@class, 'item-name')]//a)[2]";
    private final String PRICE_FIRST_PRODUCT = "(//tr[contains(@class,'item-list')])[1]//td[contains(@class,'prices')]//span[@class='price']";
    private final String PRICE_SECOND_PRODUCT = "(//tr[contains(@class,'item-list')])[2]//td[contains(@class,'prices')]//span[@class='price']";
    private final String BUTTON_ICON_REMOVE = "//a[@data-action='del']";
    private final String ITEM_LIST_EMPTY = "//td[@class='col-md-2 col-xs-3 total-price']";
    private final String CART_SUMMARY_TEXT = "//td[contains(@class, 'summary-text')]";
    private final String BUTTON_PRODUCT_PLUS_CART = "//button[@data-action='plus']";
    private final String BUTTON_PRODUCT_MINUS_CART = "//button[@data-action='minus']";

    public void clickCatalogBoardsGames() {
        Driver.click(CATALOG_BOARD_GAMES);
    }

    public void clickButtonProductPlusCart() {
        Driver.click(BUTTON_PRODUCT_PLUS_CART);
    }

    public void clickButtonProductMinusCart() {
        Driver.click(BUTTON_PRODUCT_MINUS_CART);
    }

    public String getCartSummaryText(String expectedText) {
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> driver1.findElement(By.xpath(CART_SUMMARY_TEXT)).getText().contains(expectedText));
        return driver.findElement(By.xpath(CART_SUMMARY_TEXT)).getText();
    }


    public void clickButtonIconRemove() {
        Driver.click(BUTTON_ICON_REMOVE);
    }

    public String getEmptyCartPriceText(String expectedText) {
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath(ITEM_LIST_EMPTY), expectedText
        ));

        return driver.findElement(By.xpath(ITEM_LIST_EMPTY)).getText();
    }


    public String getPriceFirstProduct() {
        return Driver.getText(PRICE_FIRST_PRODUCT);
    }

    public String getPriceSecondProduct() {
        return Driver.getText(PRICE_SECOND_PRODUCT);
    }

    public String getNameFirstProduct() {
        return Driver.getText(TITLE_FIRST_PRODUCT_NAME);
    }

    public String getNameSecondProduct() {
        return Driver.getText(TITLE_SECOND_PRODUCT_NAME);
    }

    public void clickLinkIconCart() {
        Driver.click(LINK_ICON_CART);
    }

    public void clickInputFirstItemBuy() {
        Driver.click(INPUT_FIRST_ITEM_BUY);
    }

    public void clickInputSecondItemBuy() {
        Driver.click(INPUT_SECOND_ITEM_BUY);
    }
}
