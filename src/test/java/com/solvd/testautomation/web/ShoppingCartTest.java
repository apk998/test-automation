package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.Header;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends AbstractTest {

    @Test
    public void verifyAddToCartTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        String productUrl = "https://www.sanrio.com/collections/my-melody/products/my-melody-airpods-case";
        driver.get(productUrl);
        HomePage homePage = new HomePage(driver);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        Header header = homePage.getHeader();
        int expectedItemCount = 2;   // During the month of February they give an extra freebie
        int actualItemCount = header.getCartItemCount();

        sa.assertEquals(actualItemCount, expectedItemCount, "Cart icon does not display the correct number of items");
        sa.assertAll();
    }

    @Test
    public void verifyItemRemovalTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        String productUrl = "https://www.sanrio.com/collections/my-melody/products/my-melody-airpods-case";
        driver.get(productUrl);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        cartPage.removeCartItem();

        sa.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing the item");
        sa.assertAll();
    }
}
