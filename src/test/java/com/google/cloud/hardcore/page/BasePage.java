package com.google.cloud.hardcore.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 15;

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getWAIT_TIMEOUT_SECONDS() {
        return WAIT_TIMEOUT_SECONDS;
    }

}
