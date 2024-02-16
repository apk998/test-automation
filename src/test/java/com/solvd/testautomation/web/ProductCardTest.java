package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.SearchLineComponent;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductCardTest extends AbstractTest {

    @Test(description = "verify that all links of UI object (product card) work correctly")
    public void verifyProductCardFeaturesTest() {
        String searchInput = "tee";
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLineComponent component = homePage.getHeader().getComponent();
        component.typeSearchInputValue(searchInput);
        SearchPage searchPage = component.clickSearchButton();

        searchPage.getCards().forEach(card -> {
            // Perform interactions and assertions on each product card
            card.hoverOverMainImage();
            sa.assertTrue(card.isHoverImageDisplayed(), "Hover image is not displayed after hovering over the main image");
            card.clickOrPass();
            if (card.isSelectSizeDisplayed() || card.isSelectOptionsDisplayed()) {
                sa.assertTrue(driver.getCurrentUrl().startsWith("https://www.sanrio.com/products/"),
                        "Incorrect URL after clicking select size/options button");
            }

            // Click on the title
            driver.navigate().back();
            card.getTitleElement().click();
            sa.assertTrue(driver.getCurrentUrl().startsWith("https://www.sanrio.com/products/"),
                    "Incorrect URL after clicking on the title");

            // Click on the main image
            driver.navigate().back();
            card.getMainImage().click();
            sa.assertTrue(driver.getCurrentUrl().startsWith("https://www.sanrio.com/products/"),
                    "Incorrect URL after clicking on the main image");
        });
        sa.assertAll();
    }
}
