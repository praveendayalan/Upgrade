package com.Upgrade.pages;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.Upgrade.common.BasePage;
import com.Upgrade.utility.Helper;
import com.Upgrade.utility.LoadProperties;

public class LandingPage extends BasePage {
	
	public static String loadUrl = Helper.readInput.getProperty("URL");
	Helper helper;

	public LandingPage(WebDriver driver) {
		super(driver);
		helper = new Helper(driver);
	}

	/*
	 * This method navigates to the base url and validates whether the landing page
	 * url is correct
	 */
	public void urlPageValidation() {
		helper.navigateToUrl(loadUrl);
		helper.maximizeWindow();
		helper.assertString(helper.getCurrentUrl(), loadUrl);
		
	}
}
