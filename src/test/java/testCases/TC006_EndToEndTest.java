package testCases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import pageObjects.SearchPage;

public class TC006_EndToEndTest extends BaseClass {
//Is user capable of doing register/login and add product to cart
//Must included hard assert	
	@Test(groups="Master")
	void end_to_end() {
		try {
			
//Register
			logger.info("Starting TC006_EndToEndTest"); //Derived from BaseClass, use level in method like comment
			HomePage hp = new HomePage(driver);
			logger.info("Clicking on My Account");
			hp.clickMyAccount();
			logger.info("Clicking on Register");
			hp.clickRegister();
			logger.info("Entering customer details");
			RegistrationPage rp = new RegistrationPage(driver);
			rp.typeFirstName(randomString());
			rp.typeLastName(randomString());
			String email = randomString() + "@gmail.com";
			rp.typeEmail(email);
			rp.typePhone("+18456845684");
			String password = randomString();
			rp.typePassword(password);
			rp.typePasswordConfirm(password);
			rp.clickSubscribeYes();
			rp.clickPrivacyPolicy();
			rp.clickContinue();
			logger.info("Validating expected message");
			String actualResult = rp.getAccountCreatedMsg();
			AssertJUnit.assertEquals(actualResult, "Your Account Has Been Created!");
			rp.click_MyAccount();
			logger.info("Clicking on Logout");
			rp.click_Logout();
			//Login
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
			boolean expectedResult = mp.validateMyAccount();
			Assert.assertEquals(expectedResult, true);
			//Add to Cart
			logger.info("Typing on search box");
			mp.typeProduct("Samsung");
			mp.clickSearch();
			
			SearchPage sp = new SearchPage(driver);
			sp.click_AddToCart();
			
			logger.info("Validating product added");
			boolean actualResult2 = sp.validate_AddedToCart();
			Assert.assertEquals(actualResult2, true);
		
		} catch(Exception e)
			{
				logger.error("Test Failed...");
				logger.debug("Debug logs..");
				AssertJUnit.fail();
			}
		
			logger.info("Finished TC001_AccountRegistration"); //Derived from BaseClass, use level in method like comment

}
}