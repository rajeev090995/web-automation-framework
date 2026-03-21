package com.utility;

import com.constants.Browser;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class BrowserUtility {
    private static ThreadLocal<WebDriver> driver =  new ThreadLocal<WebDriver>(); //instance variable - heap stores null values as non primitive data type

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);
    }

    public BrowserUtility(Browser browserName){
        if(browserName== Browser.CHROME){
            driver.set(new ChromeDriver());
        } else if (browserName== Browser.EDGE) {
            driver.set(new EdgeDriver());
        } else {
            System.err.println("Invalid Browser");
        }
    }

    public BrowserUtility(Browser browserName,boolean isHeadless){
        if(browserName== Browser.CHROME){
            if(isHeadless){
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=old");
                chromeOptions.addArguments("--window-size=1920,1080");

                driver.set(new ChromeDriver(chromeOptions));
            }else {
                driver.set(new ChromeDriver());
            }


        } else if (browserName== Browser.EDGE) {
            if(isHeadless){
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old");
                options.addArguments("disable-gpu");

                driver.set(new EdgeDriver(options));
            }else {
                driver.set(new EdgeDriver());
            }

        } else if(browserName ==Browser.FIREFOX){
            if(isHeadless){
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                driver.set(new FirefoxDriver(options));
            }else {
                driver.set(new FirefoxDriver());
            }
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void goToWebsite(String url){
        driver.get().get(url);
    }

    public void maximizeWindow(){
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        WebElement webElement = driver.get().findElement(locator);
        webElement.click();
    }

    public void enterText(By locator,String textToEnter){
        WebElement webElement = driver.get().findElement(locator);
        webElement.sendKeys(textToEnter);

    }

    public String getVisibleText(By locator){
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public String takeScreenShot(String name){
        //code to take screenshot and save it to the specified file path

        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
        String timestamp = formatter.format(date);

        String path = "./screenshots/"+File.separator+name+" - "+timestamp;
        File screenshotFile = new File(path);

      try {
        FileUtils.copyFile(screenshotData, screenshotFile);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return path;

    }


}
