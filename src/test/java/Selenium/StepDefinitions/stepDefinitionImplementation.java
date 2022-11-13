package Selenium.StepDefinitions;

import Selenium.Data.TestComponents.BaseTest;
import Selenium.pageObjectModel.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class stepDefinitionImplementation extends BaseTest {
    public landingPage lp;
    ProductCatalogue pc;
    Confirmation confir;
    @Given("I landed on the E-commerce page")
    public void I_landed_on_the_Ecommerce_page() throws IOException{
       launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password){
        pc = lp.loginApplication(username, password);
    }

    @When("^I add product (.+)$")
    public void addingProduct(String productName){
        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException {
        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(productName);

        Cart c = new Cart(driver);
        System.out.println(productName);
        Assert.assertTrue(c.verifyCartItem(productName));
        c.goToCheckout();
        Checkout co = new Checkout(driver);
        confir = co.submitOrder();
    }

    @Then("{string} message is displayed on the confirmation page")
    public void message_displayed_confirmation_page(String msg){
        confir.messageWithParameters(msg);
    }

}



