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

@Title("Wiki Index Page Class")
@Description("Description: Contains all Wiki Index related test cases")
@Listeners(TestListener.class)
public class WikiIndexPageTest {

	WebDriver driver;
	Map<String, String> map;

	BasePageClass basePage;
	WikiIndexPage wikiIndexPage;
	WikiHomePage wikiHomePage;
	Logger log = Logger.getLogger(WikiIndexPageTest.class);
	
	
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

	
	@Features("Wiki Index")
	@Stories("Wiki Index Stories 1")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify index 1")
	@Description("Description: wiki index 1 should render properly")
	@Test
	public void verifyEnglishLink() throws InterruptedException, IOException {
		log.info("|| Wiki Index page, thread : " + Thread.currentThread().getName());
		basePage.get("https://www.wikipedia.org/");
		wikiIndexPage = new WikiIndexPage(driver);
		wikiIndexPage.clickEnglish();
		
		wikiHomePage = new WikiHomePage(driver);
		Assert.assertTrue(wikiHomePage.getCurrentUrl().endsWith("wiki/Main_Page"), "title mismatch");
		
	}
	
	
	@Features("Wiki Index")
	@Stories("Wiki Index Stories 2")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Verify index 2")
	@Description("Description: wiki index 2 should render properly")
	@Test
	public void verifySearchBox() throws InterruptedException, IOException {
		log.info("|| Wiki Index page, thread : " + Thread.currentThread().getName());
		basePage.get("https://www.wikipedia.org");

		wikiIndexPage = new WikiIndexPage(driver);
		wikiIndexPage.setTextInSearchBox("Index");
		wikiIndexPage.clickSearchBoxButton();
		
		wikiHomePage = new WikiHomePage(driver);
//		Assert.assertTrue(driver.getCurrentUrl().endsWith("wiki/Index"), "title mismatch");
		Assert.assertTrue(wikiHomePage.getCurrentUrl().endsWith("wiki/Index"), "title mismatch");
		
	}
	
	
	
	
}
