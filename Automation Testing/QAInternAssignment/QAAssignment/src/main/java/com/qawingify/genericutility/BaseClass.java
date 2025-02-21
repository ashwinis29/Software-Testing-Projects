package com.qawingify.genericutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.qawingify.pomrepository.LoginPage;

public class BaseClass {
	
	public WebDriver driver;
	public WebDriverUtility webdriverUtils = new WebDriverUtility();
	public JavaUtility javaUtils = new JavaUtility();
	protected ExtentReports extent;
    protected ExtentTest test;
	
	@BeforeSuite
	public void setupExtent() 
	{
	    extent = ExtentReportUtility.setupReport();
	}
	
	@BeforeMethod
	public void configBeforeMethod(Method method) throws Throwable, Throwable
	{
		//Makes sure that extent is not null before creating test
		if (extent == null) 
		{
            extent = ExtentReportUtility.setupReport();
        }
		
		//Create test
		test = extent.createTest(method.getName());
		//Browser initialization
		String browserName = javaUtils.propertyData("browser");
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		webdriverUtils.implicitWait(driver);
		//100% abstraction
		driver.get(javaUtils.propertyData("url"));
	}
	
	@AfterMethod
	public void configAfterMethod(ITestResult result)
	{
		if (driver != null) {
	        try {
	            //Perform any necessary cleanup actions (e.g., minimize window) if the session is active
	            driver.manage().window().minimize();
	            if (result.getStatus() == ITestResult.FAILURE) {
	                String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());
	                test.fail("Test Failed: " + result.getThrowable().getMessage(),
	                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	            } else if (result.getStatus() == ITestResult.SUCCESS) {
	                test.pass("Test Passed.");
	            } else if (result.getStatus() == ITestResult.SKIP) {
	                test.skip("Test Skipped: " + result.getThrowable().getMessage());
	            }
	        } catch (Exception e) {
	            //Handle if the driver session is already closed
	            System.out.println("WebDriver session already closed: " + e.getMessage());
	        } finally {
	            // Always quit the driver at the end of the cleanup
	            driver.quit();
	        }
	    }
	}
	
	@AfterSuite
    public void tearDownExtent() {
		if (extent != null) {
            extent.flush();
        }
    }
	
	public void verifyLogin(String un, String pwd) throws Throwable, Throwable
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getUsernameTextField().sendKeys(un);
		loginPage.getPasswordTextField().sendKeys(pwd);
		loginPage.getLoginButton().click();
	}
	
	@DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Throwable, Throwable {
        
        //Read data from csv
        Reader reader = new FileReader("src/test/resources/data.csv");
		CSVReader csvReader = new CSVReader(reader);
		List<String[]> csvDataList = csvReader.readAll();
		
        //Prepare a 2D array to store the data for the DataProvider
        Object[][] data = new Object[csvDataList.size()-1][2];

        //Iterate through the pairs and fill the data array
        for (int i = 1; i < csvDataList.size(); i++) 
        {
            String[] row = csvDataList.get(i);
            data[i-1][0] = row[0];  // username
            data[i-1][1] = row[1];  // password
        }
        
        csvReader.close();
        reader.close();
        return data;
    }
}
