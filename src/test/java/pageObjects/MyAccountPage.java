package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {
	//1)Constructor, no driver as we already started in base page
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	//2) Locators, shouldnt be changed from outside
	private final By get_MyAccount = By.xpath("//h2[normalize-space()='My Account']");
	private final By btn_Logout = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");
	//3) Actions, make public so other packages can access it
	public boolean validateMyAccount() {
		try {
			return driver.findElement(get_MyAccount).isDisplayed();
		} catch (Exception e) {
			return false;
		}}
	public void clickLogout() {
		driver.findElement(btn_Logout).click();
	}
	

}
