package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.ProductCard;
import com.solvd.testautomation.web.components.SearchLineComponent;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePageTest extends AbstractTest {

    @Test
    public void verifySearchLineTest() {
        String productType = "Kuromi";

        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage page = new HomePage(driver);
        page.open();

        SearchLineComponent component = page.getHeader().getComponent();
        Assert.assertTrue(component.getSearchInput().isElementPresent(1), "Search input is not present");
        sa.assertEquals(component.getSearchInputPlaceholder(), "What are you looking for?", "Search input has an incorrect placeholder");
        Assert.assertTrue(component.getSearchButton().isElementPresent(1), "Search button is not present");

        component.typeSearchInputValue(productType);
        SearchPage searchPage = component.clickSearchButton();

        sa.assertTrue(driver.getCurrentUrl().contains(productType.toLowerCase()), "URL does not contain product type");

        List<ProductCard> cards = searchPage.getCards();
        for (ProductCard card : cards) {
            sa.assertTrue(card.getTitleText().toLowerCase().contains(productType.toLowerCase()),
                    String.format("Product with name %s doesn't contain the product type in title", card.getTitleText()));
        }

        sa.assertAll();
    }

    @Test
    public void verifyNavigationTest() {

    }

    @Test
    public void verifyFilterTest() {

    }

    @Test
    public void verifyFooterLinkTest() {

    }
}
