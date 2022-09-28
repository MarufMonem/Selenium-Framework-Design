package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

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

    }

}
