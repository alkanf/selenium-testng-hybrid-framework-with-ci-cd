package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
//1) Constructor
public SearchPage(WebDriver driver) {
	super(driver);
}
//2) Locator
final private By get_Macbook = By.xpath("//div[@id='content']//div[1]//div[1]//div[2]//div[1]//h4[1]");

//3) Method 
public boolean validate_Macbook() {
	try {
		return driver.findElement(get_Macbook).isDisplayed();
	} catch (Exception e) {
		return false;
	}
}}
