package com.Upgrade.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Upgrade.common.BaseTest;
import com.Upgrade.pages.LoginApi;
import com.Upgrade.pages.PayOffOffer;
import com.Upgrade.pages.PayOffOfferPostLogin;

public class PayOffOfferPostLoginTest extends BaseTest {
	
	PayOffOfferPostLogin payOffOfferPostLogin;
	
	@BeforeClass
	public void initialize() {
		
		payOffOfferPostLogin = new PayOffOfferPostLogin(BaseTest.getDriver());
	}

	@Test(description="TC12: Validate whether the loan Amount is equal",groups="PayOffOfferPostLoginTest",dependsOnGroups= {"CheckYourRateTest","CreateAccountTest","PayOffOfferTest","LoginTest"})
	public void TC_12_usrloanAmount_Validation() {
		payOffOfferPostLogin.loanAmount_Validation();
	}
	
	@Test(description="TC13: Validate whther the  Monthly Payment is equal",groups="PayOffOfferPostLoginTest",dependsOnMethods="TC_12_usrloanAmount_Validation")
	public void TC_13_usrMonthlyPayment_Validation() {
		payOffOfferPostLogin.monthlyPayment_Validation();
	}
	
	@Test(description="TC14: Validate whether the loan Term is equal",groups="PayOffOfferPostLoginTest",dependsOnMethods="TC_13_usrMonthlyPayment_Validation")
	public void TC_14_usrLoanTerm() {
		payOffOfferPostLogin.loanTerm_Validation();
	}
	
	@Test(description="TC15: Validate the Interest Rate",groups="PayOffOfferPostLoginTest",dependsOnMethods="TC_14_usrLoanTerm")
	public void TC_15_usrInterestRate_Validation() {
		payOffOfferPostLogin.interestRate_Validation();
	}
	
	@Test(description="TC16: Validate the APR",groups="PayOffOfferPostLoginTest",dependsOnMethods="TC_15_usrInterestRate_Validation")
	public void TC_16_usrApr_Validation() {
		payOffOfferPostLogin.Apr_Validation();
	}
	
}
