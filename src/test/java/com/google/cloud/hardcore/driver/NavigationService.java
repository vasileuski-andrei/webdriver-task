package com.google.cloud.hardcore.driver;

import com.google.cloud.hardcore.page.BasePage;
import com.google.cloud.hardcore.page.HomePage;
import com.google.cloud.hardcore.page.TenMinutePage;
import com.google.cloud.hardcore.page.UndefinedPage;
import com.google.cloud.hardcore.waits.WaitingForEvents;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigationService {

    private WebDriver driver;
    private Map<String, BasePage> pageUrl;
    private WaitingForEvents waitingForEvents;

    public NavigationService(WebDriver driver) {
        this.driver = driver;
        pageUrl = new HashMap();
        pageUrl.put("https://cloud.google.com/", new HomePage());
        pageUrl.put("https://10minutemail.com", new TenMinutePage());
        waitingForEvents = new WaitingForEvents(driver);
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

    public void switchToNextTab() {
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int currentTab = tabs.indexOf(driver.getWindowHandle());
        driver.switchTo().window(tabs.get(currentTab + 1));
    }

    public void switchToPreviousTab() {
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int currentTab = tabs.indexOf(driver.getWindowHandle());
        if (currentTab != 0) {
            driver.switchTo().window(tabs.get(currentTab - 1));
        }
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
        waitingForEvents.waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        driver.switchTo().frame(frameName);
    }

}
