package com.solvd.testautomation.web.components;

import com.solvd.testautomation.web.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchLineComponent extends AbstractUIObject {

    @FindBy(xpath = ".//[@class = 'search__fields']")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = ".//[@class = 'search__fields']//span[@class = 'icon ']")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = ".//*[@id='fast-autocomplete-1235']/div[1]/div[1]/div")
    private ExtendedWebElement suggestionsContainer;

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

    public List<String> getSearchSuggestions() {
        return suggestionsContainer.findElements(By.tagName("a"))   // HTML tag <a> represents hyperlinks
                .stream()
                .map(element -> (ExtendedWebElement) element)
                .map(ExtendedWebElement::getText)
                .collect(Collectors.toList());
    }
}
