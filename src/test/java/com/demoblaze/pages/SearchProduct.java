package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchProduct extends BasePageObject{

    private By categoryLocator = By.xpath("//div[@class='list-group']/a[text()='Laptops']");
    private By productLocator = By.xpath("//h4[@class='card-title']/a[text()='MacBook air']");

    public SearchProduct(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public ProductPage selectProduct(){

        //Search for laptop
        click(categoryLocator);
        //select the product Macbook air
        click(productLocator);
        return new ProductPage(driver,log);
    }
}
