package com.pages;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

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

	// singleton implemented
	private static BasePageClass instance = null;

	/**
	 * log4j need to be initialized once
	 */
	public static BasePageClass getInstance() throws MalformedURLException {
		PropertyConfigurator.configure("log4j.properties");

		//if (instance == null) {
			instance = new BasePageClass();
		//}
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
	 * driver can be picked from Thread local or from Enum(w/o thread local implementation)
	 */
	public WebDriver getDriver() {

		String driverToRun = getProperties().get("driver");

		if (driverToRun.equals("firefox")) {
			//driver = DriverRepo.FIREFOX.getDriver();
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

	public void getScreenshot(WebDriver driver,String testclass, String testname) throws IOException {

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

}
