package com.google.cloud.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TenMinutePage extends BasePage {

    @FindBy(xpath = "//div[@id='copy_address']")
    private WebElement copiedEmailAddress;

    @FindBy(xpath = "//table[@class='quote']//td[not(@colspan)]/h3")
    private WebElement estimatedBill;

    public WebElement getCopiedEmailAddress() {
        return copiedEmailAddress;
    }

    public void waitForALetter() {
        waitingForEvents.waitForAppearanceElementLocatedByAndClick(By.xpath("//div[@class='message_top']"));
    }

    public String getEstimatedCostPerMonthFromEmail() {
        return estimatedBill.getText();
    }

}
