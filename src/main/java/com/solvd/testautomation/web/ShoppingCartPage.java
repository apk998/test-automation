package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='Combined- ']")
    private ExtendedWebElement removeItemButton;

    @FindBy(xpath = "//*[@class='cart__count--text']")
    private ExtendedWebElement cartSummary;

    @FindBy(xpath = "//*[@class='ajax-cart__empty-text']")
    private ExtendedWebElement emptyStatement;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void removeCartItem() {
        removeItemButton.click();
    }

    public boolean isCartEmpty() {
        return emptyStatement.getText().contains("There are no items in your cart.");
    }
}
