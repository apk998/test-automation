package com.solvd.testautomation.web.components;

import com.solvd.testautomation.web.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchLineComponent extends AbstractUIObject {

    @FindBy(xpath = ".//[@class = 'search__fields']")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = ".//[@class = 'search__fields']//span[@class = 'icon ']")
    private ExtendedWebElement searchButton;

    public SearchLineComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isSearchInputDisplayed() {
        return searchInput.isElementPresent(1);
    }

    public boolean isSearchButtonDisplayed() {
        return searchButton.isElementPresent(1);
    }

    public String getSearchInputPlaceholder() {
        return searchInput.getAttribute("placeholder");
    }

    public SearchPage clickSearchButton() {
        searchButton.click();
        return new SearchPage(getDriver());
    }

    public void typeSearchInputValue(String value) {
        searchInput.type(value);
    }
}
