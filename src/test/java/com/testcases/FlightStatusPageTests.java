package com.testcases;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.TestListener;
import com.pages.BasePageClass;
import com.pages.FlightStatusPage;
import com.pages.HomePage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Flight Page Test Class")
@Description("Description: Contains all Flight related test cases")
@Listeners(TestListener.class)
public class FlightStatusPageTests {

	WebDriver driver;
	Map<String, String> propertyMap;

	BasePageClass basePage;
	HomePage homePage;
	FlightStatusPage flightStatusPage;

	Logger log = Logger.getLogger(FlightStatusPage.class);

	@BeforeClass
	public void init() throws MalformedURLException, InterruptedException {
		//PropertyConfigurator.configure("log4j.properties");
		log.info("|| Flight Before Class, thread:  " + Thread.currentThread().getName());

		basePage = BasePageClass.getInstance();
		driver = basePage.getDriver();
		propertyMap = basePage.getProperties();
		log.debug("Flight status driver hashcode: "+driver.hashCode());
	}

	@AfterClass
	public void tearDown() {
		basePage.quit();
	}

	@Features("Flight Feature 1")
	@Stories("Flight Stories 1")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify Flight 1")
	@Description("Description: Flight 1 should render properly")
	@Test
	public void verifyDepartureArrivalCityTest() throws MalformedURLException, InterruptedException {
		log.info("|| Flight Status Page Test 1  >> " + Thread.currentThread().getName());
		basePage.get("https://book2.spicejet.com/");

		homePage = new HomePage(driver);
		homePage.isHomePageLoaded();
		homePage.clickFlighStatusTab();

		Thread.sleep(2000);
		flightStatusPage = new FlightStatusPage(driver);
		flightStatusPage.isFlightStatusPageLoaded();
		flightStatusPage.setDepartureCity(1);
		flightStatusPage.setArrivalCity(5);

		Thread.sleep(2000);
		basePage.get("https://www.wikipedia.org/");
		Assert.assertTrue(false);
		Thread.sleep(2000);
	}

	
	@Features("Flight Feature 1")
	@Stories("Flight Stories 2")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify Flight 2")
	@Description("Description: Flight 2 should render properly")
	//@Test
	public void verifyPromotionalBannerCount() throws MalformedURLException, InterruptedException {
		log.info("|| Flight Status Page Test 2  >> " + Thread.currentThread().getName());

//		driver.get("https://book2.spicejet.com/");
//		homePage = new HomePage(driver);
//		homePage.isHomePageLoaded();
//		homePage.clickFlighStatusTab();
//		Thread.sleep(2000);
//		flightStatusPage = new FlightStatusPage(driver);
//		flightStatusPage.isFlightStatusPageLoaded();
//		Assert.assertEquals(flightStatusPage.getPromotionalBannersCount(), 6, "Banner count is wrong");

		basePage.get("https://github.com/");
		Thread.sleep(2000);
		Assert.assertTrue(true);
	}

}
