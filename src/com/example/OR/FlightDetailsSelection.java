package com.example.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightDetailsSelection {

	@FindBy(how = How.XPATH, using = "//input[@name='outFlight'])[2]")
	public WebElement departSelection;
	
	
	@FindBy(how = How.XPATH, using = "(//input[@name='inFlight'])[2]")
	public WebElement returnSelection;
	
	@FindBy(how = How.NAME, using = "reserveFlights")
	public WebElement reserveFlightsButton;
	
	
	
	public void doSelectFlights(WebDriver driver){
		this.departSelection.click();
		this.returnSelection.click();
		this.reserveFlightsButton.click();
		
		
	}
	
	
	
}
