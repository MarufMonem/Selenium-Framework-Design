package Selenium.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Selenium/Cucumber/SubmitOrder.feature", glue = "Selenium.StepDefinitions", monochrome = true, plugin = {"html:target/cucumber/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
