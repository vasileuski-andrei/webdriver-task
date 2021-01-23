package com.google.cloud.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://cloud.google.com/";

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchFieldAndButton;

    @FindBy(xpath = "//div[@class='gs-title']/a[@href='https://cloud.google.com/products/calculator']")
    private WebElement searchResult;

    public GoogleCloudHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='q']")));
        return this;
    }

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

    /**
     * The title of this website contains "non-breaking space" so I used "replace" '\u00a0' to make it easier to read and compare.
     */
    public Boolean isWebsiteCorrect() {
        return driver.getTitle().replace(Character.toString('\u00a0'), "").equals("Cloud Computing Services | Google Cloud");
    }

    public GoogleCloudPricingCalculatorPage searchPricingCalculatorPage(String webPage) {
        clickSearchButton();
        inputSearchText(webPage);
        waitSearchResults();
        clickSearchResultLink();

        return new GoogleCloudPricingCalculatorPage(driver);
    }
    

}
