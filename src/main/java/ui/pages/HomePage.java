package ui.pages;

import ui.utils.Driver;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final String URL = "https://hobbygames.by/";
    private final String PERSONAL_LINK = "//a[@href='/login']";
    private final String CLOSE_COOKIE = "//div[@class='cookie-banner__button']//button";
    private WebDriver driver;

    public HomePage() {
        driver = Driver.getDriver();
    }

    public HomePage openSite() {
        Driver.getDriver().get(URL);
        return this;
    }

    public HomePage closeCookie(){
        Driver.clickElement(CLOSE_COOKIE);
        return this;
    }

    public void clickPersonalLink() {
        Driver.clickElement(PERSONAL_LINK);
    }
}
