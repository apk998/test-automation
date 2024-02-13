package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='Combined- ']")
    private ExtendedWebElement removeItemButton;

    @FindBy(xpath = "//*[@id='ajax-cart__content']/form/div[1]/h2/span")
    private ExtendedWebElement cartSummary;

    @FindBy(xpath = "//*[@id='shopify-section-cart__main']/section/div[2]/div/h2")
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
