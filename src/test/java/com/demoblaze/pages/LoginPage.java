package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePageObject{
    private String website = "https://www.demoblaze.com/index.html";

    private By logIn = By.xpath("//a[@id='login2']");
    private By usernameLocator = By.xpath("//input[@id='loginusername']");
    private By passwordLocator = By.xpath("//input[@id='loginpassword']");
    private By logInButton = By.xpath("//button[text()='Log in']");
    private By loginConfirmationLocator= By.xpath("//div[@id='navbarExample']//a[@id='nameofuser']");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //Open the webpage
    public void openPage(){
        //Go to the URL
        log.info("Opening : " + website) ;
        openUrl(website);
        log.info("Page Opened!");
    }

    //Login with valid credentials
    public SearchProduct login(String username, String password){

        //click Log In
        log.info("Clicking on Log In");
        click(logIn);

        //enter username and password
        type(username,usernameLocator);
        type(password, passwordLocator);

        //click log-in
        click(logInButton);

        //return the object of SearchProduct page class
         return new SearchProduct(driver,log);

    }

    public String loginConfirmation(){
        //Confirm login successful
        String loginConfMsg = getTextOfElement(loginConfirmationLocator);
        return loginConfMsg;
    }
}
