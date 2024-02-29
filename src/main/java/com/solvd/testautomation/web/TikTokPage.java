package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TikTokPage extends AbstractPage {

    @FindBy(xpath = "//*[@data-e2e='title-privacy-policy']")
    private ExtendedWebElement titleText;

    public TikTokPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleTextDisplayed() {
        return titleText.isElementPresent(1);
    }

    public String getTitleText() {
        return titleText.getText();
    }
}
