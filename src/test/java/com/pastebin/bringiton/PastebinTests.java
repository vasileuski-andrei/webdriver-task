package com.pastebin.bringiton;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

//Открыть https://pastebin.com  или аналогичный сервис в любом браузере
//        Создать New Paste со следующими деталями:
//        * Код:
//        git config --global user.name  "New Sheriff in Town"
//        git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
//        git push origin master --force

//        * Syntax Highlighting: "Bash"
//        * Paste Expiration: "10 Minutes"
//        * Paste Name / Title: "how to gain dominance among developers"
//
//        3. Сохранить paste и проверить следующее:
//        * Заголовок страницы браузера соответствует Paste Name / Title
//        * Синтаксис подсвечен для bash
//        * Проверить что код соответствует введенному в пункте 2

public class PastebinTests {

    public WebDriver driver;
    public PastebinHomePage homePage;

    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new PastebinHomePage(driver);
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

        String browserPageTitle = "how to gain dominance among developers";
        String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
                      "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                      "git push origin master --force";

        homePage.inputCode(code);

        homePage.clickDropdownMenuSyntaxHighlighting();
        homePage.selectElementFromDropDownMenuSyntaxHighlighting("Bash");

        homePage.clickDropdownMenuPasteExpiration();
        homePage.selectElementFromDropDownMenuPasteExpiration("10 Minutes");

        homePage.inputPasteName(browserPageTitle);
        homePage.clickButtonCreateNewPaste();
        homePage.waitNotificationOfCreationNewPaste();

        Assert.assertTrue(driver.findElements((By.xpath("//div[@class='notice -success -post-view']"))).size() > 0);
        Assert.assertEquals(driver.getTitle(), browserPageTitle + " - Pastebin.com");
        Assert.assertFalse(driver.findElement(By.xpath("//span[contains (text(),'git config')]")).getCssValue("color").equals("rgba(0, 0, 0, 1)"));
        Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='bash']")).getText(), code);

    }

    @After
    public void close() {
        driver.quit();
    }

}
