package com.Upgrade.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Upgrade.common.BaseTest;
import com.Upgrade.pages.CheckYourRate;
import com.Upgrade.pages.LandingPage;
import com.Upgrade.pages.PayOffOffer;
import com.Upgrade.pages.PayOffOfferPostLogin;

public class PayOffOfferTest extends BaseTest {
	
	
	PayOffOffer payOffOffer;
	

	@BeforeClass
	public void initialize() {
		//landingPage = new LandingPage(BaseTest.getDriver());
		payOffOffer = new PayOffOffer(BaseTest.getDriver());

	}
	
	@Test(description="Validate whether the landing page is  OfferPageUrl",groups="PayOffOfferTest",dependsOnGroups= {"CheckYourRateTest","CreateAccountTest"})
	public void TC_06_offerPageUrl_Validation() throws Exception {
		payOffOffer.offerPageValidation();
		
	}
	
	@Test(description="Validate whether the logout is successful",groups="PayOffOfferTest",dependsOnMethods="TC_06_offerPageUrl_Validation")
	public void TC_07_signOut_Validation() throws InterruptedException {
		
		payOffOffer.logOutUrl_Validation();
		
	}
	
	
	
}
