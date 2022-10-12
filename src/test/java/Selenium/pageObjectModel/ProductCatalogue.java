package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends abstractComponent {
    WebDriver driver;

    //        constructor
    public ProductCatalogue(WebDriver d){
        super(d);
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

//    Page Factory
    @FindBy(className="mb-3")
    List<WebElement> products;

    By byProduct = By.className("mb-3");

    public List<WebElement> getProductList(){
//        We can not pass in the page factory elements. It doesnt support By
//        So we need to create a By object and then pass it
        waitForElementToAppear(byProduct);
        return products;
    }


//    public void loginApplication(String email, String password){
//        userEmail.sendKeys(email);
//        userPassword.sendKeys(password);
//        loginClick.click();
//    }

    public void goToApp(){
        driver.get("https://rahulshettyacademy.com/client/");
    }


}
