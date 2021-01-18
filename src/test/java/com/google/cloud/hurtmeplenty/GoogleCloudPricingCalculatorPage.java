package com.google.cloud.hurtmeplenty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleCloudPricingCalculatorPage {

    public WebDriver driver;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//md-card-content[@id='mainForm']//md-tab-item/div[@title='Compute Engine']")
    private WebElement sectionComputeEngine;

    @FindBy(xpath = "//form//input[@id='input_63']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//md-select-value//div[contains(text(), 'Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS')]")
    private WebElement menuOperatingSystemAndSoftware;

    @FindBy(xpath = "//*[@id='select_value_label_57']/span/div[text()='Regular']")
    private WebElement menuMachineClass;

    @FindBy(xpath = "//md-select[@name='series']")
    private WebElement series;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_60']")
    private WebElement machineType;

    @FindBy(xpath = "//md-checkbox/div[2]")
    private WebElement checkBoxAddGPU;

    @FindBy(xpath = "//*[@id='select_value_label_392']/span/div")
    private WebElement numberOfGPU;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_393']")
    private WebElement typeGPU;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_354']/span/div")
    private WebElement localSSD;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_61']")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_62']")
    private WebElement commitedUsage;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains (text(), 'Add to Estimate')]")
    private WebElement addToEstimateButton;

    public void inputNumberOfInstances(String number) {
        numberOfInstances.sendKeys(number);
    }

    public void selectCheckBoxAddGPU() {
        checkBoxAddGPU.click();
    }

    public void clickSectionComputeEngine() {
        sectionComputeEngine.click();
    }

    public void clickMenuOperatingSystemAndSoftware() {
        menuOperatingSystemAndSoftware.click();
    }

    public void clickMenuMachineClass() {
        menuMachineClass.click();
    }

    public void clickMenuSeries() {
        series.click();
    }

    public void clickMenuMachineType() {
        machineType.click();
    }

    public void clickMenuNumberOfGPU() {
        numberOfGPU.click();
    }

    public void clickMenuTypeGPU() {
        typeGPU.click();
    }

    public void clickMenuLocalSSD() {
        localSSD.click();
    }

    public void clickMenuDatacenterLocation() {
        datacenterLocation.click();
    }

    public void clickMenuCommitedUsage() {
        commitedUsage.click();
    }

    public void clickAddToEstimateButton() {
        addToEstimateButton.click();
    }

    public void selectElementFromMenuOperatingSystemAndSoftware(String element) {
        driver.findElement(By.xpath("//md-content/md-option/div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuMachineClass(String element) {
        driver.findElement(By.xpath("//md-option[@id='select_option_78' or @id='select_option_79']/div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuSeries(String element) {
        driver.findElement(By.xpath("//md-option//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuMachineType(String element) {
        driver.findElement(By.xpath("//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuNumberOfGPU() {
        driver.findElement(By.cssSelector("#select_option_399 > div.md-text.ng-binding")).click();
    }

    public void selectElementFromMenuTypeGPU(String element) {
        driver.findElement(By.xpath("//md-option//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuLocalSSD(String element) {
        driver.findElement(By.xpath("//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuDatacenterLocation(String element) {
        //driver.findElement(By.xpath("//md-content//md-option[@id='select_option_205']/div")).click();
        List<WebElement> dataCenterLocation = driver.findElements(By.xpath("//md-option/div[contains (text(),'" +element+ "')]"));
        dataCenterLocation.get(2).click();
    }

    public void selectElementFromMenuCommitedUsage(String element) {
        driver.findElement(By.xpath("//div[@id='select_container_100']//div[text()='" +element+ "']")).click();
    }

    public void waitForOpenPricingCalculatorPageAndSwitchToFrame() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)).switchTo().frame("myFrame");
    }

    public void waitForAppearanceRequiredTypeOfGPU() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-select//div[contains (text(), 'n1-standard-1 (vCPUs: 1, RAM: 3.75GB)')]")));
    }

    public void waitForAppearanceMenuNumberOfGPU() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/md-input-container/label[text()='Number of GPUs']")));
    }

    public void waitForAppearanceMenuTypeGPU() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-select-value[@id='select_value_label_393']")));
    }

    public Boolean isVirtualMachineClassCorrect(String virtualMachineClass) {
        return ("VM class: " + virtualMachineClass).equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[2]/div")).getText());
    }

    public Boolean isInstanceTypeCorrect(String instanceType) {
        return ("Instance type: " + instanceType).equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[3]/div")).getText());
    }

    public Boolean isDataCenterLocationCorrect(String region) {
        return ("Region: " + region).equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[4]/div")).getText());
    }

    public Boolean isLocalSSDCorrect(String localSSD) {
        return ("Total available local SSD space " + localSSD + " GiB").equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[5]/div")).getText());
    }

    public Boolean isCommitmentTermCorrect(String commitmentTerm) {
        return ("Commitment term: " + commitmentTerm).equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[6]/div")).getText());
    }

    public Boolean isEstimatedCostPerMonthCorrect(String cost) {
        return ("Estimated Component Cost: USD " + cost + " per 1 month").equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[7]/div")).getText());
    }


}
