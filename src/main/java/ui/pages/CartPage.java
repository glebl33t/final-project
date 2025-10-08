package ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.Driver;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver = Driver.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

    private boolean clickWithRetry(String xpath) {
        for (int i = 0; i < 5; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                element.click();
                return true;
            } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {}
            }
        }
        return false;
    }

    private String getTextByXpath(String xpath) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean clickCatalogBoardsGames() {
        return clickWithRetry(CATALOG_BOARD_GAMES);
    }

    public boolean clickLinkIconCart() {
        return clickWithRetry(LINK_ICON_CART);
    }

    public boolean clickInputFirstItemBuy() {
        return clickWithRetry(INPUT_FIRST_ITEM_BUY);
    }

    public boolean clickInputSecondItemBuy() {
        return clickWithRetry(INPUT_SECOND_ITEM_BUY);
    }

    public boolean clickButtonProductPlusCart() {
        return clickWithRetry(BUTTON_PRODUCT_PLUS_CART);
    }

    public boolean clickButtonProductMinusCart() {
        return clickWithRetry(BUTTON_PRODUCT_MINUS_CART);
    }

    public boolean clickButtonIconRemove() {
        return clickWithRetry(BUTTON_ICON_REMOVE);
    }

    public String getNameFirstProduct() {
        return getTextByXpath(TITLE_FIRST_PRODUCT_NAME);
    }

    public String getNameSecondProduct() {
        return getTextByXpath(TITLE_SECOND_PRODUCT_NAME);
    }

    public String getPriceFirstProduct() {
        return getTextByXpath(PRICE_FIRST_PRODUCT);
    }

    public String getPriceSecondProduct() {
        return getTextByXpath(PRICE_SECOND_PRODUCT);
    }

    public boolean waitForText(By locator, String expectedText, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return customWait.until(driver -> {
                try {
                    String text = driver.findElement(locator).getText().trim();
                    System.out.println("Текущий текст: '" + text + "'; ожидается: '" + expectedText + "'");
                    return text.contains(expectedText);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForCartSummaryText(String expectedText, int timeoutSeconds) {
        return waitForText(By.xpath(CART_SUMMARY_TEXT), expectedText, timeoutSeconds);
    }

    public boolean waitForEmptyCartPriceText(String expectedText, int timeoutSeconds) {
        return waitForText(By.xpath(ITEM_LIST_EMPTY), expectedText, timeoutSeconds);
    }
}
