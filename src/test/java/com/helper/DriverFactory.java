package com.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

	public static WebDriver createInstance(String browserName) {
		WebDriver driver = null;
		if (browserName.toLowerCase().contains("firefox")) {
			
			String current_os = System.getProperty("os.name");
			// log.info("Current OS: " + current_os);
			if (current_os.equals("Mac OS X")) {
				// log.info("MAC: FF initialized");
				String path = System.getProperty("user.dir");
				System.setProperty("webdriver.gecko.driver", path + "/src/test/resources/drivers/geckodriver");
			} else if (current_os.equals("Windows")) {
				// log.info("WINDOW: FF initialized");
				//System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
			} else {
				// log.info("Unknown OS Exception");
			}
			return new FirefoxDriver();
		}

		return driver;

	}
}
