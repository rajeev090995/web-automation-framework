package com.ui.listeners;

import com.constants.Env;
import com.utility.JsonUtility;
import com.utility.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {

//  private static final int MAX_NO_OF_ATTEMPTS = Integer.parseInt(
//      PropertiesUtil.readPropertiesFile(Env.DEV, "MAX_NO_OF_ATTEMPTS"));
  private static final int MAX_NO_OF_ATTEMPTS = JsonUtility.readJSON(Env.QA).getMAX_NO_OF_ATTEMPTS();
  private static int currentAttempt = 1;

  // if returns true, test will be retried, otherwise it will be marked as failed
  @Override
  public boolean retry(ITestResult iTestResult) {
    if(currentAttempt<=MAX_NO_OF_ATTEMPTS){
      currentAttempt++;
      return true;
    }
    return false;
  }
}
