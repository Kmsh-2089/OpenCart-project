package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage extends BasePage
{
		public loginpage(WebDriver driver) 
		{
			super(driver);
		}
	
	@FindBy(xpath="//input[@id='input-email']")private WebElement textemail;
	@FindBy(xpath="//input[@id='input-password']")private WebElement textpassword;
	@FindBy(xpath="//input[@type='submit']")private WebElement btnlogin;
	
	public void enteremail(String email) 
	{
		textemail.sendKeys(email);
	}
	public void enterpasswod(String pass) 
	{
		textpassword.sendKeys(pass);
	}
	public void clicklogin() 
	{
		btnlogin.click();
	}
	
	
}
