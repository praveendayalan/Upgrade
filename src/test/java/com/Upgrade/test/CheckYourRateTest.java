package com.Upgrade.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Upgrade.common.BaseTest;
import com.Upgrade.pages.CheckYourRate;
import com.Upgrade.pages.LandingPage;
@Test(groups= "CheckYourRateTest")
public class CheckYourRateTest extends BaseTest {

	LandingPage landingPage;
	CheckYourRate checkYourRate;

	@BeforeClass
	public void initialize() {
		landingPage = new LandingPage(BaseTest.getDriver());
		checkYourRate = new CheckYourRate(BaseTest.getDriver());
	}

	@Test(description = "TC01: Verify whether the ladning page url is correct ")
	public void TC_01_landingPageurlValidation() {
		landingPage.urlPageValidation();
	}

	@Test(description = "TC02: Validate the Check your Rate does not accept empty field",dependsOnMethods="TC_01_landingPageurlValidation")
	public void TC_02_emptyField_Validation() {
		checkYourRate.emptyFieldValidation();
	}

	@Test(description = "TC03: Validate the loan amount does not accept below 1000 and above 50000",dependsOnMethods="TC_02_emptyField_Validation")
	public void TC_03_loanAmount_Validation() {
		checkYourRate.loanAmountValidation();
	}

	@Test(description = "TC04: Validate Check your Rate functionality",dependsOnMethods="TC_03_loanAmount_Validation")
	public void TC_04_checkYourRate_Validation() {
		checkYourRate.checkYourRate_HappyPath();
	}

}
