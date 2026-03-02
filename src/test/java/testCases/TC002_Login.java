package testCases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_Login extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_account_registration() {
		try {
			logger.info("Starting TC001_AccountRegistration"); // Derived from BaseClass, use level in method like
	
			
			HomePage hp = new HomePage(driver);
			logger.info("Clicking on MyAccount");
			hp.clickMyAccount();
			logger.info("Clicking on Login");
			hp.clickLogin();
			
			logger.info("Entering customer login details");
			LoginPage lp = new LoginPage(driver);
			lp.typeEmail(p.getProperty("email"));
			lp.typePassword(p.getProperty("password"));
			lp.clickLogin();
			
			logger.info("Validating expected message");
			MyAccountPage mp = new MyAccountPage(driver);
			boolean expectedResult = mp.validateMyAccount();
			Assert.assertEquals(expectedResult, true);
			
		} catch (Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug logs..");
			AssertJUnit.fail();
		}
		
		logger.info("Finished TC002_Login"); // Derived from BaseClass, use level in method like comment

	}

}
