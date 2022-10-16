package Selenium.Tests;

import Selenium.Tests.TestComponents.BaseTest;
import Selenium.pageObjectModel.*;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StandAloneTest_Test extends BaseTest {

    @Test
    public  void submitOrder() throws InterruptedException, IOException {
//        Initial setup
        String desiredProduct = "ZARA COAT 3";

        ProductCatalogue pc = lp.loginApplication("maruftest@yahoo.com","Test123456" );

        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(desiredProduct);

        Cart c = new Cart(driver);
        Assert.assertTrue(c.verifyCartItem(desiredProduct));
        c.goToCheckout();
//
        Checkout co = new Checkout(driver);
        Confirmation confir = co.submitOrder();

        confir.message();
    }

}
