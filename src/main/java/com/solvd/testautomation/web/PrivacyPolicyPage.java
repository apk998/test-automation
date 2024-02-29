package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='page__title title']")
    private ExtendedWebElement pageTitle;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPrivacyPolicyPageDisplayed() {
        return pageTitle.isElementPresent(1);
    }

    public String getTitleText() {
        return pageTitle.getText();
    }
}
