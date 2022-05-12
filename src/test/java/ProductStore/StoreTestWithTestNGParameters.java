package ProductStore;

import com.demoblaze.base.BaseTest;
import com.demoblaze.base.TestUtilities;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.PlaceOrderPage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.pages.SearchProduct;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class StoreTestWithTestNGParameters extends TestUtilities {

    @Parameters({"username","password"})
    @Test
    public void purchaseTest(String username, String password) {

        log.info("Starting the purchase test");
        log.info("Open the website");
        LoginPage loginPage = new LoginPage(driver,log);
        loginPage.openPage();

        log.info("Log in to the website");
        SearchProduct searchProduct= loginPage.login(username,password);

        //Login Confirmation
        String loginConfirmationMsg = loginPage.loginConfirmation();
        Assert.assertTrue(loginConfirmationMsg.contains(username));
        log.info("login successful");
        //takeScreenshot("Login Successful");


        log.info("Search  for product");
        ProductPage productPage = searchProduct.selectProduct();

        log.info("Add to cart");
        PlaceOrderPage placeOrderPage = productPage.AddToCart();
        //takeScreenshot("Product Page Opened");

        log.info("Place order");
        placeOrderPage.placeanOrder();
        //takeScreenshot("Product order placed");

        log.info("Place order Confirmation");
        String orderConfirmation = placeOrderPage.placeOrderConfirmation();
        Assert.assertTrue(orderConfirmation.contains("Junaise"));
        log.info(orderConfirmation);


    }




}
