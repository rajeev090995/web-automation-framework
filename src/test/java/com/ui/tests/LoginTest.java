package com.ui.tests;

import com.ui.listeners.MyRetryAnalyzer;
import static org.testng.Assert.*;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase{

  @Test(description = "Verify that user is able to login with valid credentials",
      groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider"
  ,retryAnalyzer = MyRetryAnalyzer.class)
  public void loginTest(User user) {

    assertEquals( homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword())
        .getUserName(),"Rajeev Kumar");

  }

//  @Test(description = "Verify that user is able to login with valid credentials",
//      groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
//  public void loginCSVTest(User user) {
//
//    assertEquals( homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword())
//        .getUserName(),"Rajeev Kumar");
//  }
//
//  @Test(description = "Verify that user is able to login with valid credentials",
//      groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider")
//  public void loginExcelTest(User user) {
//
//    assertEquals( homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword())
//        .getUserName(),"Rajeev Kumar");
//  }
}
