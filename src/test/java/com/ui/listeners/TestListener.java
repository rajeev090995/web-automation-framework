package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.LoginTest;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import java.util.Arrays;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
  Logger logger = LoggerUtility.getLogger(LoginTest.class);
  ExtentSparkReporter extentSparkReporter; // job is to create html file - look style
  ExtentReports extentReports; // job is to create report and attach the reporter
  ExtentTest extentTest; // job is to store the test info and steps

  public void onTestStart(ITestResult result) {
    logger.info(result.getMethod().getMethodName());
    logger.info(result.getMethod().getDescription());

    logger.info(Arrays.toString(result.getMethod().getGroups()));

    ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
  }

  public void onTestSuccess(ITestResult result) {
    logger.info(result.getMethod().getMethodName() + " PASSED");
    ExtentReporterUtility.getTest().log(Status.PASS,result.getMethod().getMethodName() + " PASSED");
  }

  public void onTestFailure(ITestResult result) {
    logger.info(result.getMethod().getMethodName() + " FAILED.");
    ExtentReporterUtility.getTest().log(Status.FAIL,result.getMethod().getMethodName() + " FAILED");
    ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
    Object testclass = result.getInstance();
    logger.info("capturing screenshot for failed test case");
    BrowserUtility browserUtility = ((TestBase)testclass).getInstance();
    String screenShotPath =browserUtility.takeScreenShot(result.getMethod().getMethodName());
    ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
    logger.info("Attached screenshot to html file report");

  }

  public void onTestSkipped(ITestResult result) {
    logger.info(result.getMethod().getMethodName() + " SKIPPED.");
    ExtentReporterUtility.getTest().log(Status.SKIP,result.getMethod().getMethodName() + " SKIPPED");

  }

  public void onStart(ITestContext context) {
    logger.info("Test suite started");
    ExtentReporterUtility.setUpSparkReporter("report.html");
  }

  public void onFinish(ITestContext context) {
    logger.info("Test suite completed");
    ExtentReporterUtility.flushReport();
  }
}
