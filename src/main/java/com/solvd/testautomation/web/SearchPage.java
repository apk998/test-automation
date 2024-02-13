package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.ProductCard;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='boost-pfs-filter-tree']/div/div[3]/div[1]/div[2]/div/ul/li[1]")
    private ExtendedWebElement underTenFilter;

    @FindBy(xpath = "//*[@id = 'isp_search_result_page_container']")
    private List<ProductCard> cards;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getCards() {
        return cards;
    }

    public void applyUnderTenFilter() {
        underTenFilter.click();
    }
}
