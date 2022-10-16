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

public class Checkout extends abstractComponent {
    WebDriver driver;
    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(css=".btnn.action__submit")
    WebElement submitButton;

    @FindBy(css=".hero-primary")
    WebElement successMsg;
    By suggestedResult = By.cssSelector(".ta-item:nth-child(3)");

    //        constructor
    public Checkout(WebDriver d){
        super(d);
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

    public Confirmation submitOrder(){

        Actions a = new Actions((driver));
        a.sendKeys( country, "Ban").build().perform();
        waitForElementToAppear(suggestedResult);
        driver.findElement(suggestedResult).click();
        submitButton.click();
        return new Confirmation(driver);

    }


}
