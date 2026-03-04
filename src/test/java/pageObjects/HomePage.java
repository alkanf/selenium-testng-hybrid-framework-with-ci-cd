package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
//1)Constructor, no driver as we already started in base page
public HomePage(WebDriver driver) {
	super(driver);
}
//2) Locators, shouldnt be changed from outside
private final By btn_MyAccount = By.xpath("//a[@title='My Account']");
private final By btn_Register = By.xpath("//a[normalize-space()='Register']");
private final By btn_Login = By.xpath("//a[normalize-space()='Login']");
private final By txt_Search = By.xpath("//input[@placeholder='Search']");
private final By btn_Search = By.xpath("//button[@class='btn btn-default btn-lg']");

//3) Actions, make public so other packages can access it
public void clickMyAccount() {
	driver.findElement(btn_MyAccount).click();
}

public void clickRegister() {
	driver.findElement(btn_Register).click();
}

public void clickLogin() {
	driver.findElement(btn_Login).click();
}

public void typeSearch(String product) {
	driver.findElement(txt_Search).sendKeys(product);
}

public void clickSearch () {
	driver.findElement(btn_Search).click();
}




}
