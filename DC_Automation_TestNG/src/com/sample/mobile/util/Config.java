package com.sample.mobile.util;

import org.testng.Assert;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author maheswaran.palanisamy
 *
 */

public class Config implements IConfig {

	public AppiumDriver driver;
	static Logger log = Logger.getLogger(Config.class);

	// Launching Driver with the desired capabilities
	public AppiumDriver Selectingdriver(@Optional("Android") String driver_name)
			throws MalformedURLException {
		if (driver_name.equals("Android")) {
			File app = new File(System.getProperty("user.dir") + File.separator +"app").listFiles()[0];
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformVersion", "5.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "SM-G900F");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities
					.setCapability("appPackage", "com.sample.mobile");
			capabilities.setCapability("app-wait-activity", "WelcomeActivity");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);
		} else if (driver_name.equals("iOS")) {
			File app = new File(System.getProperty("user.dir") + File.separator +"app").listFiles()[0];
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "iPhone");
			// capabilities.setCapability("browserName", "Safari");
			capabilities.setCapability("udid",
					"de8fc3a7729bcc020cd4136a57c1e84f75f4110a");
			capabilities.setCapability("bundleid", "com.sample.mobile");
			capabilities.setCapability("app", app.getAbsolutePath());
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);

		}
		return driver;
	}

	// Initiate driver capabilities
	public AppiumDriver setUp(String driver_name) {
		try {
			driver = Selectingdriver(driver_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Driver Launched");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	// Closing the driver
	public void tearDown() {

		try {
			driver.quit();
			log.info("Execution successful : Driver Quit");
		} catch (Exception e) {
			log.error("TearDown Failed", e);
			Assert.fail("TearDown Failed", e);
		}
	}

	@Override
	public void wifi(String state) {

		if (!((AndroidDriver) driver).getNetworkConnection().wifiEnabled()
				&& state.equalsIgnoreCase("enable")) {
			NetworkConnectionSetting WifiEnablesetting = new NetworkConnectionSetting(
					false, true, false);
			((AndroidDriver) driver).setNetworkConnection(WifiEnablesetting);

		} else if (state.equalsIgnoreCase("disable")) {
			NetworkConnectionSetting WifiDisablesetting = new NetworkConnectionSetting(
					false, false, false);
			((AndroidDriver) driver).setNetworkConnection(WifiDisablesetting);
		}
	}

}
