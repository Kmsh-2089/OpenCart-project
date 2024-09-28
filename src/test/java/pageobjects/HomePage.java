package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
@FindBy(xpath="//span[text()='My Account']") private WebElement lnkMyAccount;
	
@FindBy(xpath="(//a[text()='Register'])[1]") private WebElement lnkRegister;	

@FindBy(xpath="(//a[text()='Login'])[1]") private WebElement lnklogin;

	public void ClickMyAccount() 
	{
		lnkMyAccount.click();
	}
	public void clickRegister() 
	{
		lnkRegister.click();
	}
	public void clicklogin() 
	{
		lnklogin.click();
	}
	
}
