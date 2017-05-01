package com.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.BasePageClass;
import com.pages.FlightStatusPage;
import com.pages.HomePage;
import com.pages.HotelsPage;

public class NewsTests {

	WebDriver driver;
	Map<String, String> propertyMap;

	BasePageClass basePage;
	HomePage homePage;
	HotelsPage hotelsPage;
	FlightStatusPage flightStatusPage;
	Logger log = Logger.getLogger(NewsTests.class);
	HomePage hp = new HomePage();

	@BeforeClass
	public void init() throws MalformedURLException {
		//PropertyConfigurator.configure("log4j.properties");
		log.info("|| News Before Class, thread: " + Thread.currentThread().getName());
		
		basePage = BasePageClass.getInstance();
		driver = basePage.getDriver();
		//basePage.getDriver();
		propertyMap = basePage.getProperties();
		log.debug("News driver hashcode: "+driver.hashCode());
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		basePage.quit();
	}

	@Test
	public void verifyNews1() throws MalformedURLException, InterruptedException {
		
		log.info("|| News Page Test 1, thread : " + Thread.currentThread().getName());
		basePage.get("https://www.bing.com/");
		Assert.assertTrue(false);
		
	}

	@Test
	public void verifyNews2() throws InterruptedException, IOException {
		log.info("|| News Page Test 2, thread : " + Thread.currentThread().getName());
		
		basePage.get("http://www.msn.com/en-in/");
		
		Assert.assertTrue(true);
		//basePage.getScreenshot(driver, "NewsTests", "verifyNews2");
		Thread.sleep(2000);
	}
	
	


}
