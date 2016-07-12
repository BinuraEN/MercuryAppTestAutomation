package com.example.tests;

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import com.example.OR.FlightDetailsPage;
import com.example.OR.FlightDetailsSelection;
import com.example.OR.LoginPage;


import static org.testng.Assert.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TC01_Flight_booking_details_PageFactory {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://newtours.demoaut.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  
  
  @Test
  public void testTC01FlightBookingDetails() throws Exception {
	
	  
	FileInputStream fis = new FileInputStream(new File("data.properties"));
	Properties props = new Properties();
    props.load(fis);
	
	
    driver.get(baseUrl + "/mercurysignon.php");
    
   // LoginPage loginPage = new LoginPage();
    captureScreeShot("Starting login");
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    captureScreeShot("Strart flight details");
    FlightDetailsPage flightDetailsPage = loginPage.loginToApplication(driver, props.getProperty("LOGIN.USERNAME"), props.getProperty("LOGIN.PASSWORD"));
    
    FlightDetailsSelection flightDetailsSelectionPage=flightDetailsPage.fillDetailsPage(driver,"London", "20", "Paris", "20", "Blue Ses Airlines");
    flightDetailsSelectionPage.doSelectFlights(driver);
    
    // filled the return
    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'Paris to London')]/../../../../tr[3]//b[contains(text(),'Blue Skies Airlines 361')]")).getText(), "Blue Skies Airlines 361","Paris to London flight number is incorrrect");
    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'Paris to London')]/../../../../tr[3]//font[contains(text(),'Coach')]")).getText(), "Coach");
    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'Paris to London')]/../../../../tr[3]//font[contains(text(),'271')]")).getText(), "271");

    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'London to Paris')]/../../..//font[contains(text(),'7/20/2016')]")).getText(), "7/20/2016");
    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'London to Paris')]/../../../../tr[6]//b[contains(text(),'Blue Skies Airlines 631')]")).getText(), "Blue Skies Airlines 631");
    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'London to Paris')]/../../../../tr[6]//font[contains(text(),'Coach')]")).getText(), "Coach");
    assertEquals(driver.findElement(By.xpath("//td/b/font[contains(text(), 'London to Paris')]/../../../../tr[6]//font[contains(text(),'273')]")).getText(), "273");

    
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
   
	  captureScreeShot("Error Screenshot");
    	
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	   driver.quit();
      fail(verificationErrorString);
      
    }
    
    
  }
  
  private void captureScreeShot(String fileName){
	  File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  	try {
		FileUtils.copyFile(screenshotFile, new File(fileName+".png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
