package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

public final class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_LOGIN_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final By LOGIN_ERROR_LOCATOR = By.xpath("//div[contains(@class,'alert-danger')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress,String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR,emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR,password);
        clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }
}
