package com.google.cloud.hardcore.page;

import com.google.cloud.hardcore.driver.DriverSingleton;
import com.google.cloud.hardcore.waits.WaitingForEvents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    protected WaitingForEvents waitingForEvents;

    public BasePage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        waitingForEvents = new WaitingForEvents(driver);
    }

}
