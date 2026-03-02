package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
//1)Constructor, no driver as we already started in base page
	public LoginPage(WebDriver driver) {
		super(driver);
	}
//2) Locators, shouldnt be changed from outside
private final By txt_email = By.xpath("//input[@id='input-email']");
private final By txt_password = By.xpath("//input[@id='input-password']");
private final By btn_login = By.xpath("//input[@value='Login']");

//3) Actions, make public so other packages can access it
public void typeEmail(String email) {
	driver.findElement(txt_email).sendKeys(email);
}

public void typePassword(String password) {
	driver.findElement(txt_password).sendKeys(password);
}

public void clickLogin() {
	driver.findElement(btn_login).click();
}

	
	
	
	
	
	
	
	
	
}
