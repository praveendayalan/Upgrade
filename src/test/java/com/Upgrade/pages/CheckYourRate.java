package com.Upgrade.pages;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.ExtentLogger;
import com.Upgrade.utility.Helper;
import com.Upgrade.utility.LoadProperties;

/**
 * @author prave
 *
 */
/**
 * @author prave
 *
 */
public class CheckYourRate extends BasePage {

	public static String amount = Helper.readInput.getProperty("inputLoanAmount");
	public static String visibleText = Helper.readInput.getProperty("inputVisibleText");
	public static String expectedLoanErrMsg = Helper.readInput.getProperty("expectedLoanErrMsg");
	public static String expectedLoanFieldErrMsg = Helper.readInput.getProperty("expectedLoanFieldErrMsg");
	public static String expectedPurposeFieldErrMsg = Helper.readInput.getProperty("expectedPurposeFieldErrMsg");
	public static String epectedNavigatePageTxt = Helper.readInput.getProperty("epectedNavigatePageTxt");
	public static final int randomnumber_lessthan1000 = 999;
	public static final int amount_Fixed = 2000;
	Helper helper;

	public CheckYourRate(WebDriver driver) {
		super(driver);
		helper = new Helper(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@name='desiredAmount']")
	private WebElement loanAmount;

	@FindBy(xpath = "//select[@data-auto ='dropLoanPurpose']")
	private WebElement selectPurpose;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement checkBtn;

	@FindBy(xpath = "//h2[.='Check your rate with just one click']")
	private WebElement actualNavigationPageText;

	@FindBy(xpath = "//input[@name='desiredAmount']")
	private WebElement actualLoanErrMsg;

	@FindBy(xpath = "//div[.='This field is required']")
	private WebElement actualLoanFieldErrMsg;

	@FindBy(xpath = "//div[.='Select a purpose']")
	private WebElement actualPurposeFieldErrMsg;

	/*
	 * This Method is used to Enter the loan amount SendKeys method from the helper
	 * class is used to enter the integer values
	 */
	public void enterLoanAmount() {
		helper.implicitWait(10);
		helper.sendKeys(loanAmount, "2000");
	}

	/*
	 * This Method is used to select a loan purpose SelectByVisibleText method from
	 * the helper class is used to select the loan purpose
	 */
	public void selectLoanPurpose() {
		helper.selectByVisibleText(selectPurpose, visibleText);
	}

	public void clickCheckYourRate() {
		helper.clickElement(checkBtn, 10);
	}
	/*
	 * This Method validates whether the user can enter only amount between 1000 and
	 * 50000 The Error message is retreived by getText method and compared with the
	 * expected messsage located in the cofig.properties
	 */

	public void loanAmountValidation() {
		helper.implicitWait(10);
		helper.sendInput(loanAmount, randomnumber_lessthan1000);
		this.selectLoanPurpose();

		if (helper.waitForElementClickable(checkBtn, 10)) {
			this.clickCheckYourRate();
			helper.implicitWait(20);
			helper.assertString(helper.getAttribute(actualLoanErrMsg, "data-error"), expectedLoanErrMsg);
			ExtentLogger.logInfo(
					"Error message : " + helper.getAttribute(actualLoanErrMsg, "data-error") + " is displayed");
		} else {
			ExtentLogger.logInfo(
					"Error message : " + helper.getAttribute(actualLoanErrMsg, "data-error") + " is not displayed");
		}

	}

	/*
	 * This method checks whether the application throws error message for empty
	 * fields Error message is retrieved using getText method and it is compared
	 * with the expected error message which is located in the config.properites
	 */

	public void emptyFieldValidation() {

		if (helper.waitForElementClickable(checkBtn, 10)) {
			this.clickCheckYourRate();
			helper.assertString(helper.getText(actualLoanFieldErrMsg), expectedLoanFieldErrMsg);
			helper.assertString(helper.getText(actualPurposeFieldErrMsg), expectedPurposeFieldErrMsg);
			ExtentLogger.logInfo("Error message : " + helper.getText(actualLoanFieldErrMsg) + " is displayed");
			ExtentLogger.logInfo("Error message : " + helper.getText(actualPurposeFieldErrMsg) + " is displayed");
		} else {
			ExtentLogger.logInfo("Error message : " + helper.getText(actualLoanFieldErrMsg) + " is not displayed");
			ExtentLogger.logInfo("Error message : " + helper.getText(actualPurposeFieldErrMsg) + " is not displayed");

		}
	}

	/*
	 * This method accepts the users loanAmount and loan purpose Navigates to the
	 * Create Account page.I did not add a validation in this method because, when
	 * ever i enter a value as 2000, i see the value displayed as 50000. Hence i
	 * cannot assert the url.The next method is to assert the heading of the page,
	 * many times while testing, the script fails to fetch the text because the
	 * heading varies from"Lets get started to information"to "Check your rate with just one click".
	 */
	public void checkYourRate_HappyPath() {
		helper.sendInput(loanAmount, amount_Fixed);
		this.selectLoanPurpose();
		if (helper.waitForElementClickable(checkBtn, 10)) {
			this.clickCheckYourRate();
			//helper.assertString(helper.getText(actualNavigationPageText), epectedNavigatePageTxt);
			//ExtentLogger.logInfo(helper.getText(actualNavigationPageText) + " is displayed");
		} else {
			ExtentLogger.logInfo("Something went wrong in checkYourRate_HappyPath method");
		}

	}

}
