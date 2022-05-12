package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    //Open webpage with given URL
    protected void openUrl(String url) {
        driver.get(url);
    }

   //Find element using given locator
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    // Click on element with given locator when its visible
    protected void click(By locator) {
        //wait for visibility of the element for 5 seconds
        waitForVisibilityOf(locator, 20);
        find(locator).click();
    }
    //To get text of the web element
    protected String getTextOfElement(By locator){
        //wait for visibility
        waitForVisibilityOf(locator,10);
        String elementText = find(locator).getText();
        return elementText;
    }

     //Type given text into element with given locator
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 10);
        find(locator).sendKeys(text);
    }

    //To accept the alert
    protected void acceptAlert(WebDriver driver){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

     //Wait for specific ExpectedCondition for the given amount of time in seconds
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(condition);
    }


    //Wait for given number of seconds for element with given locator to be visible
     // on the page

    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        // 2 attempts
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

}

