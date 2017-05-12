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
import com.pages.WikiIndexPage;
import com.pages.WikiHomePage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Wiki Home Page Class")
@Description("Description: Contains all Wiki Home related test cases")
@Listeners(TestListener.class)
public class WikiHomePageTest {

	WebDriver driver;
	Map<String, String> map;

	BasePageClass basePage;
	WikiIndexPage wikiIndexPage;
	WikiHomePage wikiHomePage;
	Logger log = Logger.getLogger(WikiHomePageTest.class);
	
	
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

	
	@Features("Wiki Homepage")
	@Stories("Wiki Home Stories 1")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify wiki home 1")
	@Description("Description: wiki home 1 should render properly")
	@Test
	public void verifySearchBox() throws InterruptedException, IOException {
		log.info("|| Wiki Index page, thread : " + Thread.currentThread().getName());
		basePage.get("https://en.wikipedia.org/wiki/Main_Page");
		wikiHomePage = new WikiHomePage(driver);
		wikiHomePage.setTextInSearchBox("World");
		wikiHomePage.clickSearchButton();
		
		Thread.sleep(3000);
		//We should have switched to search page and then can get the url
		//Assert.assertTrue(driver.getCurrentUrl().contains("World"),"url mismatch");
		Assert.assertTrue(driver.getCurrentUrl().contains("World"),"url mismatch");
		
	}
	
	
	@Features("Wiki Homepage")
	@Stories("Wiki Home Stories 2")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify wiki home 2")
	@Description("Description: wiki home 2 should render properly")
	@Test
	public void verifyDonateLink() throws InterruptedException, IOException {
		log.info("|| Wiki Index page, thread : " + Thread.currentThread().getName());
		//basePage.get("https://en.wikipedia.org/wiki/Main_Page");
		
		basePage.get("https://www.wikipedia.org/");
		wikiIndexPage = new WikiIndexPage(driver);
		wikiIndexPage.clickEnglish();
		
		wikiHomePage = new WikiHomePage(driver);
		String href = wikiHomePage.getAttributeValue(WikiHomePage.donateToWikiLink, "href");
		Assert.assertTrue(href.contains("donate.wikimedia.org"), "wrong href");
		Thread.sleep(2000);
	}
	
	
	
	
	
}
