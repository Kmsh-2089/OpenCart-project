package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myaccountpage extends BasePage
{
		public myaccountpage(WebDriver driver) 
		{
			super(driver);
		}
	
		
	@FindBy(xpath="//h2[contains(text(),'My Account')]")private WebElement msgheading;
	@FindBy(xpath="(//a[@class='list-group-item'][normalize-space()='Logout'])[1]")private WebElement lknlogout;
	
	public boolean ismyaccountexist() 
	{
		try
		{
			return(msgheading.isDisplayed());
		}
		catch(Exception e) 
		{
			return false;
		}
	}
	public void clicklogout() 
	{
		lknlogout.click();
	}
	
	
}


