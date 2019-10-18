package com.Upgrade.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Upgrade.common.BaseTest;

import com.Upgrade.pages.LandingPage;
import com.Upgrade.pages.Login;

public class LoginTest extends BaseTest {

	
	Login login;

	@BeforeClass
	public void initialize() {
		
		login = new Login(BaseTest.getDriver());
	}

	@Test(description="TC08: Validate whether the landing page is the login page Url",groups="LoginTest",dependsOnGroups= {"CheckYourRateTest","CreateAccountTest","PayOffOfferTest"})
	public void TC_08_loginUrl_validation() {
		login.urlPageValidation();
	}
	@Test(description="TC09: Validate whether all the fields are mandatory in the login page",groups="LoginTest",dependsOnMethods="TC_08_loginUrl_validation")
	public void TC_09_manadatoryField_Validation() {
		login.emptyField_Validation();
	}
	
	@Test(description="TC10: Validate whether the incorrect credentials error messages are displayed",groups="LoginTest",dependsOnMethods="TC_09_manadatoryField_Validation")
	public void TC_10_incorrectField_Validation() {
		login.inCorrectCredentials_Validation();
	}
	
	@Test(description="TC11: validate whether the loign is successful",groups="LoginTest",dependsOnMethods="TC_10_incorrectField_Validation")
	public void TC_11_SigninSuccess_Validation() {
		login.loginSuccess_Validation();
	}
	
	
}
