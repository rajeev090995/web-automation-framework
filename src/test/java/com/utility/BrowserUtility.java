package com.utility;

import com.constants.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public abstract class BrowserUtility {
    private WebDriver driver; //instance variable - heap stores null values as non primitive data type

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public BrowserUtility(Browser browserName){
        if(browserName== Browser.CHROME){
            driver = new ChromeDriver();
        } else if (browserName== Browser.EDGE) {
            driver = new EdgeDriver();
        } else {
            System.err.println("Invalid Browser");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void goToWebsite(String url){
        driver.get(url);
    }

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public void clickOn(By locator){
        WebElement webElement = driver.findElement(locator);
        webElement.click();
    }

    public void enterText(By locator,String textToEnter){
        WebElement webElement = driver.findElement(locator);
        webElement.sendKeys(textToEnter);

    }

    public String getVisibleText(By locator){
        WebElement element = driver.findElement(locator);
        return element.getText();
    }
}
