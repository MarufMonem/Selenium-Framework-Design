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

public class ErrorValidation extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public  void loginErrorValidation() throws InterruptedException, IOException {

        ProductCatalogue pc = lp.loginApplication("maruftest@yahoo.com","Test1234567" );
        Assert.assertEquals(lp.getErrorMsg(),"Incorrect email or password.");

    }

    @Test
    public  void productErrorValidation() throws InterruptedException, IOException {
//        Initial setup
        String desiredProduct = "ZARA COAT 3";

        ProductCatalogue pc = lp.loginApplication("maruftest@yahoo.com","Test123456" );

        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(desiredProduct);

        Cart c = new Cart(driver);
        Assert.assertFalse(c.verifyCartItem("ZARA COAT 3"));
    }

}
