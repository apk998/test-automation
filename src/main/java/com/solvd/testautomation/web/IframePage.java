package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class IframePage extends AbstractPage {

    @FindBy(xpath = "//div[@data-e2e='legal-links']//a[contains(text(), 'Privacy Policy')]")
    private ExtendedWebElement privacyPolicyLink;

    public IframePage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getPrivacyPolicyLink() {
        return privacyPolicyLink;
    }
}
