package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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

    @FindBy(css="//button[text()='Checkout']")
    WebElement checkoutButton;



 public boolean verifyCartItem(String desiredProduct){
        cartButton.click();
     return cartItems.stream().anyMatch(item-> item.getText().contains(desiredProduct));
 }

 public void checkout(){
    checkoutButton.click();
//     Actions a = new Actions((driver));
//     a.sendKeys( driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ban").build().perform();
//     waitForElementToAppear();
 }


}
