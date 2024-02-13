package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.Footer;
import com.solvd.testautomation.web.components.Header;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//div[@id='shopify-section-header__top-bar']")
    private Header header;

    @FindBy(xpath = "//*[@id='shopify-section-footer-classic']/footer/section")
    private Footer footer;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        openURL(Configuration.getRequired("home_url"));
    }

    public Header getHeader() {
        return header;
    }

    public Footer getFooter() {
        return footer;
    }
}