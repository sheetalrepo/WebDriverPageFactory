package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import helper.DriverRepo;
import helper.PropertyFileReader;

/*
 * Base page is parent class of all page classes
 * contains all static general methods
 * reading property file + driver repo 
 */
public class BasePage {

	WebDriver driver = null;
	PropertyFileReader propertiesReader = null;
	Map<String, String> propertyMap;
	String driverToRun = null;
	//Logger log = Logger.getLogger("BasePage");
	Logger log = Logger.getLogger(getClass());
	
	
	// singleton implemented
	private static BasePage instance = null;

	public static BasePage getInstance() throws MalformedURLException {

		PropertyConfigurator.configure("log4j.properties");

		if (instance == null) {
			instance = new BasePage();

		}
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
	 * This method will 1. read driver value from command line and properties
	 * file 2. initializes driver
	 * 
	 * todo: in case we use quit() in test cases then current running driver
	 * context get removed + driver instance not set to null, so in that case if
	 * loop not get executed and test failed
	 * 
	 * thread local can be used
	 */
	public WebDriver getDriver() {

		// if(driverToRun == null){
		log.info("Driver is null at this point of time");
		String driverToRun = getProperties().get("driver");

		if (driverToRun.equals("firefox")) {
			log.info("Running with firefox driver");
			driver = DriverRepo.FIREFOX.getDriver();
		} else if (driverToRun.equals("chrome")) {
			log.info("Running with chrome driver");
			driver = DriverRepo.CHROME.getDriver();
		} else {
			log.info("Running with default driver");
			driver = DriverRepo.FIREFOX.getDriver();
		}
		// }
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

	/*
	 * todo : incomplete method This method will accept test class and method
	 * name to make readable image name + take screen shot and place in
	 * specified folder
	 */
	public static void getScreenshot(String testclass, String testname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
		String dir;

		// todo: set relative path
		dir = "/Users/sheetalsingh/Documents/workspace/SpiceAuto/src/test/resources/screenshots/";

		String path = dir + testclass + "_" + testname + "_" + timestamp;
		System.out.println("path:" + path);

		// File scrFile = ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scrFile, new File(path));
	}

	// todo - need to check how to use in framwwork
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
