package com.example.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

	@FindBy(how = How.NAME, using = "userName")
	public WebElement userName;
	
	
	@FindBy(how = How.NAME, using = "password")
	public WebElement password;
	
	@FindBy(how = How.NAME, using = "login")
	public WebElement submitButton;
	
	
	
	public FlightDetailsPage loginToApplication(WebDriver driver, String userName, String Password){
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear();
		this.password.sendKeys(Password);
		this.submitButton.click();
		
		return PageFactory.initElements(driver, FlightDetailsPage.class);
		
	}
	
	
	
}
