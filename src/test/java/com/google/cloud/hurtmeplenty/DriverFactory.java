package com.google.cloud.hurtmeplenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            driver= new ChromeDriver();
        }

        return driver;
    }

}
