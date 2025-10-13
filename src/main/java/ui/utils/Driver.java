package ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(TIMEOUT);
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebElement findElement(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public static void click(String xpath) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public static void sendKeys(String xpath, String value) {
        WebElement element = findElement(xpath);
        element.clear();
        element.sendKeys(value);
    }

    public static String getText(String xpath) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                return element.getText();
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Failed to get text from element due to stale element after retries: " + xpath);
    }
}
