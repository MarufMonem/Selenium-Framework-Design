package Selenium.Tests;

import Selenium.TestComponents.BaseTest;
import Selenium.pageObjectModel.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(String email, String password, String desiredProduct) throws InterruptedException, IOException {
//        Initial setup
        ProductCatalogue pc = lp.loginApplication(email, password);

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

    @Test(dependsOnMethods = {"submitOrder"})
//    This is because we are dependent on the submitOrder to run first
    public void OrderHistoryTest(){
        ProductCatalogue pc = lp.loginApplication("maruftest@yahoo.com", "Test123456");
        Order ord = new Order(driver);
        boolean result =  ord.verifyOrderItem(desiredProduct);
        Assert.assertTrue(result);

    }
    @DataProvider
    public Object [] [] getData(){
        return new Object[] [] {{"maruftest@yahoo.com", "Test123456", "ZARA COAT 3"}, {"shetty@gmail.com", "Iaming@000", "ADIDAS ORIGINAL"}};
    }

}
