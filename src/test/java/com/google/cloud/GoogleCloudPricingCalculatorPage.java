package com.google.cloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage {

    public WebDriver driver;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//label[contains(text(),'Number of instances')]")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//md-checkbox[div[contains(text(), 'Add GPUs')]]")
    WebElement checkBoxAddGPU;

    public void enterNumberOfInstances(String number) {
        numberOfInstances.sendKeys(number);
    }

    public void selectCheckBoxAddGPU() {
        checkBoxAddGPU.click();
    }

    public void clickNumberOfInstances() {
        numberOfInstances.click();
    }

    public void waitForOpenPricingCalculatorPage() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h2[contains(text(), 'Google Cloud Pricing Calculator')]")));
    }

}
