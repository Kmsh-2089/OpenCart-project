package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationpage extends BasePage
{
	public AccountRegistrationpage(WebDriver driver) 
	{
		super(driver);
	}

@FindBy(xpath="//input[@id='input-firstname']") private WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']") private WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']") private WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") private WebElement txtTeliphone;
@FindBy(xpath="//input[@id='input-password']") private WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") private WebElement txtConfirmPassword;
@FindBy(xpath="//input[@type='checkbox']") private WebElement CheckPolicy;
@FindBy(xpath="//input[@type='submit']") private WebElement BtnContinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") private WebElement msgconfirm;

public void Setfirstname(String fname) 
{
	txtFirstName.sendKeys(fname);
}
public void Setlasttname(String lname) 
{
	txtLastName.sendKeys(lname);
}
public void Setemail(String email) 
{
	txtEmail.sendKeys(email);
}
public void Settelephone(String tel) 
{
	txtTeliphone.sendKeys(tel);
}
public void Setpassword(String pws) 
{
	txtPassword.sendKeys(pws);
}
public void Setconfirmpassword(String cpws) 
{
	txtConfirmPassword.sendKeys(cpws);
}
public void clickCheckPolicy() 
{
	CheckPolicy.click();
}
public void clickcontinue() 
{
	BtnContinue.click();
}
public String getconfirmationMsg() 
{
	try {
		return(msgconfirm.getText());
	}catch(Exception e) 
	{
		return(e.getMessage());
	}
	
}

}
