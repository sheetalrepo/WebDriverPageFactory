package com.testcases;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
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
	Map<String, String> map;

	BasePageClass basePage;
	HomePage homePage;
	HotelsPage hotelsPage;
	FlightStatusPage flightStatusPage;
	Logger log = Logger.getLogger(NewsTests.class);
	
	ScreenRecorder screenRecorder;
	
	@BeforeClass
	public void init() throws IOException, AWTException {
		//PropertyConfigurator.configure("log4j.properties");
		log.info("|| News Before Class, thread: " + Thread.currentThread().getName());
		
		basePage = BasePageClass.getInstance();
		driver = basePage.getDriver();
		map = basePage.getProperties();
		log.debug("News driver hashcode: "+driver.hashCode());
		
		basePage.startrecording();
		
	}

	@AfterClass
	public void tearDown() throws InterruptedException, IOException, AWTException {
		basePage.quit();
		basePage.stoprecording();
	}

	@Test
	public void verifyNews1() throws InterruptedException, IOException, AWTException {
		//basePage.startrecording();
		
		log.info("|| News Page Test 1, thread : " + Thread.currentThread().getName());
		//basePage.get("https://www.bing.com/");
		basePage.get(map.get("server"));
		Assert.assertTrue(false);
		Thread.sleep(2000);
		
		//basePage.stoprecording();
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
