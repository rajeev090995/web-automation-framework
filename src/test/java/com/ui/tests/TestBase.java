package com.ui.tests;

import static com.constants.Browser.CHROME;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

  protected HomePage homePage;
  Logger logger = LoggerUtility.getLogger(this.getClass());

  private boolean isLambdaTest = true;

  @Parameters({"browser","isLambdaTest","isHeadless"})
  @BeforeMethod(description = "Load the home page of the application")
  public void setUp(@Optional("chrome") String browser,
      @Optional("false") boolean isLambdaTest,
      @Optional("true") boolean isHeadless,ITestResult result) {
    WebDriver lambdaDriver;
    if(isLambdaTest){

      this.isLambdaTest = isLambdaTest;
      lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser,result.getMethod().getMethodName());
      homePage = new HomePage(lambdaDriver);
    }else {
      // Running the test in local machine
      logger.info("Load the homepage of website");
      homePage = new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless);
    }

  }

  public HomePage getInstance() {
    return homePage;
  }

  @AfterMethod(description = "Tear down the browser")
  public void tearDown(){
    if (isLambdaTest){
      LambdaTestUtility.quitSession();
    }else {
      //homePage.quit();
    }
    //homePage.quit();
  }

}
