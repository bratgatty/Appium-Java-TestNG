package com.sample.mobile.util;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AppiumHelpers {

	WebDriverWait wdwait = null;

	public void clickOnID(By id, AppiumDriver driver) {
		driver.findElement(id).click();

	}

	public void clickOnXpath(By xpath, AppiumDriver driver) {
		driver.findElement(xpath).click();

	}

	public void clickOnClassname(By classname, AppiumDriver driver) {
		driver.findElement(classname).click();

	}

	public void enterTextById(By id, String text, AppiumDriver driver) {
		driver.findElement(id).sendKeys(text);

	}

	public void enterTextByXpath(By xpath, String text, AppiumDriver driver) {
		driver.findElement(xpath).sendKeys(text);

	}

	public void enterTextByClassname(By classname, String text,
			AppiumDriver driver) {
		driver.findElement(classname).sendKeys(text);
		;

	}

	public void verifyActualExpected(By actual, String expected,
			AppiumDriver driver) {
		Assert.assertEquals(actual, expected);

	}

	public void waitForVisibilityOfElementBy(AppiumDriver driver, By locator) {
		wdwait = new WebDriverWait(driver, 30);
		wdwait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(locator));
	}

	public void verifyActualExpected(String[] actual, String[] expected) {
		if (actual.length <= expected.length) {
			for (int i = 0; i < actual.length; i++) {
				Assert.assertEquals(actual[i], expected[i]);
			}
		} else if (expected.length <= actual.length) {
			for (int i = 0; i < expected.length; i++) {
				Assert.assertEquals(actual[i], expected[i]);
			}
		}

	}

	public void verifyTextPresent(String text, AppiumDriver driver) {
		Assert.assertTrue(driver.getPageSource().contains(text), "Expected "
				+ text + " is not present");
	}
	
	public void verifyTextNotPresent(String text, AppiumDriver driver) {
		Assert.assertFalse(driver.getPageSource().contains(text), "Expected "
				+ text + " is present");
	}
	
	
	public void verifyElementPresentById(By id, AppiumDriver driver) {
		Assert.assertTrue(driver.findElement(id).isDisplayed(), "Expected "
				+ id + " is not present");
	}
	
	//Need to write functions for scrolldown, scrollup
	public void scrollUntilVisibilityOf(String visibilityText, AppiumDriver driver) {
		driver.scrollTo(visibilityText);
	}
	
	public void scrollExactVisibilityOf(String visibilityText, AppiumDriver driver) {
		driver.scrollToExact(visibilityText);
	}

}
