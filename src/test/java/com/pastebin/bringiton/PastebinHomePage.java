package com.pastebin.bringiton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {

    public WebDriver driver;
    private WebElement elementFromDropDownMenuSyntaxHighlighting;
    private WebElement elementFromDropDownMenuPasteExpiration;

    public PastebinHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "postform-text")
    private WebElement codeField;

    @FindBy(id = "select2-postform-format-container")
    private WebElement dropDownMenuSyntaxHighlighting;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement dropDownMenuPasteExpiration;

    @FindBy(id = "postform-name")
    private WebElement pasteNameField;

    @FindBy(xpath = "//button[contains (text(),'Create New Paste')]")
    private WebElement buttonCreateNewPaste;

    public void inputCode(String code) {
        codeField.sendKeys(code);
    }

    public void inputPasteName(String pasteName) {
        pasteNameField.sendKeys(pasteName);
    }

    public void clickDropdownMenuSyntaxHighlighting() {
        dropDownMenuSyntaxHighlighting.click();
    }

    public void clickDropdownMenuPasteExpiration() {
        dropDownMenuPasteExpiration.click();
    }

    public void selectElementFromDropDownMenuSyntaxHighlighting(String element) {
        elementFromDropDownMenuSyntaxHighlighting = driver.findElement(By.xpath("//li[contains (text(),'" +element+ "')]"));
        elementFromDropDownMenuSyntaxHighlighting.click();
    }

    public void selectElementFromDropDownMenuPasteExpiration(String element) {
        elementFromDropDownMenuPasteExpiration = driver.findElement(By.xpath("//li[contains (text(),'" +element+ "')]"));
        elementFromDropDownMenuPasteExpiration.click();
    }

    public void clickButtonCreateNewPaste() {
        buttonCreateNewPaste.click();
    }

    public void waitNotificationOfCreationNewPaste() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='notice -success -post-view']")));
    }

}
