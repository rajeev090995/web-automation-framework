package com.ui.pages;

import com.constants.Browser;
import com.utility.BrowserUtility;
import org.openqa.selenium.By;

public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR =By.xpath("//a[contains(text(),\"Sign\")]");

    public HomePage(Browser browserName) {
        super(browserName); // to call the parent class constructor from the child class
        goToWebsite("http://www.automationpractice.pl");
    }


    public LoginPage goToLoginPage(){
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver()); //giving session to nxt page
        return loginPage;
    }





}
