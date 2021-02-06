package com.google.cloud.hardcore.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TenMinutePage extends BasePage {

    @FindBy(xpath = "//div[@id='copy_address']")
    private WebElement copiedEmailAddress;

    @FindBy(xpath = "//table[@class='quote']//td[not(@colspan)]/h3")
    private WebElement estimatedBill;

    @FindBy(xpath = "//div[@class='message_top']")
    private WebElement inboxLetter;

    public void copyEmailAddress() {
        waitingForEvents.waitFor(ExpectedConditions.visibilityOf(copiedEmailAddress));
        copiedEmailAddress.click();
    }

    public void waitForALetter() {
        waitingForEvents.waitFor(ExpectedConditions.visibilityOf(inboxLetter));
        inboxLetter.click();
    }

    public String getEstimatedCostPerMonthFromEmail() {
        return estimatedBill.getText();
    }

}
