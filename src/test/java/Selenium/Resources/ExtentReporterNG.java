package Selenium.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+"\\Extent Reports\\index.html";
//        Extent Spark reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

//        Change report name
        reporter.config().setReportName("Web Automation Results");
//        Document title
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
//        Set tester name
        extent.setSystemInfo("Tester", "Maruf Monem");
        return extent;
    }

}
