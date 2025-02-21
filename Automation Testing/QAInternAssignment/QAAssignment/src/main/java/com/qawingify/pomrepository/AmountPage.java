package com.qawingify.pomrepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmountPage {
	
	WebDriver driver;
	
	public AmountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "amount")
	private WebElement amountHeader;
	
	@FindBy(xpath = "//td[contains(.,'USD')]")
	private List<WebElement> amountsInUSD;

	public WebElement getAmountHeader() {
		return amountHeader;
	}

	public List<WebElement> getAmountsInUSD() {
		return amountsInUSD;
	}
	
	
}
