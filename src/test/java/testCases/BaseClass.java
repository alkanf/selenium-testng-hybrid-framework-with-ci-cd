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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger = LogManager.getLogger(this.getClass());
	public Properties p;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "operator", "browser" })
	public void setup(@Optional("windows") String operator, @Optional("chrome") String browser) throws IOException {

		// Loading config.properties file
		p = new Properties();
		p.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

		String executionEnv = p.getProperty("execution_env");

		// --- REMOTE EXECUTION (Selenium Grid) ---
		if (executionEnv.equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Operating system setup for Grid
			if (operator.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (operator.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No Matching Operating System");
				return;
			}

			String gridURL = p.getProperty("gridURL");

			// Browser setup for RemoteWebDriver
			switch (browser.toLowerCase()) {
				case "chrome":
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless=new");
					options.merge(capabilities);
					driver = new RemoteWebDriver(URI.create(gridURL).toURL(), options);
					break;
				case "firefox":
					FirefoxOptions foptions = new FirefoxOptions();
					foptions.addArguments("-headless");
					foptions.merge(capabilities);
					driver = new RemoteWebDriver(URI.create(gridURL).toURL(), foptions);
					break;
				default:
					System.out.println("Invalid browser name for remote..");
					return;
			}
		} 
		
		// --- LOCAL EXECUTION (Standard or GitHub Actions) ---
		else if (executionEnv.equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
				case "chrome":
					ChromeOptions options = new ChromeOptions();
					// Auto-detect GitHub Actions environment and enable Headless mode
					if (System.getenv("GITHUB_ACTIONS") != null) {
						options.addArguments("--headless=new");
						options.addArguments("--no-sandbox");
						options.addArguments("--disable-dev-shm-usage");
						options.addArguments("--window-size=1920,1080");
					}
					driver = new ChromeDriver(options);
					break;

				case "edge":
					driver = new EdgeDriver();
					break;

				case "firefox":
					FirefoxOptions foptions = new FirefoxOptions();
					if (System.getenv("GITHUB_ACTIONS") != null) {
						foptions.addArguments("-headless");
					}
					driver = new FirefoxDriver(foptions);
					break;

				default:
					System.out.println("Invalid browser name for local..");
					return;
			}
		}

		// Common Driver Settings
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	}

	@AfterClass(alwaysRun = true)
	public void exit() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String randomString() {
		return UUID.randomUUID().toString().substring(0, 5);
	}

	public String randomNumber() {
		return String.valueOf(System.currentTimeMillis()).substring(7);
	}

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

		return targetFilePath;
	}
}