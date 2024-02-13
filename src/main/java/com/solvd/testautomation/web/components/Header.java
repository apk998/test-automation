package com.solvd.testautomation.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject {

    @FindBy(xpath = ".//*")
    private SearchLineComponent component;

    @FindBy(xpath = ".//li[@class='header__login']/ul/li/a[contains(text(),'Login')]")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = ".//li[@class='header__login']/ul/li/a[contains(text(),'Create Account')]")
    private ExtendedWebElement createAccountButton;

    @FindBy(xpath = ".//div[@class='header__dropdown']")
    private ExtendedWebElement dropdownMenu;

    @FindBy(xpath = ".//*[@id='navLink-3']/span")
    private ExtendedWebElement charactersCategoryLink;

    @FindBy(xpath = ".//*/section/div/header/ul[2]/li[3]/a/span[2]/span[2]")
    private ExtendedWebElement cartItemCountElement;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public SearchLineComponent getComponent() {
        return component;
    }

    public void clickLogin() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(dropdownMenu).perform();
        loginButton.click();
    }

    public void clickCreateAccount() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(dropdownMenu).perform();
        createAccountButton.click();
    }

    public void navigateToCharactersCategory() {
        charactersCategoryLink.click();
    }

    public int getCartItemCount() {
        String itemCountText = cartItemCountElement.getText().trim();

        try {
            return Integer.parseInt(itemCountText);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Unable to parse cart item count as an integer: " + itemCountText);
        }
    }
}
