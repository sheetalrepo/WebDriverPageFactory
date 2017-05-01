package com.pages;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightStatusPage extends BasePageClass {

	
	/**
	 * Page elements
	 */
	
	WebDriver driver;
	
	@FindBy (id="FlifoSearchInputSearchView_originStation")
	static WebElement departureCity;
	
	@FindBy (id = "FlifoSearchInputSearchView_destinationStation")
	static WebElement arrivalCity; 
	
	//@FindBy (css = ".promotional-banner-cont>a")
	@FindBy (xpath = ".//*[@id='secondMain']/div/a")
	static List<WebElement> listPromotionalBanners; 
	
	
	
	//default constructor
	public FlightStatusPage() {
		//super();
	}


	public FlightStatusPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	/**
	 * Actions methods
	 */
	
	
	
	/**
	 * Getters
	 */
	public int getPromotionalBannersCount(){
		return listPromotionalBanners.size();
	}
	
	
	/**
	 * Setters
	 */
	public void setDepartureCity(int departCity){
		Select dropdown= new Select(departureCity);
		dropdown.selectByIndex(departCity);
	}
	
	public void setArrivalCity(int arvlCity){
		Select dropdown= new Select(arrivalCity);
		dropdown.selectByIndex(arvlCity);
	}
	
	
	/**
	 * Verification methods
	 */
	public boolean isFlightStatusPageLoaded(){
		return isElementPresent(departureCity);
	}
	
	
	
}



