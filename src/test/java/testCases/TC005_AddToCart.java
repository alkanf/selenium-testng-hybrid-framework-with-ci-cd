package testCases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
public class TC005_AddToCart extends BaseClass {
	
	
	
@Test(groups={"Regression","Master"})
public void add_to_cart() {
	try {
		logger.info("Starting TC005_AddToCart"); // Derived from BaseClass, use level in method like

		
		HomePage hp = new HomePage(driver);
		logger.info("Typing on search box");
		hp.typeSearch("Iphone");
		hp.clickSearch();
		
		SearchPage sp = new SearchPage(driver);
		sp.click_AddToCart();
		
		logger.info("Validating expected product");
		boolean actualResult = sp.validate_AddedToCart();
		Assert.assertEquals(actualResult, true);
		

		
		
	} catch (Exception e) {
		logger.error("Test Failed...");
		logger.debug("Debug logs..");
		AssertJUnit.fail();
	}
	
	logger.info("Finished TC004_Search Product"); // Derived from BaseClass, use level in method like comment

}
}
