package com.sample.mobile.testcases;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.sample.mobile.pages.WelcomePage;
import com.sample.mobile.util.AppiumHelpers;
import com.sample.mobile.util.Config;
import com.sample.mobile.util.IConfig;
import com.sample.mobile.util.ServiceHandler;

import io.appium.java_client.AppiumDriver;

/**
 * @author maheswaran.palanisamy
 *
 */
public class LoginTest {
	AppiumDriver driver;
	
	/// Create the objects which are required for login test
	IConfig config = new Config();
	AppiumHelpers ah = new AppiumHelpers();
	ServiceHandler sh = new ServiceHandler();
	WelcomePage wp = new WelcomePage();
	static Logger log = Logger.getLogger(Config.class);
	
	@BeforeClass
	@Parameters({"DRIVER_NAME"})
	public void setUp(String driver_name){
		log.info("Started initiating driver");
		driver = config.setUp(driver_name);
		System.out.println("driver launched");
	}
	
	@AfterClass
	public void tearDown(){
		config.tearDown();
	}
	
	@Test
	public void verifyWelcomeScreen(){
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(driver.getPageSource());
		Assert.assertTrue(driver.getPageSource().contains("Hello and welcome"), "Hello and welcome text is not displayed");
	}
	
	@Test()
	public void verifyNoNetworkBanner(){
		///Write code to disable the network
        config.wifi("disable");
        Assert.assertTrue(driver.getPageSource().contains("NO CONNECTION"), "No Network banner TEXT is not displayed");
	}
	
	@Test@Parameters({"ENVIRONMENT","USERNAME","PASSWORD"})
	public void verifyLogin(String env, String username, String password) throws ParseException, IOException, JSONException{
		WebDriverWait wdwait = new WebDriverWait(driver, 30);

		wdwait.until(ExpectedConditions.visibilityOfElementLocated(wp.getLoginbutton()));
		driver.findElement(wp.getLoginbutton()).click();
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(wp.getEmailaddress()));
		driver.findElement(wp.getEmailaddress()).click();
		driver.findElement(wp.getEmailaddress()).sendKeys("abc@gmail.com");
		driver.findElement(wp.getPassword()).sendKeys("Password");
		driver.findElement(wp.getSigninbutton()).click();
		ah.verifyTextPresent("Welcom", driver);
		
	}
}
