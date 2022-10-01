package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
//        Initial setup
        String desiredProduct = "Zara";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        driver.get("https://rahulshettyacademy.com/client/");
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
        Thread.sleep(10000);
        driver.quit();
    }

}
