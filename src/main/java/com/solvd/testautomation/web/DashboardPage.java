package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='shop_dashboard']")
    private ExtendedWebElement dashboardTitle;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardPageDisplayed() {
        return dashboardTitle.isElementPresent(1);
    }
}
