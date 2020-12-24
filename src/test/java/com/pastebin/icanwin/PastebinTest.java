package com.pastebin.icanwin;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get("https://pastebin.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void checkWebsiteTitle() {
        Assert.assertTrue(driver.getTitle().equals("Pastebin.com - #1 paste tool since 2002!"));
    }

    @Test
    public void createNewPaste() {

        homePage.inputCode("Hello from WebDriver");
        homePage.clickDropdownMenuPasteExpiration();
        homePage.selectElementFromDropDownMenuPasteExpiration("10 Minutes");
        homePage.inputPasteName("helloweb");
        homePage.clickButtonCreateNewPaste();
        homePage.waitNotificationOfCreationNewPaste();

        Assert.assertTrue(driver.findElements((By.xpath("//div[@class='notice -success -post-view']"))).size() > 0);
    }

    @After
    public void close() {
        driver.quit();
    }

}
