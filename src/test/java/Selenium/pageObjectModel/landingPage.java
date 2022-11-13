package Selenium.pageObjectModel;

import Selenium.abstractPackages.abstractComponent;
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

    @FindBy(css="[class*='ng-trigger-flyInOut']")
    WebElement errorMsg;

    @FindBy(id="login")
    WebElement loginClick;

    public String getErrorMsg(){
        waitForElementToAppear(errorMsg);
        return errorMsg.getText();
    }

    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginClick.click();
        return new ProductCatalogue(driver);
    }


    public void goToApp(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
