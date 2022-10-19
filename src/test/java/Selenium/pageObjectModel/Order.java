package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Order extends abstractComponent {
    WebDriver driver;

    //        constructor
    public Order(WebDriver d){
        super(d);
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
    WebElement orderButton;

    @FindBy(xpath="//table/tbody/tr")
    List<WebElement> orderItems;


    public void goToOrderPage(){
        orderButton.click();
    }

    public boolean verifyOrderItem(String desiredProduct){
        goToOrderPage();
        return orderItems.stream().anyMatch(item-> item.getText().contains(desiredProduct.toLowerCase()));
    }


}
