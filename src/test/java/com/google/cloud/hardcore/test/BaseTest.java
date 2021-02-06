package com.google.cloud.hardcore.test;

import com.google.cloud.hardcore.page.EmailYourEstimatePage;
import com.google.cloud.hardcore.page.HomePage;
import com.google.cloud.hardcore.page.PricingCalculatorPage;
import com.google.cloud.hardcore.page.TenMinutePage;
import com.google.cloud.hardcore.driver.DriverSingleton;
import com.google.cloud.hardcore.driver.NavigationService;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected PricingCalculatorPage pricingCalculatorPage;
    protected EmailYourEstimatePage emailYourEstimatePage;
    protected TenMinutePage tenMinutePage;
    protected NavigationService navigationService;

    @Before
    public void setup() {
        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        navigationService = new NavigationService(driver);
    }

    @After
    public void close() {
        DriverSingleton.closeDriver();
    }

}
