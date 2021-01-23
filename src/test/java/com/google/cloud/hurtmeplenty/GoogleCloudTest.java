package com.google.cloud.hurtmeplenty;

import com.google.cloud.hurtmeplenty.page.GoogleCloudHomePage;
import com.google.cloud.hurtmeplenty.page.GoogleCloudPricingCalculatorPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

// 1.Открыть https://cloud.google.com/ +++
// 2.Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator" +++
// 3.Запустить поиск, нажав кнопку поиска. +++
// 4.В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора. +++
// 5.Активировать раздел COMPUTE ENGINE вверху страницы +++
// 6.Заполнить форму следующими данными:
//  * Number of instances: 4 +++
//  * What are these instances for?: оставить пустым +++
//  * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS +++
//  * VM Class: Regular +++
//  * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB) +++
//  * Выбрать Add GPUs +++
//  * Number of GPUs: 1 +++
//  * GPU type: NVIDIA Tesla V100 +++
//  * Local SSD: 2x375 Gb +++
//  * Datacenter location: Frankfurt (europe-west3) +++
//  * Commited usage: 1 Year +++
// 7.Нажать Add to Estimate +++
// 8.Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
// 9.Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.

public class GoogleCloudTest {

    public WebDriver driver;
    public GoogleCloudHomePage cloudHomePage;
    public GoogleCloudPricingCalculatorPage pricingCalculatorPage;

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testPricingCalculator() {

        String expectedVirtualMachineClass = "regular";
        String expectedInstanceType = "n1-standard-8";
        String expectedDataCenterLocation = "Frankfurt";
        String expectedLocalSSD = "2x375";
        String expectedCommitmentTerm = "1 Year";
        String expectedEstimatedCostPerMonth = "1,082.77";

        cloudHomePage = new GoogleCloudHomePage(driver);
        cloudHomePage.openPage();
        Assert.assertTrue(cloudHomePage.isWebsiteCorrect());
        pricingCalculatorPage = cloudHomePage.searchPricingCalculatorPage("Google Cloud Platform Pricing Calculator");

        pricingCalculatorPage.waitForOpenPricingCalculatorPageAndSwitchToFrame();

        pricingCalculatorPage.clickSectionComputeEngine();

        String numberOfInstances = "4";
        pricingCalculatorPage.inputNumberOfInstances(numberOfInstances);

        String operatingSystemAndSoftware = "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";
        pricingCalculatorPage.selectElementFromMenuOperatingSystemAndSoftware(operatingSystemAndSoftware);

        String machineClass = "Regular";
        pricingCalculatorPage.selectElementFromMenuMachineClass(machineClass);

        String series = "N1";
        pricingCalculatorPage.selectElementFromMenuSeries(series);

        String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
        pricingCalculatorPage.selectElementFromMenuMachineType(machineType);

        pricingCalculatorPage.selectCheckBoxAddGPU();

        pricingCalculatorPage.selectElementFromMenuNumberOfGPU();

        String typeGPU = "NVIDIA Tesla V100";
        pricingCalculatorPage.selectElementFromMenuTypeGPU(typeGPU);

        String localSSD = "2x375 GB";
        pricingCalculatorPage.selectElementFromMenuLocalSSD(localSSD);

        String datacenterLocation = "Frankfurt (europe-west3)";
        pricingCalculatorPage.selectElementFromMenuDatacenterLocation(datacenterLocation);

        String commitedUsage = "1 Year";
        pricingCalculatorPage.selectElementFromMenuCommitedUsage(commitedUsage);

        pricingCalculatorPage.clickAddToEstimateButton();

        Assert.assertTrue(pricingCalculatorPage.isVirtualMachineClassCorrect(expectedVirtualMachineClass));
        Assert.assertTrue(pricingCalculatorPage.isInstanceTypeCorrect(expectedInstanceType));
        Assert.assertTrue(pricingCalculatorPage.isDataCenterLocationCorrect(expectedDataCenterLocation));
        Assert.assertTrue(pricingCalculatorPage.isLocalSSDCorrect(expectedLocalSSD));
        Assert.assertTrue(pricingCalculatorPage.isCommitmentTermCorrect(expectedCommitmentTerm));
        Assert.assertTrue(pricingCalculatorPage.isEstimatedCostPerMonthCorrect(expectedEstimatedCostPerMonth));

    }

    @After
    public void close() {
        driver.quit();
    }

}
