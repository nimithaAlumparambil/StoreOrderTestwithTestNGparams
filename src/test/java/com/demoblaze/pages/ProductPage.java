package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePageObject {
    private By addcartLocator = By.xpath("//a[@class='btn btn-success btn-lg'][text()='Add to cart']");

    public ProductPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public PlaceOrderPage AddToCart(){

        //click add to cart
        click(addcartLocator);
        //Accept the alert
        acceptAlert(driver);
        return new PlaceOrderPage(driver, log);
    }
}
