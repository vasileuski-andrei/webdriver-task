package com.google.cloud.hardcore.waits;

import com.google.cloud.hardcore.driver.NavigationService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingForEvents {

    private WebDriver driver;

    public WaitingForEvents(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForAppearanceElement(WebElement element) {
        new WebDriverWait(driver, NavigationService.WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAppearanceElementAndClick(WebElement element) {
        new WebDriverWait(driver, NavigationService.WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void waitForAppearanceElementLocatedBy(By by) {
        new WebDriverWait(driver, NavigationService.WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForAppearanceElementLocatedByAndClick(By by) {
        new WebDriverWait(driver, NavigationService.WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    public void waitForAppearanceInvisibilityOfElementLocatedBy(By by) {
        new WebDriverWait(driver, NavigationService.WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
