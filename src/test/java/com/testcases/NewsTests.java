package com.testcases;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.helper.PropertyFileReader;
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
		PropertyConfigurator.configure("log4j.properties");
		log.info("|| News Before Class, thread: " + Thread.currentThread().getName());
		log.debug("I AM DEBUG");
		

		basePage = BasePageClass.getInstance();
		driver = basePage.getDriver();
		//basePage.getDriver();
		propertyMap = basePage.getProperties();
		log.debug("News driver hashcode: "+driver.hashCode());
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		// Thread.sleep(5000);

		basePage.quit();
		//basePage.close();
	}

	@Test
	public void verifyNews1() throws MalformedURLException, InterruptedException {
		
		log.info("|| News Page Test 1, thread : " + Thread.currentThread().getName());
		basePage.get("https://www.bing.com/");
		Thread.sleep(20000);
	}

	@Test
	public void verifyNews2() throws MalformedURLException, InterruptedException {
		log.info("|| News Page Test 2, thread : " + Thread.currentThread().getName());
		
		basePage.get("http://www.msn.com/en-in/");
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>  "+driver.toString());
		
//		Thread.sleep(1000);
//		Assert.assertTrue(false);
		
	}

}
