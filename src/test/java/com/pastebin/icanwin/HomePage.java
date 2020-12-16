package com.pastebin.icanwin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "postform-text")
    private WebElement codeField;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement dropDownMenuPasteExpiration;

    @FindBy(xpath = "//li[contains (text(),'10 Minutes')]")
    private WebElement dropDownMenuElement;

    @FindBy(id = "postform-name")
    private WebElement titleField;

    @FindBy(xpath = "//button[contains (text(),'Create New Paste')]")
    private WebElement buttonCreateNewPaste;

    public void inputCode(String code) {
        codeField.sendKeys(code);
    }

    public void inputTitle(String title) {
        titleField.sendKeys(title);
    }

    public void clickDropdownMenuPasteExpiration() {
        dropDownMenuPasteExpiration.click();
    }

    public void clickDropDownMenuElement() {
        dropDownMenuElement.click();
    }

    public void clickButtonCreateNewPaste() {
        buttonCreateNewPaste.click();
    }

}
