package Selenium.pageObjectModel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
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
        String desiredProduct = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        driver.manage().window().maximize();
//      Calling the constructor
        landingPage lp = new landingPage(driver);

        lp.goToApp();
        ProductCatalogue pc = lp.loginApplication("maruftest@yahoo.com","Test123456" );

        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(desiredProduct);

        Cart c = new Cart(driver);
        Assert.assertTrue(c.verifyCartItem(desiredProduct));
        c.goToCheckout();
//
        Checkout co = new Checkout(driver);
        co.checkout();


        Thread.sleep(10000);
        driver.quit();
    }

}
