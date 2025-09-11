package by.hobbygames.ui.pages;

import by.hobbygames.ui.utils.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPage {
    String INPUT_SEARCH = "//input[@name='keyword']";
    String ICON_SEARCH = "//span[@class='icon icon-ic_search_black search-btn']";
    String ELEMENT_TITLE = "//div[@class='product-card-title']//a[@class=' ']";
    String NOT_FOUND_TITLE = "//div[@class='result']";
    private org.openqa.selenium.WebDriver driver;

    public SearchPage() {
        driver = WebDriver.getDriver();
    }

    public void sandKeysSearch(String search) {
        WebDriver.sandKeysToElement(INPUT_SEARCH, search);
    }

    public void startSearch() {
        WebDriver.clickElement(ICON_SEARCH);
    }

    public String getSearchResultFirstItemTitleText() {
        return WebDriver.getTextFromElement(ELEMENT_TITLE);
    }

    public List<String> getSearchResultAllItemsTitleText() {
        List<WebElement> listOfWebElements = Collections.singletonList(WebDriver.findElement(ELEMENT_TITLE));
        List<String> listOfSearchItems = new ArrayList<>();

        for (WebElement element : listOfWebElements) {
            listOfSearchItems.add((element.getText()).toLowerCase());
        }
        return listOfSearchItems;
    }

    public String getNotFountTitle() {
        return WebDriver.getTextFromElement(NOT_FOUND_TITLE);
    }
}
