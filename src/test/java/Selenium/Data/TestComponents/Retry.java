package Selenium.Data.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count =0;
    int maxTry = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        System.out.println("RETRYING.......");
        if(count<maxTry){
            count++;
            return true; //signifies YES RETRY
        }
        return false;
    }
}
