package com.google.cloud.hardcore.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingleton {

    private static final String RESOURCES_PATH = "src/test/resources/drivers/";
    private static WebDriver driver;

    private DriverSingleton() {

    }

    public static WebDriver getDriver() {

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

}
