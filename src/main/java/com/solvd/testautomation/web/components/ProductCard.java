package com.solvd.testautomation.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {

    @FindBy(xpath = ".//*[@class = 'isp_product_info']//*[text()]")
    private ExtendedWebElement titleElement;

    // Find more stable XPath?
    @FindBy(xpath = "//*[@id='shopify-section-collection__main']/div/div[1]/div/div[1]/div[2]/div")
    private ExtendedWebElement priceElement;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isTitleElementDisplayed() {
        return titleElement.isElementPresent(1);
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public String getProductPrice() {
        return priceElement.getText();
    }
}
