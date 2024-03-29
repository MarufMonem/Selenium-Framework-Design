package Selenium.abstractPackages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class abstractComponent {
    WebDriver driver;

    public abstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findby){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForElementToAppear(WebElement item){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(item));
    }

    public void waitForElementToDisappear(WebElement disappearingItem){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(disappearingItem));
    }


}
