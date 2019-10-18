package com.Upgrade.utility;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.ExtentLogger;

public class Helper extends BasePage {
	public static final Properties readInput = LoadProperties.readPropertyFile("\\config.properties");

	public Helper(WebDriver driver) {
		super(driver);
	}

	// This method navigates to any specific url
	public void navigateToUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			System.out.println("Exception in opening the URL " + url);
		}
	}

	// This method returns the current url of the page navigation
	public String getCurrentUrl() {
		return driver.getCurrentUrl();

	}

	// This method compares the actual and the expected value of a string
	public void assertString(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}

	// This method is used to set the window to the maximum size
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	// This method is used to wait without any condition
	public void implicitWait(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

	}

	// This method is used to enter any String input value in the UI
	public void sendKeys(WebElement element, String inputValue) {
		this.clearTextEntry(element);
		element.clear();
		element.sendKeys(inputValue);

	}

	/* This method is used to enter any Integer input value in the UI */

	public void sendInput(WebElement element, int inputValue) {
		element.clear();
		element.sendKeys(String.valueOf(inputValue));

	}

	// This method is used to select a drop-down based on the visible text
	public void selectByVisibleText(WebElement element, String inputValue) {
		Select select = new Select(element);
		if (element.isDisplayed()) {
			select.selectByVisibleText(inputValue);
		} else {
			System.out.println(element + " is not displayed");

		}
	}

	// This method is used to click an element
	public void clickElement(WebElement element, int waitTime) {
		this.implicitWait(10);
		element.click();

	}

	// This method is used to wait for an element which must be clickable
	public boolean waitForElementClickable(WebElement element, int waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			ExtentLogger.logInfo("Exception is clicking element within 10 seconds ");
		}
		return true;
	}

	// This method is used to extract the inner text
	public String getText(WebElement element) {
		return element.getText();
	}

	/*
	 * This Method is used to select the Second index result appearing in the
	 * autocomplete address field.
	 */
	public void autoCompleteSelection(WebElement element1, String inputValue, List<WebElement> element2) {
		this.sendKeys(element1, inputValue);
		List<WebElement> addressList = element2;
		this.implicitWait(20);
		addressList.get(1).click();
	}

	/*
	 * This method is used to click on the checkbox I have used javascript executore
	 * since normal click operation was not able to be performed as the click was
	 * happening on other co-ordinates
	 */
	public void isCheckBoxSelected(WebElement element, int waitTime) {
		if (!element.isSelected()) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

		} else {

		}
	}

	// This method returns a boolean True if the element is Displayed
	public Boolean isDisplayed(WebElement element) {
		Boolean isDisplayed = false;
		if (element.isDisplayed()) {
			isDisplayed = true;
		} else {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/* This method returns a boolean true if the element is visible */
	public boolean visibilityOfElement(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	// This method waits for the URL untill the page loads
	public void waitForurl(String url, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.urlToBe(url));

	}

	// This method is used as an alternative for clear method when we don't have the
	// type = text
	public void clearTextEntry(WebElement element) {
		element.sendKeys(Keys.CONTROL, "a");
		element.sendKeys(Keys.DELETE);

	}

	// This method is used to get attribute of any given element
	public String getAttribute(WebElement element, String inputValue) {
		return element.getAttribute(inputValue);

	}

}
