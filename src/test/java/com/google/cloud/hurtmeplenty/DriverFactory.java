package com.google.cloud.hurtmeplenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class DriverFactory {

    public static WebDriver getDriver(String name) {
        WebDriver driver = null;

        if ("chrome".equalsIgnoreCase(name)) {
            driver= new ChromeDriver();
        }

        return driver;

    }

}
