package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
	//1)Constructor, no driver as we already started in base page
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	//2) Locators, shouldnt be changed from outside
	private final By txt_FirstName = By.xpath("//input[@id='input-firstname']");
	private final By txt_LastName = By.xpath("//input[@id='input-lastname']");
	private final By txt_Email = By.xpath("//input[@id='input-email']");
	private final By txt_Telephone = By.xpath("//input[@id='input-telephone']");
	private final By txt_Password = By.xpath("//input[@id='input-password']");
	private final By txt_PasswordConfirm = By.xpath("//input[@id='input-confirm']");
	private final By btn_SubscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private final By btn_SubscribeNo = By.xpath("//input[@value='0']");
	private final By btn_PrivacyPolicy = By.xpath("//input[@name='agree']");
	private final By btn_Continue = By.xpath("//input[@value='Continue']");
	private final By txt_AccountCreated = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
    private final By btn_MyAccount = By.xpath("//a[@title='My Account']");
    private final By btn_Logout = By.xpath("(//a[normalize-space()='Logout'])[1]");
    		
	
	//Adding getsuccess msg or not as it in another page?
	//3) Actions
	public void typeFirstName(String firstName) {
		driver.findElement(txt_FirstName).sendKeys(firstName);
	}
	public void typeLastName(String lastName) {
		driver.findElement(txt_LastName).sendKeys(lastName);
	}
	public void typeEmail(String email) {
		driver.findElement(txt_Email).sendKeys(email);
	}
	public void typePhone(String phone) {
		driver.findElement(txt_Telephone).sendKeys(phone);
	}
	public void typePassword(String password) {
		driver.findElement(txt_Password).sendKeys(password);
	}
	public void typePasswordConfirm(String passwordconfirm) {
		driver.findElement(txt_PasswordConfirm).sendKeys(passwordconfirm);
	}
	public void clickSubscribeYes() {
		driver.findElement(btn_SubscribeYes).click();
	}
	public void clickSubscribeNo() {
		driver.findElement(btn_SubscribeNo).click();
	}
	public void clickPrivacyPolicy() {
		driver.findElement(btn_PrivacyPolicy).click();
	}
	public void clickContinue() {
		driver.findElement(btn_Continue).click();
	}
	public String getAccountCreatedMsg() {
	    try {
	        return driver.findElement(txt_AccountCreated).getText();
	    } 
	    catch (Exception e) {
	        return e.getMessage();
	    }}
	public void click_MyAccount() {
	 driver.findElement(btn_MyAccount).click();
	}
	public void click_Logout() {
	driver.findElement(btn_Logout).click();
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
}
