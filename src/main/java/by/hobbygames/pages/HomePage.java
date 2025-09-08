package by.hobbygames.pages;

import by.hobbygames.utils.WebDriver;

public class HomePage {
    private final String URL = "https://hobbygames.by/";
    private final String PERSONAL_LINK = "//a[@href='/login']";
    private org.openqa.selenium.WebDriver driver;

    public HomePage() {
        driver = WebDriver.getDriver();
    }

    public void openSite() {
        WebDriver.getDriver().get(URL);
    }

    public void clickPersonalLink() {
        WebDriver.clickElement(PERSONAL_LINK);
    }
}
