package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        
        // Target folder is safer for Maven based environments
        String reportPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "reports";
        File reportDir = new File(reportPath);
        if(!reportDir.exists()) reportDir.mkdirs();

        sparkReporter = new ExtentSparkReporter(reportPath + File.separator + repName);
        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Browser", testContext.getCurrentXmlTest().getParameter("browser"));
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.log(Status.PASS, result.getName() + " executed successfully");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.log(Status.SKIP, result.getName() + " skipped");
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
        // Mandatory delay for GitHub Actions to catch up with file system writing
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Extent Report flushed to: target/reports/" + repName);
    }
}