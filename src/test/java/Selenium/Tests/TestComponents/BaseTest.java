package Selenium.Tests.TestComponents;

import Selenium.pageObjectModel.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {
    public WebDriver driver;

    public WebDriver intializeDriver() throws IOException {
//        Properties class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Selenium\\Resources\\GlobalData.properties");
        prop.load(fis);
       String browserName =  prop.getProperty("browser");

//        Chrome
        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }else{
            System.out.println("we dont have this");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        driver.manage().window().maximize();

        return driver;
    }

    public landingPage launchApplication() throws IOException {
        driver = intializeDriver();
        landingPage lp = new landingPage(driver);
        lp.goToApp();
        return lp;

    }
}
