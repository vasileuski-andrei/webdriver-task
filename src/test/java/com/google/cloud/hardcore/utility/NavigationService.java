package com.google.cloud.hardcore.utility;

import com.google.cloud.hardcore.page.BasePage;
import com.google.cloud.hardcore.page.HomePage;
import com.google.cloud.hardcore.page.TenMinutePage;
import com.google.cloud.hardcore.page.UndefinedPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigationService {

    private WebDriver driver;
    private Map<String, BasePage> pageUrl;

    public NavigationService(WebDriver driver) {
        this.driver = driver;
        pageUrl = new HashMap();
        pageUrl.put("https://cloud.google.com/", new HomePage(driver));
        pageUrl.put("https://10minutemail.com", new TenMinutePage(driver));
    }

    public <T extends BasePage> T openPage(String url) {
        driver.get(url);
        if (pageUrl.containsKey(url)) {
            return (T) pageUrl.get(url);
        }
        return (T) new UndefinedPage();
    }

    public void openNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");
    }

    public void switchToTab(int tabIndex) {
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }

    /**
     * The title of this website contains "non-breaking space" so I used "replace" '\u00a0' to make it easier to read and compare.
     */
    public Boolean isWebsiteCorrect() {
        return driver.getTitle().replace(Character.toString('\u00a0'), "").equals("Cloud Computing Services | Google Cloud");
    }

    public void pasteCopiedData(WebElement field) {
        field.sendKeys(Keys.LEFT_CONTROL + "v");
    }

    public void switchToFrame(String frameName) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)).switchTo().frame(frameName);
    }

}
