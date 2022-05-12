package demotests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreTest {
    private static WebDriver driver;

    public static void main(String[] args) {
        String website = "https://www.demoblaze.com/index.html";
        String username = "junu";
        String password = "qwerty";

        // Set the browser and WebDriver
        driver = setBrowser("chrome");
        driver.manage().window().maximize();

        //Go to the URL
        driver.get(website);
        //click Log In
        driver.findElement(By.xpath("//a[@id='login2']")).click();
        //enter username and password
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginusername']"))).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys(password);
        //click log-in
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        //Confirm login successful
        String loginresult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='navbarExample']//a[@id='nameofuser']"))).getText();
        System.out.println(loginresult);

        //click for laptop
        driver.findElement(By.xpath("//div[@class='list-group']/a[text()='Laptops']")).click();
        //select the product Macbook air
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='card-title']/a[text()='MacBook air']")));
        product.click();
        //click add to cart
        WebElement AddtoCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-success btn-lg'][text()='Add to cart']")));
        AddtoCart.click();
        //Accept the alert
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //Go to cart
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navbarExample']//a[text()='Cart']")));
        cart.click();

        //Click Place order
        WebElement PlaceOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-1']//button[text()='Place Order']")));
        PlaceOrder.click();

        //Fill the form of place order
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-group']/input[@id='name']"))).sendKeys(username);
        driver.findElement(By.xpath("//div[@class='form-group']/input[@id='country']")).sendKeys("india");
        driver.findElement(By.xpath("//div[@class='form-group']/input[@id='city']")).sendKeys("Thrissur");
        driver.findElement(By.xpath("//div[@class='form-group']/input[@id='card']")).sendKeys("123489073456");
        driver.findElement(By.xpath("//div[@class='form-group']/input[@id='month']")).sendKeys("April");
        driver.findElement(By.xpath("//div[@class='form-group']/input[@id='year']")).sendKeys("2022");
        driver.findElement(By.xpath("//div[@class='modal-footer']/button[text()='Purchase']")).click();

        //Confirmation message of place order
        WebElement PurchaseConfirmMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sweet-alert  showSweetAlert visible']/p")));
        String ms = PurchaseConfirmMsg.getText();
        driver.findElement(By.xpath("//button[@class='confirm btn btn-lg btn-primary'][text()='OK']")).click();

        System.out.println(ms);

    }

    public static WebDriver setBrowser(String browser) {
        String lowerCaseBrowser = browser.toLowerCase();
        WebDriver driver;

        switch (lowerCaseBrowser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\selenium-java-4.1.3\\geckodriver-v0.30.0-win64\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\selenium-java-4.1.3\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-4.1.3\\chromedriver.exe");
                driver = new ChromeDriver();
        }

        return driver;

    }
}
