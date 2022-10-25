package Selenium.TestComponents;

import Selenium.Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extentR = ExtentReporterNG.getReportObject();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        extentR.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        test.log(Status.FAIL, "Test Failed");
        test.fail(result.getThrowable());

        try {
//            Video 172 - 10.55
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//            getTestClass gives the test class that called this method
//            GetRealClass gives the actual class such as submit order
//            get field gives us the required variable
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String screenShotPath = null;
        try {
            screenShotPath = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(screenShotPath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extentR.flush();
    }
}
