package Selenium.Data.TestComponents;

import Selenium.pageObjectModel.landingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class BaseTest {
    public WebDriver driver;
    public landingPage lp;
    public String desiredProduct = "ZARA COAT 3";

    public WebDriver intializeDriver() throws IOException {
//        Properties class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Selenium\\Resources\\GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//        value assign = if this system value is not null ? then use whatever value you got : if its null then use the global properties

//        String browserName =  prop.getProperty("browser");

//        Chrome
        if(browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
//            below helps to run in ful screen
            driver.manage().window().setSize(new Dimension(1440,900));

        }else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        else{
            System.out.println("we dont have this");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        driver.manage().window().maximize();

        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//        read json to string
        String jsonContent =  FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8 );

//       String to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});

        return data;
    }


    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        driver = intializeDriver();
        lp = new landingPage(driver);
        lp.goToApp();
    }


    @AfterMethod(alwaysRun = true)
    public void closeBrowser() throws InterruptedException {
        System.out.println("Closing browser...");
        Thread.sleep(6000);
        driver.close();
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName +  ".png") ;
        FileUtils.copyFile(source,file );
        return System.getProperty("user.dir") + "//reports//" + testCaseName +  ".png";
    }
}
