package com.demoblaze.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    //ScreenShot variables
    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, String browser, ITestContext ctx) {
        //read the test name from testNG xml file
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        //create object for BrowserDriverFactory
        // Create driver
        log.info("Create driver: " + browser);
        BrowserDriverFactory factory = new BrowserDriverFactory(browser,log);
        //set the webDriver
        driver = factory.createDriver();
        driver.manage().window().maximize();

        //Initializing screenshot variables
        /*this.testSuiteName = ctx.getSuite().getName();
        this.testName = testName;
        this.testMethodName = method.getName(); *///Use method to get the name of test method from test suite

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        //close the browser
        log.info("Close browser");
        driver.quit();
    }

}
