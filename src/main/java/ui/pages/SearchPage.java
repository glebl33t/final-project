package ui.pages;

import ui.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPage {
    String INPUT_SEARCH = "//input[@name='keyword']";
    String ICON_SEARCH = "//span[@class='icon icon-ic_search_black search-btn']";
    String ELEMENT_TITLE = "//div[@class='product-card-title']//a[@class=' ']";
    String NOT_FOUND_TITLE = "//div[@class='result']";
    private WebDriver driver;

    public SearchPage() {
        driver = Driver.getDriver();
    }

    public void sendKeysSearch(String search) {
        Driver.sendKeysToElement(INPUT_SEARCH, search);
    }

    public void startSearch() {
        Driver.clickElement(ICON_SEARCH);
    }

    public String getSearchResultFirstItemTitleText() {
        return Driver.getTextFromElement(ELEMENT_TITLE);
    }

    public List<String> getSearchResultAllItemsTitleText() {
        List<WebElement> listOfWebElements = Collections.singletonList(Driver.findElement(ELEMENT_TITLE));
        List<String> listOfSearchItems = new ArrayList<>();

        for (WebElement element : listOfWebElements) {
            listOfSearchItems.add((element.getText()).toLowerCase());
        }
        return listOfSearchItems;
    }

    public String getNotFoundTitle() {
        return Driver.getTextFromElement(NOT_FOUND_TITLE);
    }
}
