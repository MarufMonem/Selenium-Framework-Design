package Selenium.Tests;

import Selenium.TestComponents.BaseTest;
import Selenium.pageObjectModel.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> map) throws InterruptedException, IOException {
//        Initial setup
        ProductCatalogue pc = lp.loginApplication(map.get("email"), map.get("password"));

        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(map.get("desiredProduct"));

        Cart c = new Cart(driver);
        System.out.println(map.get("desiredProduct"));
        Assert.assertTrue(c.verifyCartItem(map.get("desiredProduct")));
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

//    JSON
    @DataProvider
    public Object [] [] getData() throws IOException {
        List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\Selenium\\Data\\PurchaseOrder.json"); //return a list of hashmaps
        return new Object[] [] {{data.get(0)}, {data.get(1)}};
    }




//    hashmap
//    @DataProvider
//    public Object [] [] getData(){
//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","maruftest@yahoo.com" );
//        map.put("password","Test123456" );
//        map.put("desiredProduct","ZARA COAT 3" );
//
//        HashMap<String,String> map2 = new HashMap<>();
//        map2.put("email","mhetty@gmail.com" );
//        map2.put("password","Iamking@000" );
//        map2.put("desiredProduct","ADIDAS ORIGINAL" );
//
//        return new Object[] [] {{map}, {map2}};
//    }

//    Arrays
//@DataProvider
//public Object [] [] getData(){
//    return new Object[] [] {{"maruftest@yahoo.com", "Test123456", "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//}




}
