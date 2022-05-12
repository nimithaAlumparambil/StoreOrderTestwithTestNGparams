package demotests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HotelBookingTest {
    private static WebDriver driver;


    public static void main(String[] args) {
        String website = "https://www.booking.com/";
        driver = setBrowser("firefox");
        //navigate to website
        driver.get(website);
        driver.manage().window().maximize();
        //Enter the city name
        driver.findElement(By.xpath("//label/input[@id='ss']")).sendKeys("Broadbeach, Gold Coast, Queensland, Australia");
        //Select date
        driver.findElement(By.xpath("//div[@data-calendar2-type='checkin']")).click();
        driver.findElement(By.xpath("//td/span[@aria-label='29 April 2022']")).click();
        driver.findElement(By.xpath("//td/span[@aria-label='3 May 2022']")).click();
        //Click submit button
        driver.findElement(By.xpath("//button[@type='submit'][@class='sb-searchbox__button ']")).click();
        //Modify search
        driver.findElement(By.xpath("(//div[text()='4 stars'])[1]")).click();
        //Click the 2nd hotel
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.xpath("(//div[@data-testid='property-card']//div[@data-testid='title'])[2]"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", element);
        String title = element.getText();
        switchToWindowWithTitle(title);
        System.out.println(driver.getTitle());


        //close the browser
        //driver.quit();
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

    public static void switchToWindowWithTitle(String expectedTitle) {
        // Switching to new window
        String firstWindow = driver.getWindowHandle();
        //Create a set containing all windows
        //The window handle is a unique identifier that stores the values of windows opened on a webpage and helps in window handling in Selenium.
        Set<String> allWindows = driver.getWindowHandles();
        //Iterating through all the windows
        Iterator<String> windowsIterator = allWindows.iterator();

        //Condition to check next window exist or not
        while (windowsIterator.hasNext()) {
            String windowHandle = windowsIterator.next();
            if (!windowHandle.equals(firstWindow)) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().contains(expectedTitle)) {
                    break;
                }
            }
        }
    }

}
