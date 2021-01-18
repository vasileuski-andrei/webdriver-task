package com.google.cloud.hurtmeplenty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {

    public WebDriver driver;

    public GoogleCloudHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchFieldAndButton;

    @FindBy(xpath = "//div[@class='gs-title']//b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement searchResult;

    public void clickSearchButton() {
        searchFieldAndButton.click();
    }

    public void clickSearchResultLink() {
        searchResult.click();
    }

    public void inputSearchText(String searchText) {
        searchFieldAndButton.sendKeys(searchText);
    }

    public void waitSearchResults() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='gs-title']//b[text()='Google Cloud Platform Pricing Calculator']")));
    }

    public Boolean isWebsiteCorrect() {
        return driver.getTitle().equals("Cloud Computing Services " + '\u00a0' + "|" + '\u00a0' + " Google Cloud");
    }

}
