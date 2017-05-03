package com.pages;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;

import com.helper.DriverFactory;
import com.helper.DriverRepo;
import com.helper.PropertyFileReader;
import com.helper.ThreadLocalDriver;

import org.apache.commons.io.FileUtils;

/*
 * Base page is parent class of all page classes
 * contains all static general methods
 * reading property file + driver repo 
 */
public class BasePageClass {

	WebDriver driver = null;
	PropertyFileReader propertiesReader = null;
	Map<String, String> propertyMap;
	String driverToRun = null;
	Logger log = Logger.getLogger(BasePageClass.class);
	ScreenRecorder screenRecorder;

	// singleton implemented
	private static BasePageClass instance = null;

	/**
	 * log4j need to be initialized once
	 */
	public static BasePageClass getInstance() throws MalformedURLException {
		PropertyConfigurator.configure("log4j.properties");

		// if (instance == null) {
		instance = new BasePageClass();
		// }
		return instance;
	}

	/**
	 * This method will read properties from xyz.properties file and return a
	 * HashMap object with key, values pair
	 */
	public Map<String, String> getProperties() {
		if (propertiesReader == null) {
			propertiesReader = new PropertyFileReader();
		}
		propertyMap = propertiesReader.getPropertyMap();
		log.info("fetched all properties");
		return propertyMap;
	}

	/**
	 * driver can be picked from Thread local or from Enum(w/o thread local
	 * implementation)
	 */
	public WebDriver getDriver() {

		String driverToRun = getProperties().get("driver");

		if (driverToRun.equals("firefox")) {
			// driver = DriverRepo.FIREFOX.getDriver();
			ThreadLocalDriver.setWebDriver(DriverFactory.createInstance("firefox"));
			driver = ThreadLocalDriver.getDriver();

		} else if (driverToRun.equals("chrome")) {
			// driver = DriverRepo.CHROME.getDriver();
			ThreadLocalDriver.setWebDriver(DriverFactory.createInstance("chrome"));
			driver = ThreadLocalDriver.getDriver();

		} else {
			// driver = DriverRepo.FIREFOX.getDriver();
			ThreadLocalDriver.setWebDriver(DriverFactory.createInstance("firefox"));
			driver = ThreadLocalDriver.getDriver();
		}

		log.debug("driver has been initialized as:  " + driver);
		return driver;
	}

	public void quit() {
		log.info("quit application");
		driver.quit();
	}

	public void close() {
		log.info("close application");
		driver.close();
	}

	public void get(String url) {
		log.info("open url: " + url);
		driver.get(url);
	}

	/**
	 * Need to pass driver, as null pointer coming in case we don't pass driver
	 * from TestListeners class
	 */
	public void getScreenshot(WebDriver driver, String testclass, String testname) throws IOException {

		log.info("taking screenshot for failed test case: " + testclass + "_" + testname);
		String filePath = "./src/test/resources/screenshots/";
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		String path = filePath + testclass + "_" + testname + "_" + timestamp;

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(path + ".png"));
			log.info("screenshot captured at: " + path + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Wait<WebDriver> setupWait(WebDriver driver, Integer timeout) {
		return new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}

	public void sendKeys(WebElement element, String keyword) {
		element.clear();
		element.sendKeys(keyword);
	}

	public void click(WebElement element) {
		element.click();
	}

	public boolean isPageLoaded(WebElement element) {
		return element.isDisplayed();
	}

	public boolean isElementPresent(WebElement element) {
		return element.isDisplayed();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * method will start the screen recording
	 */
	public void startrecording() throws IOException, AWTException {
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		screenRecorder = new ScreenRecorder(gc, gc.getBounds(),
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, new File(System.getProperty("user.dir") + "/src/test/resources/videos/"));
		
		screenRecorder.start();
		
	}

	/**
	 * method will stop the screen recording
	 */
	public void stoprecording() throws IOException {
		screenRecorder.stop();
	}

}
