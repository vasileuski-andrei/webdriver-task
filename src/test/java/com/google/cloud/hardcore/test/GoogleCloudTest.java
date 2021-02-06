package com.google.cloud.hardcore.test;

import org.junit.Assert;
import org.junit.Test;

// 1. Открыть https://cloud.google.com/
// 2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
// 3. Запустить поиск, нажав кнопку поиска.
// 4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
// 5. Активировать раздел COMPUTE ENGINE вверху страницы
// 6. Заполнить форму следующими данными:
//  * Number of instances: 4
//  * What are these instances for?: оставить пустым
//  * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
//  * VM Class: Regular
//  * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
//  * Выбрать Add GPUs
//  * Number of GPUs: 1
//  * GPU type: NVIDIA Tesla V100
//  * Local SSD: 2x375 Gb
//  * Datacenter location: Frankfurt (europe-west3)
//  * Commited usage: 1 Year
// 7. Нажать Add to Estimate

// 8. Выбрать пункт EMAIL ESTIMATE
// 9. В новой вкладке открыть https://10minutemail.com или аналогичный сервис для генерации временных email'ов
// 10. Скопировать почтовый адрес сгенерированный в 10minutemail
// 11. Вернуться в калькулятор, в поле Email ввести адрес из предыдущего пункта
// 12. Нажать SEND EMAIL
// 13. Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме совпадает с тем, что отображается в калькуляторе

public class GoogleCloudTest extends BaseTest {

    @Test
    public void testPricingCalculator() {

        String expectedVirtualMachineClass = "regular";
        String expectedInstanceType = "n1-standard-8";
        String expectedDataCenterLocation = "Frankfurt";
        String expectedLocalSSD = "2x375";
        String expectedCommitmentTerm = "1 Year";
        String expectedEstimatedCostPerMonth = "USD 1,082.77";

        homePage = navigationService.openPage("https://cloud.google.com/");
        Assert.assertTrue(navigationService.isWebsiteCorrect());
        pricingCalculatorPage = homePage.searchPricingCalculatorPage("Google Cloud Platform Pricing Calculator");
        navigationService.switchToFrame("myFrame");
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

        emailYourEstimatePage = pricingCalculatorPage.clickEmailEstimateButton();
        emailYourEstimatePage.waitForAppearanceOfEmailYourEstimateForm();

        navigationService.openNewTab();
        navigationService.switchToNextTab();
        tenMinutePage = navigationService.openPage("https://10minutemail.com");
        tenMinutePage.copyEmailAddress();

        navigationService.switchToPreviousTab();
        navigationService.switchToFrame("myFrame");
        navigationService.pasteCopiedData(emailYourEstimatePage.getEmailField());

        emailYourEstimatePage.clickSendEmailButton();

        navigationService.switchToNextTab();

        tenMinutePage.waitForALetter();
        String estimatedCostPerMonthFromEmail = tenMinutePage.getEstimatedCostPerMonthFromEmail();

        navigationService.switchToPreviousTab();
        navigationService.switchToFrame("myFrame");

        Assert.assertTrue(pricingCalculatorPage.isVirtualMachineClassCorrect(expectedVirtualMachineClass));
        Assert.assertTrue(pricingCalculatorPage.isInstanceTypeCorrect(expectedInstanceType));
        Assert.assertTrue(pricingCalculatorPage.isDataCenterLocationCorrect(expectedDataCenterLocation));
        Assert.assertTrue(pricingCalculatorPage.isLocalSSDCorrect(expectedLocalSSD));
        Assert.assertTrue(pricingCalculatorPage.isCommitmentTermCorrect(expectedCommitmentTerm));
        Assert.assertTrue(pricingCalculatorPage.isEstimatedCostPerMonthCorrect(expectedEstimatedCostPerMonth));
        Assert.assertTrue(pricingCalculatorPage.isEstimatedCostPerMonthCorrect(estimatedCostPerMonthFromEmail));

    }

}
