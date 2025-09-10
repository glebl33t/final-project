package by.hobbygames.ui.pages;

import by.hobbygames.ui.utils.WebDriver;

public class HomePage {
    private final String URL = "https://hobbygames.by/";
    private final String PERSONAL_LINK = "//a[@href='/login']";
    private final String CLOSE_COOKIE = "//div[@class='cookie-banner__button']//button";
    private org.openqa.selenium.WebDriver driver;

    public HomePage() {
        driver = WebDriver.getDriver();
    }

    public HomePage openSite() {
        WebDriver.getDriver().get(URL);
        return this;
    }

    public HomePage closeCookie(){
        WebDriver.clickElement(CLOSE_COOKIE);
        return this;
    }

    public void clickPersonalLink() {
        WebDriver.clickElement(PERSONAL_LINK);
    }
}
