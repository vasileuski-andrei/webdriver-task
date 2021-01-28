package com.google.cloud.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinutePage extends BasePage {

    public TenMinutePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='copy_address']")
    private WebElement copiedEmailAddress;

    @FindBy(xpath = "//table[@class='quote']//td[not(@colspan)]/h3")
    private WebElement estimatedBill;

    public WebElement getCopiedEmailAddress() {
        return copiedEmailAddress;
    }

    public void waitForALetter() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message_top']"))).click();
    }

    public String getEstimatedCostPerMonthFromEmail() {
        return estimatedBill.getText();
    }

}
