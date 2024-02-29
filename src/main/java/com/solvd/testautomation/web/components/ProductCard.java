package com.solvd.testautomation.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.testautomation.web.components.ProductCard.class);

    @FindBy(xpath = ".//*[@class = 'isp_product_info']//*[text()]")
    private ExtendedWebElement titleElement;

    @FindBy(xpath = ".//*[@class='product-price']")
    private ExtendedWebElement priceElement;

    @FindBy(xpath = ".//*[@class='product-image__wrapper']")
    private ExtendedWebElement mainImage;

    @FindBy(xpath = ".//*[@id='shopify-section-collection__main']/div/div[1]/div/div[1]/div[1]/div[1]/div/a/div[2]/img")
    private ExtendedWebElement hoverImage;

    @FindBy(xpath = ".//*[@aria-label='Select Option']")
    private ExtendedWebElement selectOptionsButton;

    @FindBy(xpath = "//a[contains(@class, 'add-to-cart-text') and contains(text(), 'SELECT SIZE')]")
    private ExtendedWebElement selectSizeButton;

    @FindBy(xpath = ".//*[@data-label='ADD TO BAG']")
    private ExtendedWebElement addToCartButton;


    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public ExtendedWebElement getTitleElement() {
        return titleElement;
    }

    public String getProductPrice() {
        return priceElement.getText();
    }

    public ExtendedWebElement getMainImage() {
        return mainImage;
    }

    public void hoverOverMainImage() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(mainImage).perform();
    }

    public boolean isHoverImageDisplayed() {
        return hoverImage.isElementPresent(1);
    }

    public boolean isSelectOptionsDisplayed() {
        return selectOptionsButton.isElementPresent(1);
    }

    public boolean isSelectSizeDisplayed() {
        return selectSizeButton.isElementPresent(1);
    }

    public void clickOrPass() {
        if (selectSizeButton.isElementPresent(2)) {
            selectSizeButton.click();
        } else if (selectOptionsButton.isElementPresent(2)) {
            selectOptionsButton.click();
        } else {
            LOGGER.info("No suitable button found. Skipping click operation.");
        }
    }
}
