package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        /* * 1. CRITICAL: Set locale to English to prevent "exception" vs "exceptıon" 
         * error on Turkish operating systems during report generation.
         */
        Locale.setDefault(Locale.ENGLISH);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        /* * 2. Define the report path within the target folder for Maven and GitHub Actions compatibility.
         */
        String reportFolderPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "reports";
        File reportFolder = new File(reportFolderPath);
        
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        sparkReporter = new ExtentSparkReporter(reportFolderPath + File.separator + repName);

        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        // Finalize the report
        extent.flush();

        /* * 3. Auto-open report in default browser after execution (Local only).
         * Will skip this step on GitHub Actions (Linux) to prevent Headless display errors.
         */
        String pathOfExtentReport = System.getProperty("user.dir") + File.separator + "target" + File.separator + "reports" + File.separator + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (extentReport.exists() && !os.contains("linux")) {
                Desktop.getDesktop().browse(extentReport.toURI());
            }
        } catch (IOException e) {
            System.out.println("Auto-open failed: " + e.getMessage());
        }
    }
}