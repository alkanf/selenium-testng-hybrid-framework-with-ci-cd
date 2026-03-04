package testCases;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger = LogManager.getLogger(this.getClass()); // Log4j
	public Properties p;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "operator", "browser" })
	public void setup(String operator, String browser) throws IOException {

		// Loading config.properfies file
		p = new Properties();
		p.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

		// Loading remote from config.properties for Grid
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// operating system
			if (operator.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (operator.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No Matching Operator System");
				return;
			}

			String gridURL = p.getProperty("gridURL"); // Correct grid URL

			// browser, can write if or switch in this case
			// Chrome options for docker
			switch (browser.toLowerCase()) {

			case "chrome":

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--remote-allow-origins=*");

				options.merge(capabilities);

				driver = new RemoteWebDriver(
						URI.create(gridURL).toURL(),
						options);

				break;

			case "firefox":

				FirefoxOptions foptions = new FirefoxOptions();
				foptions.addArguments("-headless");

				foptions.merge(capabilities);

				driver = new RemoteWebDriver(
						URI.create(gridURL).toURL(),
						foptions);

				break;

			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}

		// Local Execution
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}

		// driver = new ChromeDriver(); without xml
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL")); // Getting url from config.properites
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(alwaysRun = true)
	public void exit() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Random Test Data Methods
	public String randomString() { // Random identifer and take it from 0 - 5 length
		return UUID.randomUUID().toString().substring(0, 5);
	}

	public String randomNumber() {
		return String.valueOf(System.currentTimeMillis()).substring(7);
	}

	// Capture SS Method for Extend Utility Class, Tname is test name
	// TakesScreenshot is interface and timestap must be created
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		if (driver == null) {
			return null;
		}

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\"
				+ tname + "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath); 
		sourceFile.renameTo(targetFile);

		return targetFilePath; // Not only created but you need to pass to report also
	}
}