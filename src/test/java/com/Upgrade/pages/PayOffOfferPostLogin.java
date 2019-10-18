package com.Upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.Helper;

public class PayOffOfferPostLogin extends BasePage {

	Helper helper;
	PayOffOffer payOffOffer;

	public PayOffOfferPostLogin(WebDriver driver) {
		super(driver);
		helper = new Helper(driver);
		payOffOffer = new PayOffOffer(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@data-auto='userLoanAmount']")
	private WebElement usrLoanAmnt;

	@FindBy(xpath = "//span[@data-auto='defaultMonthlyPayment']")
	private WebElement monthlyPayment;

	@FindBy(xpath = "//div[@data-auto='defaultLoanTerm']")
	private WebElement loanTerm;

	@FindBy(xpath = "//div[@data-auto='defaultLoanInterestRate']")
	private WebElement interestRate;

	@FindBy(xpath = "//div[@data-auto='defaultMoreInfoAPR']")
	private WebElement APR;

	/* This method is used to return monthly payment of the user */

	public String monthlyPayment_AftrLogin() {

		return helper.getText(monthlyPayment);

	}

	/* This method is used to return loan Amount of the user */
	public String usrLoanAmount_AftrLogin() {
		return helper.getText(usrLoanAmnt);
	}

	/* This method is used to return loan term of the user */
	public String loanTerm_AftrLogin() {
		return helper.getText(loanTerm);
	}

	/* This method is used to return interest Rate of the user */
	public String interestRate_AftrLogin() {
		return helper.getText(interestRate);
	}

	/* This method is used to return APR of the user */
	public String Apr_AftrLogin() {
		return helper.getText(APR);

	}

	/*
	 * This method validates the loan amount retrieved before the login and after
	 * the login
	 */
	public void loanAmount_Validation() {
		helper.assertString(usrLoanAmount_AftrLogin(), payOffOffer.usrLoanAmount_BfrLogin());
		System.out.print("The loan amount is: " + usrLoanAmount_AftrLogin());

	}

	/*
	 * This method validates the monthly payment retrieved before the login and
	 * after the login
	 */
	public void monthlyPayment_Validation() {
		helper.assertString(monthlyPayment_AftrLogin(), payOffOffer.monthlyPayment_BfrLogin());
		System.out.print("The Monthly Payment is: " + monthlyPayment_AftrLogin());
	}

	/*
	 * This method validates the loan term retrieved before the login and after the
	 * login
	 */
	public void loanTerm_Validation() {
		helper.assertString(loanTerm_AftrLogin(), payOffOffer.loanTerm_BfrLogin());
		System.out.print("The loan Term is: " + loanTerm_AftrLogin());

	}

	/*
	 * This method validates the interest rate retrieved before the login and after
	 * the login
	 */
	public void interestRate_Validation() {
		helper.assertString(interestRate_AftrLogin(), payOffOffer.interestRate_BfrLogin());
		System.out.print("The Interest Rate  is: " + interestRate_AftrLogin());
	}

	/*
	 * This method validates the APR retrieved before the login and after the login
	 */
	public void Apr_Validation() {
		helper.assertString(Apr_AftrLogin(), payOffOffer.Apr_BfrLogin());
		System.out.print("The APR is: " + Apr_AftrLogin());

	}

}
