package com.Upgrade.common;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Upgrade.utility.ExtentLogger;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	private static WebDriver driver;
	public String testName;
	public String testDescription = null;
	
	
	
	
	@BeforeSuite
	@Parameters("browser")
	public void loadDriver(@Optional("Chrome") String browserName) {
		driver = returnDriver(browserName);
		ExtentLogger.startReport();
	}
	
	@BeforeMethod
	public void startTest(Method method)
	{
		testName=method.getName();
		Test test = method.getAnnotation(Test.class);
		testDescription = test.description();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(testName+" : "+testDescription);
		ExtentLogger.startTest(testName, testDescription);	
	}
	
	@AfterMethod
	public void endTestAndGetResult(ITestResult result)
	{
		try {
			ExtentLogger.getResults(result);
		    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void tearDownDriver() {
		driver.quit();
		ExtentLogger.flushReport();	
		ExtentLogger.endTesting("UpgradeQA");
	}
	
	public WebDriver returnDriver(String browserName) {
		
		switch (browserName)
		{
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
			
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
			
		default:
        	System.out.println(driver.toString()+ "Not Supported");
        	break;  	
		}
	return driver;
	}
	
	
	public static WebDriver getDriver() {
		return driver;
	}

}
