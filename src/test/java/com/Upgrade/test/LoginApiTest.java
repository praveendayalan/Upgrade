package com.Upgrade.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Upgrade.common.BaseTest;
import com.Upgrade.pages.LoginApi;

public class LoginApiTest extends BaseTest{
	LoginApi loginApi;
	@BeforeClass
	public void initialize() {
	loginApi = new LoginApi(BaseTest.getDriver());
	}
	
	@Test(description="TC17: Validate the login api retruns success status code 200",groups="LoginApiTest",dependsOnGroups={"CheckYourRateTest","CreateAccountTest","PayOffOfferTest","LoginTest","PayOffOfferPostLoginTest"})
	public void TC_17_loginAPI_SuccessCodeValidation() {
		loginApi.loginApi_Validation();
	}
	
	@Test(description="TC18: Validate whether the error code 401 is displayed for invlaid credentials",groups="LoginApiTest")
	public void TC_18_loginAPI_ErrorCodeValidation() {
		loginApi.loginApiInvalidCredentials_Validation();
		
	}
	
	@Test(description="TC19: Validate whether the response returns the correct attribute",groups="LoginApiTest")
	public void TC_19_loginAPI_AttributeValidation() {
	
		loginApi.loginApi_attributeValidation();
	}

}
