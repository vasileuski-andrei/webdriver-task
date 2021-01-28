package com.google.cloud.hardcore.waits;

import com.google.cloud.hardcore.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    private WebDriver driver;
    private int waitTimeoutSeconds;

    public Waits(WebDriver driver) {
        this.driver = driver;
        waitTimeoutSeconds = new BasePage().getWAIT_TIMEOUT_SECONDS();
    }

    public void waitForAppearanceElement(WebElement element) {
        new WebDriverWait(driver, waitTimeoutSeconds)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAppearanceElementAndClick(WebElement element) {
        new WebDriverWait(driver, waitTimeoutSeconds)
                .until(ExpectedConditions.visibilityOf(element)).click();
    }

}
