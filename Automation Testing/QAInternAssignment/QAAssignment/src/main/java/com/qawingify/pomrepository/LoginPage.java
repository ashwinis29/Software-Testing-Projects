package com.qawingify.pomrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qawingify.genericutility.JavaUtility;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username")
	private WebElement usernameTextField;
	
	@FindBy(id = "password")
	private WebElement passwordTextField;
	
	@FindBy(id = "log-in")
	private WebElement loginButton;
	
	@FindBy(css = "input[type='checkbox']")
	private WebElement rememberMeCheckbox;
	
	@FindBy(xpath = "//div[@role='alert' and contains(.,'present')]")
	private WebElement usernamePwdError;
	
	@FindBy(xpath = "//img[@src='img/twitter.png']/parent::a")
	private WebElement twitterIcon;
	
	@FindBy(xpath = "//img[@src='img/facebook.png']/parent::a")
	private WebElement facebookIcon;
	
	@FindBy(xpath = "//img[@src='img/linkedin.png']/parent::a")
	private WebElement linkedinIcon;

	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getRememberMeCheckbox() {
		return rememberMeCheckbox;
	}

	public WebElement getUsernamePwdError() {
		return usernamePwdError;
	}

	public WebElement getTwitterIcon() {
		return twitterIcon;
	}

	public WebElement getFacebookIcon() {
		return facebookIcon;
	}

	public WebElement getLinkedinIcon() {
		return linkedinIcon;
	}
	
}
