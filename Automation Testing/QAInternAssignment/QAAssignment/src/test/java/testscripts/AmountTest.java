package testscripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qawingify.genericutility.BaseClass;
import com.qawingify.pomrepository.AmountPage;

public class AmountTest extends BaseClass
{
	
	@Test(description = "Test Case 01: Verify amount column is sorted successfully")
    public void verifyAmountSorting() throws Throwable, Throwable 
	{
		verifyLogin(javaUtils.propertyData("username"), javaUtils.propertyData("password"));
		AmountPage amountPage = new AmountPage(driver);
		amountPage.getAmountHeader().click();
		
		List<WebElement> amountElements = amountPage.getAmountsInUSD();
		List<Double> actualAmounts = new ArrayList<>();
		for (WebElement element : amountElements) 
		{
	        String amountText = element.getText().replace("USD", "").replace(",", "").replaceAll("\\s", "").trim();
	        System.out.println(amountText);
	        actualAmounts.add(Double.parseDouble(amountText));
	    }

	    //Create a sorted copy of the list for comparison
	    List<Double> expectedAmounts = new ArrayList<>(actualAmounts);
	    Collections.sort(expectedAmounts);

	    //Validate if the list is sorted correctly
	    Assert.assertEquals(actualAmounts, expectedAmounts, "Amount column is not sorted correctly");

        //Validate if home page loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Home Page not loaded successfully");
        test.pass("Amount Column Sorted Successfully");
    }
}
