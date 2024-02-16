package com.solvd.testautomation.web;

import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

public class IframeTest extends AbstractTest {

    @Test
    public void verifyIframeElementsTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        driver.get("https://www.tiktok.com/@sanrio");
        WebElement iframeElement = driver.findElement(By.xpath("//*[@id='app']/div[2]/div[1]/div/div[2]/div"));
        driver.switchTo().frame(iframeElement);

        IframePage iframePage = new IframePage(driver);
        String mainWindowHandle = driver.getWindowHandle();
        iframePage.getPrivacyPolicyLink().click();

        switchToNewTab(driver, mainWindowHandle);
        TikTokPage tikTokPage = new TikTokPage(driver);

        sa.assertTrue(driver.getCurrentUrl().contains("privacy-policy"), "URL does not contain 'privacy-policy'");
        sa.assertTrue(tikTokPage.isTitleTextDisplayed(), "Title text is not displayed on the page");
        sa.assertEquals(tikTokPage.getTitleText(),
                "Privacy Policy", "Incorrect page title after navigating to Privacy Policy page");

        sa.assertAll();
    }

    private void switchToNewTab(WebDriver driver, String mainWindowHandle) {
        Set<String> windowHandles = driver.getWindowHandles();

        String newTabHandle = windowHandles.stream()
                .filter(handle -> !handle.equals(mainWindowHandle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No new tab found"));

        driver.switchTo().window(newTabHandle);
    }
}
