package com.Upgrade.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Upgrade.common.BaseTest;
import com.Upgrade.pages.CreateAccount;
import com.Upgrade.pages.LandingPage;

public class CreateAccountTest extends BaseTest {
	
	LandingPage landingPage;
	CreateAccount createAccount;
	
	@BeforeClass
	public void initialize() {
		landingPage = new LandingPage(BaseTest.getDriver());
		createAccount = new CreateAccount(BaseTest.getDriver());
	}
	
	@Test(description = "TC20: Validate Email field whether it doesn't accepts an in-valid email",groups="CreateAccountTest",dependsOnGroups="CheckYourRateTest")
	public void TC_20_emailFieldValidation() {
		createAccount.emailField_Validation();
	}
	
	@Test(description = "TC21: Validate password field whether it doesn't  accepts an in- valid password",groups="CreateAccountTest",dependsOnMethods="TC_20_emailFieldValidation")
	public void TC_21_passwordFieldValidation() {
		createAccount.Password_Vaidation();
	}
	@Test(description="TC05: Validate whether the user can enter Basic Information and create an account",groups="CreateAccountTest",dependsOnMethods="TC_21_passwordFieldValidation")
	public void TC_05_basicInformation_Validation() {
		createAccount.enterName();
		createAccount.enterAddress();
		createAccount.enterDob();
		createAccount.enterIncome();
		createAccount.accountCreation();
	}

}
