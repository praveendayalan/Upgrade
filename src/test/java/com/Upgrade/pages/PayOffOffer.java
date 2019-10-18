package com.Upgrade.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.ExtentLogger;
import com.Upgrade.utility.Helper;
import com.Upgrade.utility.LoadProperties;

public class PayOffOffer extends BasePage {

	public static String offerPageUrl = Helper.readInput.getProperty("offerPageUrl");
	public static String signOutPageUrl = Helper.readInput.getProperty("signOutPageUrl");
	Helper helper;

	public PayOffOffer(WebDriver driver) {
		super(driver);
		helper = new Helper(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[@data-auto='userLoanAmount']")
	private WebElement usrLoanAmnt;

	@FindBy(xpath = "//span[@data-auto='defaultMonthlyPayment']")
	private  WebElement monthlyPayment;

	@FindBy(xpath = "//div[@data-auto='defaultLoanTerm']")
	private WebElement loanTerm;

	@FindBy(xpath = "//div[@data-auto='defaultLoanInterestRate']")
	private WebElement interestRate;

	@FindBy(xpath = "//div[@data-auto='defaultMoreInfoAPR']")
	private WebElement APR;

	@FindBy(xpath = "//label[@for='header-nav-toggle' and contains(text(),'Menu')]")
	private WebElement menuBtn;

	@FindBy(linkText = "Sign Out")
	private WebElement signOut;

	public void offerPageValidation() throws Exception {

		Thread.sleep(3000);
		helper.waitForurl(offerPageUrl, 10);
		helper.assertString(helper.getCurrentUrl(), offerPageUrl);
	}
/*This method is used to retrieve the monthly payment*/
	public String monthlyPayment_BfrLogin() {

		return helper.getText(monthlyPayment);

	}
	/*This method is used to retrieve the loan Amount*/
	public String usrLoanAmount_BfrLogin() {
		return helper.getText(usrLoanAmnt);
	}
	/*This method is used to retrieve the loan term*/
	public String loanTerm_BfrLogin() {
		return helper.getText(loanTerm);
	}
	/*This method is used to retrieve the interest rate*/
	public String interestRate_BfrLogin() {
		return helper.getText(interestRate);
	}
	/*This method is used to retrieve the APR*/
	public String Apr_BfrLogin() {
		return helper.getText(APR);
	}

 /* This method is used to log out from the application 
  The script waits for the element signout to be clickable and then the click operation is performed*/

	public void logOut() throws InterruptedException {

		Thread.sleep(5000);
		if (helper.visibilityOfElement(menuBtn, 100)) {
			helper.clickElement(menuBtn, 10);

		}

		if (helper.waitForElementClickable(signOut, 10)) {
			helper.clickElement(signOut, 10);
		} else {
			ExtentLogger.logInfo(signOut + " is not displayed");
		}

	}
	
	/*This method is used to validate the log out url*/

	public void logOutUrl_Validation() throws InterruptedException {
		this.logOut();
		helper.assertString(helper.getCurrentUrl(), signOutPageUrl);

	}

}
