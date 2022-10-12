package Selenium.pageObjectModel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest_Test {
    public static void main(String[] args) throws InterruptedException {
//        Initial setup
        String desiredProduct = "Zara";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        driver.get("https://rahulshettyacademy.com/client/");

//      Calling the constructor
        landingPage lp = new landingPage(driver);

        lp.goToApp();

        lp.loginApplication("maruftest@yahoo.com","Test123456" );





        ---------------------------------------------
//      User name
        driver.findElement(By.id("userEmail")).sendKeys("maruftest@yahoo.com");
//        Password
        driver.findElement(By.id("userPassword")).sendKeys("Test123456");
//        Login
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//        Waiting until all the products are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));

//        All products
        List<WebElement> allProdcuts = driver.findElements(By.className("mb-3"));

//        Here we are filtering out the product that has the name ZARA in it. If there are multiple
//        elements having the word ZARA then return only one.
//        If nothing is found return null
        WebElement desiredItem = allProdcuts.stream()
                .filter(product-> product
                        .findElement(By.tagName("b"))
                        .getText().contains("ZARA")).findFirst().orElse(null);
        desiredItem.findElement(By.cssSelector("button:last-of-type")).click();

//        We need to wait for the animation to be over and toast message to appear
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));

        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart .items h3"));

        Boolean match= cartItems.stream().anyMatch(item-> item.getText().contains("ZARA"));
        Assert.assertTrue(match);
//      Checkout
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
//      Shipping details
//        Country
//        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Bangladesh");

        Actions a = new Actions((driver));
        a.sendKeys( driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ban").build().perform();
//        waiting till the suggested results appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group-item")));
        driver.findElement(By.cssSelector(".ta-item:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        String successMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(successMsg.contains("THANKYOU"));
        System.out.println("Done!");


        Thread.sleep(10000);
        driver.quit();
    }

}
