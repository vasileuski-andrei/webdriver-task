package com.pastebin.icanwin;

import com.pastebin.icanwin.properties.ConfigProperties;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

//Открыть https://pastebin.com или аналогичный сервис в любом браузере
//        Создать New Paste со следующими деталями:
//        * Код: "Hello from WebDriver"
//        * Paste Expiration: "10 Minutes"
//        * Paste Name / Title: "helloweb"

public class PastebinTest {

    public WebDriver driver;
    public HomePage homePage;

    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get(ConfigProperties.getProperty("website"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void checkWebsiteTitle() {
        Assert.assertTrue(driver.getTitle().equals(ConfigProperties.getProperty("websitetitle")));
    }

    @Test
    public void createNewPaste() {

        homePage.inputCode(ConfigProperties.getProperty("code"));
        homePage.clickDropdownMenuPasteExpiration();
        homePage.clickDropDownMenuElement();
        homePage.inputTitle(ConfigProperties.getProperty("title"));
        homePage.clickButtonCreateNewPaste();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href=\"/signup\"]")));

        //Assert.assertTrue(driver)  проверяем что есть определенный элемент на странице, который появляется при создании новой пасты.
    }

    @After
    public void close() {
        driver.quit();
    }

}
