package com.google.cloud.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchFieldAndButton;

    @FindBy(xpath = "//div[@class='gs-title']/a[@href='https://cloud.google.com/products/calculator']")
    private WebElement searchResult;

    public void clickSearchButton() {
        searchFieldAndButton.click();
    }

    public void clickSearchResultLink() {
        searchResult.click();
    }

    public void inputSearchText(String searchText) {
        searchFieldAndButton.sendKeys(searchText, Keys.ENTER);
    }

    public void waitSearchResults() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='gs-title']//b[text()='Google Cloud Platform Pricing Calculator']")));
    }

    public PricingCalculatorPage searchPricingCalculatorPage(String webPage) {
        clickSearchButton();
        inputSearchText(webPage);
        waitSearchResults();
        clickSearchResultLink();

        return new PricingCalculatorPage(driver);
    }

}
