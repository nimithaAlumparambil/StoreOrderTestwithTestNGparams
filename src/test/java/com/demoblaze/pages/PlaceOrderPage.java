package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlaceOrderPage extends BasePageObject{

    private By cartLocator = By.xpath("//div[@id='navbarExample']//a[text()='Cart']");
    private By placeOrderLOcator = By.xpath("//div[@class='col-lg-1']//button[text()='Place Order']");
    private By usernameTextBox = By.xpath("//div[@class='form-group']/input[@id='name']");
    private By country  = By.xpath("//div[@class='form-group']/input[@id='country']");
    private By city= By.xpath("//div[@class='form-group']/input[@id='city']");
    private By card = By.xpath("//div[@class='form-group']/input[@id='card']");
    private By month = By.xpath("//div[@class='form-group']/input[@id='month']");
    private By year = By.xpath("//div[@class='form-group']/input[@id='year']");
    private By purchaseButton = By.xpath("//div[@class='modal-footer']/button[text()='Purchase']");
    private By PurchaseConfirmMsg = By.xpath("//div[@class='sweet-alert  showSweetAlert visible']/p");
    private By okButton = By.xpath("//button[@class='confirm btn btn-lg btn-primary'][text()='OK']");

    public PlaceOrderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void placeanOrder(){

        String name = "Junaise";
        String userCountry = "India";
        String userCity = "kochi";
        String userCard = "512034092200";
        String userMonth = "May";
        String userYear = "2022";
        //Go to cart
        click(cartLocator);
        //Click Place order
        click(placeOrderLOcator);
        log.info("order placed");

        //Fill the form of place order
        type(name, usernameTextBox);
        type(userCountry, country);
        type(userCity, city);
        type(userCard, card);
        type(userMonth, month);
        type(userYear, year);
        click(purchaseButton);

    }

    public String placeOrderConfirmation(){
        //Confirmation message of place order

        String msg = getTextOfElement(PurchaseConfirmMsg);
        click(okButton);
        return msg;
    }
}
