package com.selenium.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.helper.SeleniumCommands;
import com.selenium.pages.HomePage;
import com.utils.PropertyFileReader;

public class SmokeTestSuite extends SeleniumCommands {

	private WebDriver driver;
	private String PROPERTY_FILE_NAME = "config.properties";
	PropertyFileReader reader;

	@BeforeMethod
	public void launchapp() throws IOException {

		reader = new PropertyFileReader(PROPERTY_FILE_NAME);
		String browser = reader.getPropertyValue("BROWSER");

		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println(" Executing on FireFox");

			File f = new File("libs" + File.separator + "geckodriver");
			if (System.getProperty("os.name").startsWith("Mac")) {
				Runtime.getRuntime().exec("chmod 777 " + f.getAbsolutePath());
			}
			System.setProperty("webdriver.gecko.driver", f.getAbsolutePath());
			setDriver(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			if (System.getProperty("os.name").startsWith("windows")) {
				File f = new File("libs" + File.separator + "chromedriver_win.exe");
				System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
			} else {
				File f = new File("libs" + File.separator + "chromedriver");
				Runtime.getRuntime().exec("chmod 777 " + f.getAbsolutePath());
				System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
			}
			setDriver(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("iexplore")) {
			System.out.println("Executing on IE");
			File f = new File("libs" + File.separator + "IEDriverServer(x86).exe");
			System.setProperty("webdriver.ie.driver", f.getAbsolutePath());
			setDriver(new InternetExplorerDriver());
		} else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void calculatepercent() {
		open("http://newtours.demoaut.com/");
		type(HomePage.tf_userName, "username");
		type(HomePage.tf_password, "password");
		click(HomePage.btn_signIn);
		

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

}
