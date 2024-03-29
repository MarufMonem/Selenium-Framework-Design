package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cart extends abstractComponent {
    WebDriver driver;

    //        constructor
    public Cart(WebDriver d){
        super(d);
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cartButton;

    @FindBy(css=".cart .items h3")
    List<WebElement> cartItems;

    @FindBy(xpath="//button[text()='Checkout']")
    WebElement checkoutButton;


 public boolean verifyCartItem(String desiredProduct){
//     waitForElementToAppear(checkoutButton);
     cartButton.click();
     return cartItems.stream().anyMatch(item-> item.getText().contains(desiredProduct));
 }
 public void goToCheckout() throws InterruptedException {
     waitForElementToAppear(By.xpath("//button[text()='Checkout']"));
     checkoutButton.click();
 }


}
