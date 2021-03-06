package com.testcases;

import java.awt.AWTException;
import java.io.IOException;
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
import com.pages.HotelsPage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("News Page Test Class")
@Description("Description: Contains all News related test cases")
@Listeners(TestListener.class)
public class NewsTests {

	WebDriver driver;
	Map<String, String> map;

	BasePageClass basePage;
	HomePage homePage;
	HotelsPage hotelsPage;
	FlightStatusPage flightStatusPage;
	Logger log = Logger.getLogger(NewsTests.class);
	
	
	@BeforeClass
	public void init() throws IOException, AWTException {
		//PropertyConfigurator.configure("log4j.properties");
		log.info("|| News Before Class, thread: " + Thread.currentThread().getName());
		
		basePage = BasePageClass.getInstance();
		driver = basePage.getDriver();
		map = basePage.getProperties();
		log.debug("News driver hashcode: "+driver.hashCode());
		
	}

	@AfterClass
	public void tearDown() throws InterruptedException, IOException, AWTException {
		basePage.quit();
	}

	
	@Features("News Feature 1")
	@Stories("News Stories 1")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify News 1")
	@Description("Description: News 1 should render properly")
	@Test
	public void verifyNews1() throws InterruptedException, IOException, AWTException {
		log.info("|| News Page Test 1, thread : " + Thread.currentThread().getName());
		basePage.get(map.get("server"));
		BasePageClass.attachScreenShotInAllureReport(basePage.getScreenshot(driver, "NewsTests", "AAA"));
		basePage.get("https://www.wikipedia.org/");
		BasePageClass.attachScreenShotInAllureReport(basePage.getScreenshot(driver, "NewsTests", "BBB"));
		Assert.assertTrue(true);
		Thread.sleep(2000);
		
		//basePage.stoprecording();
	}

	@Features("News Feature 1")
	@Stories("News Stories 2")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify News 2")
	@Description("Description: News 2 should render properly")
	@Test
	public void verifyNews2() throws InterruptedException, IOException {
		log.info("|| News Page Test 2, thread : " + Thread.currentThread().getName());
		basePage.get("http://www.msn.com/en-in/");
		Assert.assertTrue(false);
		Thread.sleep(2000);
	}
	
	
	
	
}
