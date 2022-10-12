package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends abstractComponent {
    WebDriver driver;

    //        constructor
    public landingPage(WebDriver d){
//        sending driver object to the parent abstract class
        super(d);
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

//    Page Factory
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginClick;

    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginClick.click();
    }

    public void goToApp(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
