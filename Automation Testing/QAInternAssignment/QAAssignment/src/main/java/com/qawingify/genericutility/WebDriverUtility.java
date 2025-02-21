package com.qawingify.genericutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	/**
	 * To supply Implicit wait for every invocation of findElement() or findElements()
	 * @author Ashwini Saravannavar
	 * @param driver
	 */
	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait until the element is Clickable on the WebPage
	 * @author Ashwini Saravannavar
	 * @param driver
	 * @param targetElement
	 */
	public void waitTillEleIsClickable(WebDriver driver, WebElement targetElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(targetElement));
	}
	
	/**
	 * This method will wait until the title contains the text from the webelement
	 * @author Ashwini Saravannavar
	 * @param driver
	 * @param targetElement
	 */
	public void waitTillTitleContains(WebDriver driver, String title)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.titleContains(title));
	}
}
