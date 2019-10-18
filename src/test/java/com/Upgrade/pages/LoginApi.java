package com.Upgrade.pages;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.Upgrade.common.BasePage;
import com.Upgrade.pojo.LoansInReview;
import com.Upgrade.pojo.LoginSuccessResponse;
import com.Upgrade.utility.ApiHelper;
import com.Upgrade.utility.ExtentLogger;
import com.Upgrade.utility.Helper;

/**
 * @author praveen
 *
 */
public class LoginApi extends BasePage {
	String urlDataFile = "/Resources/apiData/urlData.json";
	String headerDataFile = "/Resources/apiData/headers.json";
	String loginJsonPath = "/Resources/jsonRequests/PostLogin.json";
	String invlaidLoginJsonPath = "/Resources/jsonRequests/inValidPostLogin.json";
	String attributePersonalLoan = Helper.readInput.getProperty("attributeValue");

	ApiHelper apiHelper;

	public LoginApi(WebDriver driver) {
		super(driver);
		apiHelper = new ApiHelper();

	}
	/*
	 * This method is used to send a post Request and validate whether 200 Success
	 * code is returned
	 */

	public void loginApi_Validation() {
		apiHelper.postJsonRequest(loginJsonPath, "login_url", urlDataFile);
	}

	/*
	 * This method is used to send a post request with invalid credentials and
	 * validates whether 401 unauthorised error code is returned
	 */
	public void loginApiInvalidCredentials_Validation() {
		apiHelper.postErrorMessageResponse(invlaidLoginJsonPath, "login_url", urlDataFile, 401);
	}

	/*
	 * This method is used to send a post request and extract the response. The
	 * response is then converted in objects using pojo class and respective
	 * attribute values is validated from the pojo class
	 */
	public void loginApi_attributeValidation() {
		postSuccessMessageResponse(loginJsonPath, "login_url", urlDataFile, attributePersonalLoan);

	}

	/**
	 * @param path
	 *            is the path to login.Json file
	 * @param key
	 *            is the key for identifying the URL values
	 * @param UrlPath
	 *            is the path to urlData.json file This Method verifies whether the
	 *            product type attribute value is equal to Personal loan
	 *            LoginSuccessResponse is a pojo class created to deserialize the
	 *            Json response and convert into java objects which can utilised
	 *            easily. This Methos i couldn't make it as a generic one . But, i
	 *            can make another method generic using json path
	 */
	public void postSuccessMessageResponse(String path, String key, String UrlPath, String attributeValue) {

		try {
			apiHelper.setUrl(key, UrlPath);
			JSONObject requestBody = apiHelper.ReadJson(path);
			LoginSuccessResponse loginSuccessResponse = given().headers(apiHelper.getHeader()).body(requestBody)
					.contentType("application/json").when().post(apiHelper.getUrl()).then().extract().response()
					.as(LoginSuccessResponse.class);

			List<LoansInReview> productType = loginSuccessResponse.getLoansInReview();

			Assert.assertEquals(productType.get(0).getProductType(), attributeValue);
		} catch (Exception e) {
			ExtentLogger.logInfo("Exception in postErrorMessageResponse " + e);
		}

	}

}
