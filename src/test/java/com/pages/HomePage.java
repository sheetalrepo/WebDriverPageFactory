package com.pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePageClass {

	
	/**
	 * Page elements
	 */
	
	WebDriver driver;
	
	@CacheLookup
	@FindBy (className="flight_status")
	public static WebElement flightStatusTab;
	
	@FindBy (className = "holiday-packages")
	static WebElement holidayPackagesTab; 

	@FindBy (className = "myspice_trip")
	static WebElement hotelsTab; 

	@FindBy (id = "footer-headings")
	static WebElement footerHeading; 
	
	
	//default constructor
	public HomePage()  {
		//super();
	}


	public HomePage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	

	/**
	 * Actions methods
	 */
	
	public void clickHotelsTab(){
		click(hotelsTab);
	}
	
	
	public void clickFlighStatusTab(){
		click(flightStatusTab);
	}
	
	public void clickHolidayPackagesTab(){
		click(holidayPackagesTab);
	}
	
	/**
	 * Getters
	 */

	
	
	/**
	 * Setters
	 */
	
	
	/**
	 * Verification methods
	 */
	public boolean isHomePageLoaded(){
		return isElementPresent(footerHeading);
	}
	
	
}



