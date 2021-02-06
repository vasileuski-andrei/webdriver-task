package com.google.cloud.hardcore.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailYourEstimatePage extends BasePage {

    @FindBy (xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains (text(), 'Send Email')]")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//form[@name='emailForm']")
    private WebElement emailYourEstimateForm;

    public void clickSendEmailButton() {
        sendEmailButton.click();
    }

    public void waitForAppearanceOfEmailYourEstimateForm() {
        waitingForEvents.waitFor(ExpectedConditions.visibilityOf(emailYourEstimateForm));
    }

    public WebElement getEmailField() {
        return emailField;
    }

}
