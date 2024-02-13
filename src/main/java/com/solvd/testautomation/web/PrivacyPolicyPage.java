package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='shopify-section-page__main']/main/div[1]/div/h1")
    private ExtendedWebElement pageTitle;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPrivacyPolicyPageDisplayed() {
        return pageTitle.isElementPresent(1);
    }
}
