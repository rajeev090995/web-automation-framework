package com.ui.pages;

import static com.constants.Env.QA;
import static com.utility.PropertiesUtil.readPropertiesFile;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.JsonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR =By.xpath("//a[contains(text(),\"Sign\")]");

    public HomePage(Browser browserName,boolean isHeadless) {
        super(browserName,isHeadless); // to call the parent class constructor from the child class
        //goToWebsite(readPropertiesFile(QA,"URL"));
        goToWebsite(JsonUtility.readJSON(QA).getUrl());
    }

    public HomePage( WebDriver lambdaDriver) {
      super(lambdaDriver);
      goToWebsite(JsonUtility.readJSON(QA).getUrl());
    }


    public LoginPage goToLoginPage(){
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver()); //giving session to nxt page
        return loginPage;
    }





}
