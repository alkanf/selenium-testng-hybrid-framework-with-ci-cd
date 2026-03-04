package testCases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;

public class TC004_SearchProduct extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_search_Macbook() {
		try {
			logger.info("Starting TC004_Search Product"); // Derived from BaseClass, use level in method like
	
			
			HomePage hp = new HomePage(driver);
			logger.info("Typing on search box");
			hp.typeSearch("Macbook");
			hp.clickSearch();
			
			logger.info("Validating expected product");
			SearchPage sp = new SearchPage(driver);
			boolean actualResult = sp.validate_Macbook();
			Assert.assertEquals(actualResult, true);
			
		} catch (Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug logs..");
			AssertJUnit.fail();
		}
		
		logger.info("Finished TC004_Search Product"); // Derived from BaseClass, use level in method like comment

	}
	
	
	
	
	
}
