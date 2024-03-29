package com.solvd.testautomation.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Footer extends AbstractUIObject {

    @FindBy(xpath = "//*[@id='shopify-section-1594663483893']/div/ul/li[1]/a")
    private ExtendedWebElement privacyPolicyLink;

    @FindBy(xpath = ".//*[@aria-label='Instagram']")
    private ExtendedWebElement instagramButton;

    public Footer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickPrivacyPolicyLink() {
        privacyPolicyLink.click();
    }

    public void clickInstagramButton() {
        instagramButton.click();
    }
}
