package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TikTokPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='policy-card']/div[1]/div[2]")
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
