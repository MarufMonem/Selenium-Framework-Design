package Selenium.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue {
    WebDriver driver;

    //        constructor
    public ProductCatalogue(WebDriver d){
        this.driver = d;
        PageFactory.initElements(driver, this);
    }

//    Page Factory
    @FindBy(className="mb-3")
    List<WebElement> products;


    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginClick.click();
    }

    public void goToApp(){
        driver.get("https://rahulshettyacademy.com/client/");
    }


}
