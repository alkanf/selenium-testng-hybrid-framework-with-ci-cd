package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class TC001_AccountRegistration extends BaseClass {

@Test(groups={"Regression","Master"})
public void verify_account_registration() {
try {
logger.info("Starting TC001_AccountRegistration"); //Derived from BaseClass, use level in method like comment
HomePage hp = new HomePage(driver);
logger.info("Clicking on My Account");
hp.clickMyAccount();
logger.info("Clicking on Register");
hp.clickRegister();
logger.info("Entering customer details");
RegistrationPage rp = new RegistrationPage(driver);
rp.typeFirstName(randomString());
rp.typeLastName(randomString());
rp.typeEmail(randomString() + "@gmail.com");
rp.typePhone(randomNumber());
String password = randomString();
rp.typePassword(password);
rp.typePasswordConfirm(password);

rp.clickSubscribeYes();
rp.clickPrivacyPolicy();
rp.clickContinue();
logger.info("Validating expected message");
String actualResult = rp.getAccountCreatedMsg();
AssertJUnit.assertEquals(actualResult, "Your Account Has Been Created!");
} catch(Exception e)
{
	logger.error("Test Failed...");
	logger.debug("Debug logs..");
	AssertJUnit.fail();
}
logger.info("Finished TC001_AccountRegistration"); //Derived from BaseClass, use level in method like comment

}








}
