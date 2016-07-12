package com.example.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightDetailsPage {

	@FindBy(how = How.NAME, using = "tripType")
	public WebElement tripTypeRadioButton;
	
	@FindBy(how = How.NAME, using = "fromPort" )
	public WebElement fromPortComboBox;
	
	@FindBy(how = How.NAME, using = "fromDay")
	public WebElement fromDayComboBox;
	
	@FindBy(how = How.NAME, using = "toPort")
	public WebElement toPortComboBox;
	
	@FindBy(how = How.NAME, using = "toDay")
	public WebElement toDayComboBox;
	
	@FindBy(how = How.NAME, using = "airline")
	public WebElement airlineComboBox;
	
	@FindBy(how = How.NAME, using = "findFlights")
	public WebElement submitButton;
	
	public FlightDetailsSelection fillDetailsPage(WebDriver driver,String fromPort, String fromDay,String toPort,String toDay,
			String airline){ 
		this.tripTypeRadioButton.click();
		new Select(fromPortComboBox).selectByVisibleText(fromPort);
		new Select(fromDayComboBox).selectByVisibleText(fromDay);
		new Select(toPortComboBox).selectByVisibleText(toPort);
		new Select(toDayComboBox).selectByVisibleText(toDay);
		new Select(airlineComboBox).selectByVisibleText(airline);
		this.submitButton.click();
		
		
		return PageFactory.initElements(driver, FlightDetailsSelection.class);
	}
	
	
	
}
