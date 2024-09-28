package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.loginpage;
import pageobjects.myaccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class tc003_loginDDT extends BaseClass
{
	@Test(dataProvider="loginData",dataProviderClass=DataProviders.class,groups="DDT")
	public void verifyloginDDt(String email, String password ,String exp) 
	{
		logger.info("*** STARTING TC003_LOGINddt****");
		try 
		{
		HomePage hp= new HomePage(driver);
		hp.ClickMyAccount();
		hp.clicklogin();
		
		//for log in page 
		loginpage lp=new loginpage(driver);
		lp.enteremail(email);
		lp.enterpasswod(password);
		lp.clicklogin();
		
		// for my account page 
		myaccountpage mcp= new myaccountpage(driver);
		boolean target=mcp.ismyaccountexist();
		
	/*	 now we need to do the validation here 
		 1)if data is valid --login success--test pass---logout
						--login unsuccess---test fail
		2)if data is invalid --login success--test fails---logout
							--login unsuccess---test pass 				*/
		// for 1st case
		if(exp.equalsIgnoreCase("valid")) 
		{
			if(target==true) 
			{
				mcp.clicklogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		// for 2ond case
		if(exp.equalsIgnoreCase("invalid")) 
		{
			if(target==true) 
			{
				mcp.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		logger.info("*** FINISHED TC003_LOGINddt****");
	}
	
	
	
	
	
}
