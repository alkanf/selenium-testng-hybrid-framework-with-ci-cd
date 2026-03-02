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




}
