package com.google.cloud.hurtmeplenty;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        cloudHomePage = new GoogleCloudHomePage(driver);
        pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://cloud.google.com/");

    }

    @Test
    public void checkWebsiteTitle() {
        Assert.assertTrue(cloudHomePage.isWebsiteCorrect());
    }

    @Test
    public void testPricingCalculator() {

        cloudHomePage.clickSearchButton();
        cloudHomePage.inputSearchText("Google Cloud Platform Pricing Calculator \n");
        cloudHomePage.waitSearchResults();
        cloudHomePage.clickSearchResultLink();

        pricingCalculatorPage.waitForOpenPricingCalculatorPageAndSwitchToFrame();
        pricingCalculatorPage.clickSectionComputeEngine();
        pricingCalculatorPage.inputNumberOfInstances("4");

        pricingCalculatorPage.clickMenuOperatingSystemAndSoftware();
        pricingCalculatorPage.selectElementFromMenuOperatingSystemAndSoftware("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS");

        pricingCalculatorPage.clickMenuMachineClass();
        pricingCalculatorPage.selectElementFromMenuMachineClass("Regular");

        pricingCalculatorPage.clickMenuSeries();
        pricingCalculatorPage.selectElementFromMenuSeries("N1");

        pricingCalculatorPage.clickMenuMachineType();
        pricingCalculatorPage.waitForAppearanceRequiredTypeOfGPU();
        pricingCalculatorPage.selectElementFromMenuMachineType("n1-standard-8 (vCPUs: 8, RAM: 30GB)");

        pricingCalculatorPage.selectCheckBoxAddGPU();

        pricingCalculatorPage.waitForAppearanceMenuNumberOfGPU();
        pricingCalculatorPage.clickMenuNumberOfGPU();
        pricingCalculatorPage.selectElementFromMenuNumberOfGPU();

        pricingCalculatorPage.waitForAppearanceMenuTypeGPU();
        pricingCalculatorPage.clickMenuTypeGPU();
        pricingCalculatorPage.selectElementFromMenuTypeGPU("NVIDIA Tesla V100");

        pricingCalculatorPage.clickMenuLocalSSD();
        pricingCalculatorPage.selectElementFromMenuLocalSSD("2x375 GB");

        pricingCalculatorPage.clickMenuDatacenterLocation();
        pricingCalculatorPage.selectElementFromMenuDatacenterLocation("Frankfurt (europe-west3)");

        pricingCalculatorPage.clickMenuCommitedUsage();
        pricingCalculatorPage.selectElementFromMenuCommitedUsage("1 Year");

        pricingCalculatorPage.clickAddToEstimateButton();

        String virtualMachineClass = "regular";
        Assert.assertTrue(pricingCalculatorPage.isVirtualMachineClassCorrect(virtualMachineClass));

        String instanceType = "n1-standard-8";
        Assert.assertTrue(pricingCalculatorPage.isInstanceTypeCorrect(instanceType));

        String dataCenterLocation = "Frankfurt";
        Assert.assertTrue(pricingCalculatorPage.isDataCenterLocationCorrect(dataCenterLocation));

        String localSSD = "2x375";
        Assert.assertTrue(pricingCalculatorPage.isLocalSSDCorrect(localSSD));

        String commitmentTerm = "1 Year";
        Assert.assertTrue(pricingCalculatorPage.isCommitmentTermCorrect(commitmentTerm));

        String estimatedCostPerMonth = "1,082.77";
        Assert.assertTrue(pricingCalculatorPage.isEstimatedCostPerMonthCorrect(estimatedCostPerMonth));

    }

    @After
    public void close() {
        driver.quit();
    }

}
