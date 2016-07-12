package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC01_Flight_booking_details {
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
    driver.get(baseUrl + "/mercurysignon.php");
    
    
    driver.findElement(By.name("userName")).clear();
    driver.findElement(By.name("userName")).sendKeys("user789");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("user789");
    driver.findElement(By.name("login")).click();
    // login finished
    driver.findElement(By.name("tripType")).click();
    new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("London");
    new Select(driver.findElement(By.name("fromDay"))).selectByVisibleText("20");
    new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("Paris");
    new Select(driver.findElement(By.name("toDay"))).selectByVisibleText("21");
    // Flight details filled
    new Select(driver.findElement(By.name("airline"))).selectByVisibleText("Blue Skies Airlines");
    // added preferences
    driver.findElement(By.name("findFlights")).click();
    driver.findElement(By.xpath("(//input[@name='outFlight'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='inFlight'])[2]")).click();

    driver.findElement(By.name("reserveFlights")).click();
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
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
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
