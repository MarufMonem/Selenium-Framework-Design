package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
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
//        All products
        List<WebElement> allProdcuts = driver.findElements(By.className("mb-3"));

//        for (WebElement product: allProdcuts) {
//            String name = product.findElement(By.tagName("b")).getText();
//            System.out.println(name);
//        }

//        Here we are filtering out the product that has the name ZARA in it. If there are multiple
//        elements having the word ZARA then return only one.
//        If nothing is found return null
        WebElement desiredItem = allProdcuts.stream()
                .filter(product-> product
                        .findElement(By.tagName("b"))
                        .getText().contains("ZARA")).findFirst().orElse(null);
        desiredItem.findElement(By.cssSelector("button:last-of-type")).click();

    }

}
