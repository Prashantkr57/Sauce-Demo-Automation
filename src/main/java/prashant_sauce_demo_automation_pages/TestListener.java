package prashant_sauce_demo_automation_pages;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

@SuppressWarnings("deprecation")
public class TestListener implements ITestListener {

    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extent-report.html");
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().pass(MarkupHelper.createLabel("Test Case Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());
        WebDriver driver = ((WebDriver) result.getTestContext().getAttribute("driver"));
        takeScreenshot(driver, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().skip(MarkupHelper.createLabel("Test Case Skipped", ExtentColor.YELLOW));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    private void takeScreenshot(WebDriver driver, String methodName) {
        if (driver != null) {
            TakesScreenshot screenshot = ((TakesScreenshot) driver);
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            extentTestThreadLocal.get().addScreenCaptureFromBase64String(new String(screenshotBytes), methodName);
        }
    }
}

