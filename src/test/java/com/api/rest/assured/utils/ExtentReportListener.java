package com.api.rest.assured.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportListener implements ITestListener {
	public static ExtentReports extentReports;
	ExtentSparkReporter  extentSparkReporter ;
	public static ExtentTest extentTest;
	
	@BeforeSuite
	public void createReport() throws Exception {
		String currentDateTime = new SimpleDateFormat("dd_MM_yy_HH.mm.ss").format(Calendar.getInstance().getTime());
		//extentHtmlReporter = new ExtentHtmlReporter("./ExtentReport/Report_"+currentDateTime+".html");
		extentSparkReporter = new ExtentSparkReporter ("./ExtentReport/tests/report.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setReportName(getClass().getSimpleName());
		extentSparkReporter.config().setDocumentTitle(getClass().getSimpleName());
	}
	
	
	  @BeforeMethod 
	  
	  public void startReport(Method method ,ITestResult result) { 
	  extentTest =extentReports.createTest(method.getName()); 
	  extentTest.log(Status.INFO, result.getMethod().getMethodName());}
	 
	
	
	
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "PASSED");

	}

	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "FAILED");

	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "SKIPPED");

	}
	@AfterSuite
	public void endReport() {
		extentReports.flush();
	}
	

}

