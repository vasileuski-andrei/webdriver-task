package com.google.cloud.hardcore.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingForEvents {

    public static final int WAIT_TIMEOUT_SECONDS = 15;
    private WebDriver driver;

    public WaitingForEvents(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForCondition(ExpectedCondition condition) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(condition);
    }

    public void waitForAppearanceElement(WebElement element) {
        waitForCondition(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAppearanceElementLocatedBy(By by) {
        waitForCondition(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForAppearanceFrame(int frameLocator) {
        waitForCondition(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }



}
