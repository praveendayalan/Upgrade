package com.Upgrade.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.ApiHelper;
import com.Upgrade.utility.ExtentLogger;
import com.Upgrade.utility.Helper;
import com.Upgrade.utility.LoadProperties;

public class Login extends BasePage {

	public static String loginUrl = Helper.readInput.getProperty("loginPageUrl");
	public static String nonExistingUsr = Helper.readInput.getProperty("nonExistingUser");
	public static String inCrctPwd = Helper.readInput.getProperty("inCorrectPassword");
	public static String loginErrorMessage = Helper.readInput.getProperty("loginErrorMessage");
	public static String mandatoryErrorMessage = Helper.readInput.getProperty("mandatoryErrorMessge");

	Helper helper;
	ApiHelper apiHelper;

	public Login(WebDriver driver) {
		super(driver);
		helper = new Helper(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@name='username']")
	private WebElement usrEmailId;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement usrPassword;

	@FindBy(xpath = "//button[@type='submit' and contains(text(),'Sign in to your account')]")
	private WebElement signInBtn;

	@FindBy(xpath = "//div[@data-auto='loginError']")
	private WebElement loginErrMsg;

	@FindBy(xpath = "//div[@data-error='true'and contains(text(),'This field is required')]")
	private WebElement mandatoryFldMsg;

	/*
	 * This method is used to validte the login url CurrentUrl is retrieved by using
	 * getCurrentUrl method in the helper class and compared with the expected Url
	 */
	public void urlPageValidation() {

		helper.navigateToUrl(loginUrl);
		helper.assertString(helper.getCurrentUrl(), loginUrl);

	}

	/*
	 * This method is used to verify whether an error message is displayed on
	 * leaving the field empty. Error message is retrieved by the getText method in
	 * the helper class and compared with the expected error message
	 */
	public void emptyField_Validation() {

		if (helper.waitForElementClickable(signInBtn, 10)) {
			helper.clickElement(signInBtn, 10);
			helper.assertString(helper.getAttribute(usrEmailId, "data-error"), mandatoryErrorMessage);
		} else {
			ExtentLogger.logInfo("Error message : " + helper.getAttribute(usrEmailId, "data-error") + " is not displayed");
		}

	}
	
	/*
	 * This Method is used to verify whether error message is displayed for
	 * incorrect credentials Error message is retrieved by the getText method in the
	 * helper class and compared with the expected error message
	 */

	public void inCorrectCredentials_Validation() {
		helper.sendKeys(usrEmailId, nonExistingUsr);
		helper.sendKeys(usrPassword, inCrctPwd);
		if (helper.waitForElementClickable(signInBtn, 10)) {
			helper.clickElement(signInBtn, 10);
			helper.assertString(helper.getText(loginErrMsg), loginErrorMessage);
			ExtentLogger.logInfo("Error message : " + helper.getText(loginErrMsg) + " is displayed");
		} else {
			ExtentLogger.logInfo("Error message : " + loginErrorMessage + " is not displayed");
		}

	}

	/*
	 * This method is used to verify the successful login. This method verifies
	 * whether the application is navigated to offerpage url after successful login
	 */
	public void loginSuccess_Validation() {

		helper.sendKeys(usrEmailId, CreateAccount.emailAddress);
		helper.sendKeys(usrPassword, CreateAccount.pwd);
		if (helper.waitForElementClickable(signInBtn, 10)) {
			helper.clickElement(signInBtn, 10);
			helper.waitForurl(PayOffOffer.offerPageUrl, 20);
			helper.assertString(helper.getCurrentUrl(), PayOffOffer.offerPageUrl);
		} else {
			ExtentLogger.logInfo("The page navigated to " + helper.getCurrentUrl());

		}

	}

}
