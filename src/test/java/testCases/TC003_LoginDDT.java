package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	                           //Must add this if its in another class and import
@Test(groups="Datadriven",dataProvider="LoginData", dataProviderClass=DataProviders.class)
public void verify_loginDDT(String email, String password, String expectedResult) {
	logger.info("Starting TC003_AccountRegistration"); // Derived from BaseClass, use level in method like
	try {
	
	HomePage hp = new HomePage(driver);
	logger.info("Clicking on MyAccount");
	hp.clickMyAccount();
	logger.info("Clicking on Login");
	hp.clickLogin();
	
	logger.info("Entering customer login details");
	LoginPage lp = new LoginPage(driver);
	lp.typeEmail(email);
	lp.typePassword(password);
	lp.clickLogin();
	
	logger.info("Validating expected message");
	MyAccountPage mp = new MyAccountPage(driver);
	boolean expectedDisplay = mp.validateMyAccount();

/*Data is valid -- login success - test pass -logout
                   login failed - test fail
Data is invalid -- login success -- test fail - logout
                     login failed -- test pass
*/ //IgnoreCase as letter wont be sentivie to upper case
	if(expectedResult.equalsIgnoreCase("Valid")) {
		if(expectedDisplay==true) {
			Assert.assertTrue(true);
			mp.clickLogout();
		}
		else {
			Assert.assertTrue(false);
		}
	}
	if(expectedResult.equalsIgnoreCase("Invalid")) { 
		if(expectedDisplay==true) {
			mp.clickLogout();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
			
		}
	}
	
	
	
	
	}catch(Exception e ) {
		Assert.fail();
	}
	
	logger.info("Finished TC003_AccountRegistration");
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
