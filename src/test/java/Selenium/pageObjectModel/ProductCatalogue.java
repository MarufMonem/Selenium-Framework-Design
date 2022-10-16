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

    @FindBy(css=".ng-animating")
    WebElement spinner;

    By byProduct = By.className("mb-3");
//    By addToCart = By.cssSelector()
    By cartAnimation = By.cssSelector(".ng-animating");
    By toastMessage = By.xpath("//*[@id='toast-container']");

    public List<WebElement> getProductList(){
//        We can not pass in the page factory elements. It doesnt support By
//        So we need to create a By object and then pass it
        waitForElementToAppear(byProduct);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement desiredItem = products.stream()
                .filter(product-> product
                        .findElement(By.tagName("b"))
                        .getText().contains(productName)).findFirst().orElse(null);
        return desiredItem;
    }

    public void addProductToCart (String productName){
        WebElement item = getProductByName(productName);
        item.findElement(By.cssSelector("button:last-of-type")).click();
        waitForElementToAppear(cartAnimation);
        waitForElementToDisappear(spinner);
    }





}
