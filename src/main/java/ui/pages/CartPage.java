package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private final WebDriver driver = Driver.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private void clickWithRetry(String xpath) {
        for (int i = 0; i < 3; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                driver.findElement(By.xpath(xpath)).click();
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
            }
        }
    }

    public void clickCatalogBoardsGames() {
        clickWithRetry(CATALOG_BOARD_GAMES);
    }

    public void clickLinkIconCart() {
        clickWithRetry(LINK_ICON_CART);
    }

    public void clickInputFirstItemBuy() {
        clickWithRetry(INPUT_FIRST_ITEM_BUY);
    }

    public void clickInputSecondItemBuy() {
        clickWithRetry(INPUT_SECOND_ITEM_BUY);
    }

    public void clickButtonProductPlusCart() {
        clickWithRetry(BUTTON_PRODUCT_PLUS_CART);
    }

    public void clickButtonProductMinusCart() {
        clickWithRetry(BUTTON_PRODUCT_MINUS_CART);
    }

    public void clickButtonIconRemove() {
        clickWithRetry(BUTTON_ICON_REMOVE);
    }

    public String getCartSummaryText(String expectedText) {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_SUMMARY_TEXT)));
                wait.until(driver -> element.getText().contains(expectedText));
                return element.getText();
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new RuntimeException("Не удалось получить текст корзины после 3 попыток");
    }

    public String getEmptyCartPriceText() {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_LIST_EMPTY)));
                return element.getText().trim();
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new RuntimeException("Не удалось получить текст пустой корзины после 3 попыток");
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
}
