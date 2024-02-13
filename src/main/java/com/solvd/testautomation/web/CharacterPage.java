package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CharacterPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='shop_character']")
    private ExtendedWebElement characterPageTitle;

    public CharacterPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCharacterPageDisplayed() {
        return characterPageTitle.isElementPresent(1);
    }
}
