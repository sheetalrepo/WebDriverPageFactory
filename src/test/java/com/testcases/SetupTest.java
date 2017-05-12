package com.testcases;

import org.testng.annotations.BeforeSuite;


/*
 * Run this test class in every testng.xml files to initialize driver once
 */
public class SetupTest {

//	WebDriver driver;
//	BasePage basePage;
//	HomePage homePage;
//	
	@BeforeSuite
	public void init() {
		System.out.println("*********************#####  Before Suite *********************");
//		BasePage.getInstance().getDriver();
	}
	
	
	
	
			
}
