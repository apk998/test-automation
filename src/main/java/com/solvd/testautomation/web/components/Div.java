package com.solvd.testautomation.web.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Div extends AbstractUIObject {

    @FindBy(xpath = ".//*")
    private SearchLineComponent component;

    public Div(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public SearchLineComponent getComponent() {
        return component;
    }
}
