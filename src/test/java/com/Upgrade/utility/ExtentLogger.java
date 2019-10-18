package com.Upgrade.utility;

import org.testng.ITestResult;
import org.testng.annotations.Optional;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentLogger {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extReports;
	public static ExtentTest extTest;
	
	
	public static void startReport()
     {
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Report/testReport.html");
         htmlReporter.config().setReportName("Upgrade Test Automation Report");
         htmlReporter.config().setDocumentTitle("Upgrade - Automation Report"); 
         htmlReporter.config().setEncoding("UTF-8");
         extReports = new ExtentReports();
         extReports.setSystemInfo("Environment", "Local");
         extReports.attachReporter(htmlReporter);
     }	
	 
	public static void startTest(String testCaseName, @Optional String testDescription){	
		 extTest = extReports.createTest(testCaseName, testDescription);
		 System.out.println("<INFO> : Test Case Exceution Started & In Progress");
	 }
	
	public static void getResults(ITestResult result) throws Exception{
		
		
		String testDescription = result.getMethod().getDescription();
		
		if (testDescription == null){
			testDescription = "Test has no description";
		}
		
		String testDefnition = testDescription.substring(testDescription.lastIndexOf("-")+1).substring(5);
		
		if (result.getStatus()==ITestResult.SUCCESS){
			 extTest.log(Status.PASS, "Test Validated - "+testDescription);
			 System.out.println("INFO: Test Passed");
		 }
		
		 else if (result.getStatus()==ITestResult.FAILURE) {
			 extTest.log(Status.ERROR, result.getThrowable().getLocalizedMessage());
			 extTest.log(Status.FAIL, "Failed Test - "+ testDefnition);
			 System.out.println("INFO: Test Failed");
			 
		}else {
			extTest.log(Status.SKIP, "Skipped the Test - " + testDescription);
			System.out.println("INFO: Test Skipped");
		} 
	 }
	 
	public static void endTesting(String suiteName)
	{
		System.out.println("Test case execution for " + suiteName +"is finished");
	}
	
	public static void flushReport(){
		 extReports.flush();
	 }
	
	public static void logPass(String successMessage){
		extTest.log(Status.PASS, successMessage);
	}
	
	public static void logFail(String failureMessage){
		extTest.log(Status.FAIL, failureMessage);
	}

	
	public static void logInfo(String infoMessage){
		System.out.println(infoMessage);
		extTest.log(Status.INFO, infoMessage);
	}
	
	public static void logError(String errorMessage){
		extTest.log(Status.ERROR, errorMessage);
	}
	
	public static void logWarning(String warnMessage){
		extTest.log(Status.WARNING, warnMessage);
	}
	
	public static void logFatal(String fatalMessage){
		extTest.log(Status.FATAL, fatalMessage);
	}
	
	public static void logSkip(String skipMessage){
		extTest.log(Status.SKIP, skipMessage);
	}
	
	public static void logDebug(String debugMessage){
		extTest.log(Status.DEBUG, debugMessage);
	}
	

}
