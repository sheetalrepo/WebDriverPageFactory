package com.helper;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pages.BasePageClass;

public class TestListener implements ITestListener {

	/*
	 * It will keeps listening every test. On any test failure this code will get trigger 
	 * and screenshot will be saved in specific folder
	 */
	public void onTestFailure(ITestResult result) {
		String testclassRaw = result.getTestClass().toString().trim();
		String testclass = testclassRaw.substring(32, testclassRaw.length() - 1); 
		String testname = result.getName().toString().trim();
		
		try {
			BasePageClass.getInstance().getScreenshot(testclass, testname);
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

}