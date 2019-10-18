package com.Upgrade.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.ExtentLogger;
import com.Upgrade.utility.Helper;
import com.Upgrade.utility.LoadProperties;

public class CreateAccount extends BasePage {

	public static String firstName = Helper.readInput.getProperty("firstName");
	public static String lastName = Helper.readInput.getProperty("lastName");
	public static String address = Helper.readInput.getProperty("address");
	public static String cityName = Helper.readInput.getProperty("city");
	public static String stateName = Helper.readInput.getProperty("state");
	public static String zipCode = Helper.readInput.getProperty("zipCode");
	public static String dob = Helper.readInput.getProperty("dob");
	public static String indvlAnnualIncome = Helper.readInput.getProperty("invdlAnnualIncome");
	public static String addtnlAnnualIncome = Helper.readInput.getProperty("addtnlAnnualIncome");
	public static String emailAddress = Helper.readInput.getProperty("emailAddress");
	public static String pwd = Helper.readInput.getProperty("pwd");
	public static String inValidEmailId = Helper.readInput.getProperty("inValidEmailAddress");
	public static String emailErrorMsg = Helper.readInput.getProperty("emailErrorMessage");
	public static String inValidPassword = Helper.readInput.getProperty("inValidPwd");
	public static String passwrodErrorMsg = Helper.readInput.getProperty("passwordErrorMesssage");
	Helper helper;

	public CreateAccount(WebDriver driver) {
		super(driver);
		helper = new Helper(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='borrowerFirstName']")
	private WebElement borrowerFrstNme;

	@FindBy(xpath = "//input[@name='borrowerLastName']")
	private WebElement borrowerLstNme;

	@FindBy(xpath = "//input[@name='borrowerStreet']")
	private WebElement borrowerStrtNme;

	@FindBy(xpath = "//input[@name='borrowerCity']")
	private WebElement borrowerCity;

	@FindBy(xpath = "//input[@name='borrowerState']")
	private WebElement borrowerState;

	@FindBy(xpath = "//input[@name='borrowerZipCode']")
	private WebElement borrowerZipcde;

	@FindBy(xpath = "//ul[@class ='geosuggest__suggests']/li")
	private List<WebElement> addressList;

	@FindBy(xpath = "//input[@name='borrowerDateOfBirth']")
	private WebElement borrowerDob;

	@FindBy(xpath = "//input[@name='borrowerIncome']")
	private WebElement annualIncome;

	@FindBy(xpath = "//input[@name='borrowerAdditionalIncome']")
	private WebElement additionalIncome;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement emailId;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement checkBox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement checkBtn;

	/*
	 * This method is used to enter the users firstname and lastname SendKeys method
	 * located in the helper class is used to enter the names
	 */
	public void enterName() {
		helper.sendKeys(borrowerFrstNme, firstName);
		helper.sendKeys(borrowerLstNme, lastName);
	}

	/*
	 * This method is used to select the address from the address table
	 * autoCompeteSelection method in the helper class is used to select the address
	 */
	public void enterAddress() {

		// helper.sendKeys(borrowerStrtNme, address);
		helper.autoCompleteSelection(borrowerStrtNme, address, addressList);
		// helper.sendKeys(borrowerCity, cityName);
		// helper.sendKeys(borrowerState, stateName);
		// helper.sendKeys(borrowerZipcde, zipCode);

	}

	/*
	 * This method is used to enter the users Date of birth SendKeys method in the
	 * helper class is used to enter date of birth
	 */

	public void enterDob() {
		helper.sendKeys(borrowerDob, dob);

	}

	/*
	 * This method is used to enter the users income send Keys method located in the
	 * helper class is used to enter the income
	 */
	public void enterIncome() {
		helper.sendKeys(annualIncome, indvlAnnualIncome);
		helper.sendKeys(additionalIncome, addtnlAnnualIncome);

	}

	/*
	 * This method is used to enter the email id and password of the user SendKeys
	 * method in the helper class is used to enter the email id and password
	 */
	public void enterEmailAndPassword() {
		helper.sendKeys(emailId, emailAddress);
		helper.sendKeys(password, pwd);
	}

	/*
	 * This method is used to create an account This method accepts the email id and
	 * password from the enterEmailAndPassword method and creates an account
	 * isCheckBoxSelected method in the helper class is used to tick the checkbox
	 */
	public void accountCreation() {
		this.enterEmailAndPassword();
		helper.isCheckBoxSelected(checkBox, 10);
		if (helper.waitForElementClickable(checkBtn, 10)) {
			helper.clickElement(checkBtn, 10);
		}
	}

	/*
	 * This Method verifies whether the Email field is validated Email error message
	 * is retrieved using get attribute method and compared with the expected email
	 * error message located in the config.properites
	 */
	public void emailField_Validation() {
		helper.sendKeys(emailId, inValidEmailId);
		helper.isCheckBoxSelected(checkBox, 10);
		if (helper.waitForElementClickable(checkBtn, 10)) {
			helper.clickElement(checkBtn, 10);
			helper.assertString(helper.getAttribute(emailId, "data-error"), emailErrorMsg);
			ExtentLogger.logInfo("Error message : " + helper.getAttribute(emailId, "data-error") + " is displayed");
		} else {
			ExtentLogger.logInfo("Error message : " + helper.getAttribute(emailId, "data-error") + " is not displayed");
		}

	}
	/*
	 * This Method verifies whether the password field is validated Password error
	 * message is retrieved using get attribute method and compared with the
	 * expected password error message located in the config.properites
	 */

	public void Password_Vaidation() {
		helper.sendKeys(password, inValidPassword);
		helper.isCheckBoxSelected(checkBox, 10);
		if (helper.waitForElementClickable(checkBtn, 10)) {
			helper.clickElement(checkBtn, 10);
			helper.assertString(helper.getAttribute(password, "data-error"), passwrodErrorMsg);
			ExtentLogger.logInfo("Error message : " + helper.getAttribute(emailId, "data-error") + " is displayed");
		} else {
			ExtentLogger.logInfo("Error message : " + helper.getAttribute(emailId, "data-error") + " is not displayed");
		}
	}
}
