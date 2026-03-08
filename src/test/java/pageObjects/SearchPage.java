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
final private By btn_AddToCart = By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//button[1]");
final private By get_AddedCart = By.xpath("//div[@class='alert alert-success alert-dismissible']");

//3) Method 
public boolean validate_Macbook() {
	try {
		return driver.findElement(get_Macbook).isDisplayed();
	} catch (Exception e) {
		return false;
	}}
public void click_AddToCart() {
	driver.findElement(btn_AddToCart).click();
}

public boolean validate_AddedToCart() {
try {
	return driver.findElement(get_AddedCart).isDisplayed();
}
catch(Exception E) {
	return false;
}}




}
