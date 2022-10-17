package Selenium.Tests;

import Selenium.TestComponents.BaseTest;
import Selenium.pageObjectModel.Cart;
import Selenium.pageObjectModel.Checkout;
import Selenium.pageObjectModel.Confirmation;
import Selenium.pageObjectModel.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() throws InterruptedException, IOException {
//        Initial setup
        String desiredProduct = "ZARA COAT 3";

        ProductCatalogue pc = lp.loginApplication("maruftest@yahoo.com", "Test123456");

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
