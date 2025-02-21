package testscripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qawingify.genericutility.BaseClass;
import com.qawingify.pomrepository.LoginPage;

public class LoginTest extends BaseClass{
	
	@Test(description = "Test Case 01: Verify login page is loaded successfully")
    public void verifyLoginPage() throws Throwable, Throwable 
	{
        //Validate if login page loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login Page not loaded successfully");
        System.out.println("Login page loaded successfully");
    }
	
	@Test(description = "Test Case 02: Verify user is able to login with valid credentials")
    public void verifyLoginWithValidCredentials() throws Throwable, Throwable 
	{
		//Login with valid credentials
        verifyLogin(javaUtils.propertyData("username"),javaUtils.propertyData("password"));
        //Validate if logged in successfully
        String expectedURL = "https://sakshingp.github.io/assignment/home.html"; 
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "Login was not successful!");
    }
	
	@Test(description = "Test Case 03: Verify user is able to login with any username and password credentials", dataProvider = "loginData")
    public void verifyLoginWithAnyUnAndPwd(String username, String password) throws Throwable, Throwable 
	{
		//Login with given credentials
        verifyLogin(username, password);
        //Validate if logged in successfully
        String expectedURL = "https://sakshingp.github.io/assignment/home.html"; 
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "Login was not successful!");
    }
	
	@Test(description = "Test Case 04: Verify user is unable to login with empty credentials")
    public void verifyLoginWithEmptyCredentials() throws Throwable
	{
		LoginPage loginPage = new LoginPage(driver);
		verifyLogin("","");
        
        //Validate if able to login with empty credentials
        Assert.assertTrue(loginPage.getUsernamePwdError().isDisplayed(), "Error message not displayed.");
    }
	
	@Test(description = "Test Case 05: Verify user is unable to login with empty username")
    public void verifyLoginWithEmptyUsername() throws Throwable, Throwable 
	{
		verifyLogin("",javaUtils.propertyData("password"));
        
		//Validate if able to login with empty credentials
        Assert.assertTrue(new LoginPage(driver).getUsernamePwdError().isDisplayed(), "Error message not displayed.");
    }
	
	@Test(description = "Test Case 06: Verify user is able to login with empty password")
    public void verifyLoginWithEmptyPassword() throws Throwable, Throwable 
	{
		verifyLogin(javaUtils.propertyData("username"),"");
        
		//Validate if able to login with empty credentials
        Assert.assertTrue(new LoginPage(driver).getUsernamePwdError().isDisplayed(), "Error message not displayed.");
    }
	
	@Test(description = "Test Case 07: Verify remember me feature")
    public void verifyRememberMeFunctionality() throws Throwable, Throwable 
	{	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getRememberMeCheckbox().click();
		// Login with valid credentials
        verifyLogin(javaUtils.propertyData("username"), javaUtils.propertyData("password"));
        
		String expectedURL = "https://sakshingp.github.io/assignment/home.html";
	    Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "Login was not successful with 'Remember Me' enabled!");
	    
	   //Save cookies before quitting
	    Set<Cookie> cookies = driver.manage().getCookies();
	    
	    //Close the browser and re-open again
	    driver.manage().window().minimize();
	    driver.quit();  
	    
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
		webdriverUtils.implicitWait(driver);
		driver.get(javaUtils.propertyData("url"));
	    
		// Restore cookies before refreshing
	    for (Cookie cookie : cookies) {
	        driver.manage().addCookie(cookie);
	    }

	    //Refresh to apply cookies
	    driver.navigate().refresh();
	    
	    //Check if the user is still logged in after reopening the browser
	    //Reopen the browser and check if user is redirected to the home page
	    Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "User is not logged in after reopening the browser with 'Remember Me' enabled");
	    test.pass("Remember Me Functionality Works As Expected");
    }
	
	@Test(description = "Test Case 08: Verify twitter functionality")
    public void verifyTwitterFeature() throws Throwable, Throwable 
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getTwitterIcon().click();
		
		//Validate if redirected to twitter page
        Assert.assertEquals(driver.getCurrentUrl(),"https://x.com/", "Not redirected to twitter page");
    }
	
	@Test(description = "Test Case 09: Verify facebook functionality")
    public void verifyFacebookFeature() throws Throwable, Throwable 
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getFacebookIcon().click();
		
		//Validate if redirected to facebook page
        Assert.assertEquals(driver.getCurrentUrl(),"https://facebook.com/", "Not redirected to facebook page");
    }
	
	@Test(description = "Test Case 10: Verify Linkedkin functionality")
    public void verifyLinkedinFeature() throws Throwable, Throwable 
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getLinkedinIcon().click();
		
		//Validate if redirected to linkedin page
        Assert.assertEquals(driver.getCurrentUrl(),"https://x.com/", "Not redirected to linkedin page");
    }
	
	
}
