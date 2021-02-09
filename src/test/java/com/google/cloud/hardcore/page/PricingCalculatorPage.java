package com.google.cloud.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PricingCalculatorPage extends BasePage {

    @FindBy(xpath = "//md-card-content[@id='mainForm']//md-tab-item/div[@title='Compute Engine']")
    private WebElement sectionComputeEngine;

    @FindBy(xpath = "//form//input[@id='input_63']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_56']")
    private WebElement menuOperatingSystemAndSoftware;

    @FindBy(xpath = "//md-select[@id='select_80']")
    private WebElement menuMachineClass;

    @FindBy(xpath = "//md-select[@name='series']")
    private WebElement series;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_60']")
    private WebElement machineType;

    @FindBy(xpath = "//md-checkbox/div[2]")
    private WebElement checkBoxAddGPU;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_392']")
    private WebElement numberOfGPU;

    @FindBy(xpath = "//md-select[@id='select_396']")
    private WebElement typeGPU;

    @FindBy(xpath = "//md-select[@id='select_355']")
    private WebElement localSSD;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_61']")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_62']")
    private WebElement commitedUsage;

    /**
     * This is the only one of the 12 buttons on this form that doesn't contain a tag "md-icon" so I used this approach to find it.
     */
    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[not(md-icon)]")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    public void switchToFrame(String frameName) {
        waitingForEvents.waitForAppearanceFrame(0);
        driver.switchTo().frame(frameName);
    }

    public void inputNumberOfInstances(String number) {
        numberOfInstances.sendKeys(number);
    }

    public void selectCheckBoxAddGPU() {
        checkBoxAddGPU.click();
    }

    public void clickSectionComputeEngine() {
        waitingForEvents.waitForAppearanceElement(sectionComputeEngine);
        sectionComputeEngine.click();
    }

    public void clickAddToEstimateButton() {
        addToEstimateButton.click();
    }

    public EmailYourEstimatePage clickEmailEstimateButton() {
        emailEstimateButton.click();
        return new EmailYourEstimatePage();
    }

    public void selectElementFromMenuOperatingSystemAndSoftware(String element) {
        menuOperatingSystemAndSoftware.click();
        By elementOperatingSystemLocatedBy = By.xpath("//md-content/md-option/div[contains (text(),'" +element+ "')]");
        waitingForEvents.waitForAppearanceElementLocatedBy(elementOperatingSystemLocatedBy);
        driver.findElement(elementOperatingSystemLocatedBy).click();
    }

    public void selectElementFromMenuMachineClass(String element) {
        menuMachineClass.click();
        driver.findElement(By.xpath("//md-option[@id='select_option_78' or @id='select_option_79']/div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuSeries(String element) {
        series.click();
        driver.findElement(By.xpath("//md-option//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuMachineType(String element) {
        machineType.click();
        waitingForEvents.waitForAppearanceElementLocatedBy(By.xpath("//md-select-value[@id='select_value_label_60']//div[contains (text(), 'n1')]"));
        driver.findElement(By.xpath("//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuNumberOfGPU() {
        waitingForEvents.waitForAppearanceElement(numberOfGPU);
        numberOfGPU.click();
        driver.findElement(By.cssSelector("#select_option_399 > div.md-text.ng-binding")).click();
    }

    public void selectElementFromMenuTypeGPU(String element) {
        typeGPU.click();
        waitingForEvents.waitForAppearanceElementLocatedBy(By.xpath("//md-select[@id='select_396' and @aria-expanded='true']"));
        driver.findElement(By.xpath("//md-option//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuLocalSSD(String element) {
        localSSD.click();
        driver.findElement(By.xpath("//div[contains (text(),'" +element+ "')]")).click();
    }

    public void selectElementFromMenuDatacenterLocation(String element) {
        datacenterLocation.click();
        By elementDatacenterLocatedBy = By.xpath("//md-select-menu[@class='md-overflow']//div[contains (text(),'" +element+ "')]");
        waitingForEvents.waitForAppearanceElementLocatedBy(elementDatacenterLocatedBy);
        driver.findElement(elementDatacenterLocatedBy).click();
    }

    public void selectElementFromMenuCommitedUsage(String element) {
        commitedUsage.click();
        driver.findElement(By.xpath("//div[@id='select_container_100']//div[text()='" +element+ "']")).click();
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
        return ("Estimated Component Cost: " + cost + " per 1 month").equals(driver.findElement(By.xpath("//md-content[@id='compute']//md-list-item[7]/div")).getText());
    }

}