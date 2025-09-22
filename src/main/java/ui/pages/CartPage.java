package ui.pages;

import ui.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final String CATALOG_BOARD_GAMES = "/html/body/div[1]/div[4]/div[2]/nav/ul/li[1]/a";
    private final String BUTTON_CART = "//span[@class='icon icon-shopping_cart-black icon__black']";
    private final String BUTTON_BUY = "//div[@class='product-card-shopping product-cart  ']//a[@data-block-type='main-block']";
    private final String FIRST_ITEM_PRICE = "//span[@class='rub']";
    private final String SECOND_ITEM_PRICE = "//*[@id='product-table']/tbody/tr[2]/td[5]/span/text()[2]";
    private final String INPUT_CART = "//a[@class='with-icon cart-status cart-column cart-count']";
    private final String INPUT_FIRST_ITEM_BUY = "(//a[@data-block-type='main-block'])[1]";
    private final String INPUT_SECOND_ITEM_BUY = "(//a[@data-block-type='main-block'])[2]";
    private final String TITLE_FIRST_PRODUCT_NAME = "//td[@class='col-lg-5 col-md-4 col-sm-8 col-xs-8 name item-name']";
    private WebDriver driver;

    public void clickCatalogBoardsGames() {
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CATALOG_BOARD_GAMES)));
        element.click();
    }

    public String getNameFirstProduct() {
        return Driver.getTextFromElement(TITLE_FIRST_PRODUCT_NAME);
    }

    public String firstItemPrice() {
        return Driver.getTextFromElement(FIRST_ITEM_PRICE);
    }

    public String secondItemPrice() {
        return Driver.getTextFromElement(SECOND_ITEM_PRICE);
    }

    public void clickInputCart() {
        Driver.clickElement(INPUT_CART);
    }

    public void clickInputFirstItemBuy() {
        Driver.clickElement(INPUT_FIRST_ITEM_BUY);
    }

    public void clickInputSecondItemBuy() {
        Driver.clickElement(INPUT_SECOND_ITEM_BUY);
    }
}
