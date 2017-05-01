package com.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pages.BasePageClass;

public class TestListener implements ITestListener {

	WebDriver driver;
	
	/*
	 * It will keeps listening every test. On any test failure this code will get trigger 
	 * and screenshot will be saved in specific folder
	 */
	public void onTestFailure(ITestResult result) {
		String testclassRaw = result.getTestClass().toString().trim();
		String testclass = testclassRaw.substring(32, testclassRaw.length() - 1); 
		String testname = result.getName().toString().trim();
		
		try {
			//driver = BasePageClass.getInstance().getDriver();
			driver = ThreadLocalDriver.getDriver();
			BasePageClass.getInstance().getScreenshot(driver, testclass, testname);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
	
	
	
//	public void getScreenshot(String testclass, String testname) throws IOException {
//
//		//log.info("taking screenshot for failed test case: " + testclass + "_" + testname);
//		String filePath = "./src/test/resources/screenshots/";
//		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//
//		String path = filePath + testclass + "_" + testname + "_" + timestamp;
//
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//		try {
//			FileUtils.copyFile(scrFile, new File(path + ".png"));
//			//log.info("screenshot captured at: " + path + ".png");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	

}
