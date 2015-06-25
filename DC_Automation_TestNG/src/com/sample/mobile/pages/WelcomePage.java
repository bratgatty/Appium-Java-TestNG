package com.sample.mobile.pages;

import org.openqa.selenium.By;

/**
 * @author maheswaran.palanisamy
 *
 */
public class WelcomePage {

	By signInButton = By.id("btn_sign_in");
	By loginbutton = By.id("loginbutton");
	By emailaddress = By.id("emailaddress");
	By password = By.id("password");
	By signinbutton = By.id("signinbutton");
	
	/**
	 * @return the signInButton
	 */
	public By getSignInButton() {
		return signInButton;
	}
	/**
	 * @return the loginbutton
	 */
	public By getLoginbutton() {
		return loginbutton;
	}
	/**
	 * @return the emailaddress
	 */
	public By getEmailaddress() {
		return emailaddress;
	}
	/**
	 * @return the password
	 */
	public By getPassword() {
		return password;
	}
	/**
	 * @return the signinbutton
	 */
	public By getSigninbutton() {
		return signinbutton;
	}

}
