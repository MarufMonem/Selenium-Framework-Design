package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Confirmation extends abstractComponent {
    WebDriver driver;

    @FindBy(css=".hero-primary")
    WebElement successMsg;


    //        constructor
    public Confirmation(WebDriver d){
        super(d);
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

 public void message(){
     Assert.assertTrue(successMsg.getText().contains("THANKYOU"));
     System.out.println("Done!");

 }


}
