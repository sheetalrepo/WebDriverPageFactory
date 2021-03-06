package com.pages;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelsPage extends BasePageClass {

	
	/**
	 * Page elements
	 */
	
	WebDriver driver;
	
	@FindBy (id="MySpiceTripSearchView_TextBoxMarketOrigin1")
	static WebElement destinationCity;
	
	
	//default constructor
	public HotelsPage() {
		//super();
	}


	public HotelsPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	/**
	 * Actions methods
	 */
	
	
	
	/**
	 * Getters
	 */

	
	
	/**
	 * Setters
	 */
	public void setDestinationCity(String city){
		destinationCity.sendKeys("Leh");
	}
	
	
	/**
	 * Verification methods
	 */
	public boolean isHotelsPageLoaded(){
		return isElementPresent(destinationCity);
	}
	
	
	
}



