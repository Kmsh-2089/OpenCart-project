package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.loginpage;
import pageobjects.myaccountpage;
import testBase.BaseClass;

public class tc002_account_login extends BaseClass
{
	@Test(groups={"Regression","Master"})
	public void verify_login() 
	{
		logger.info("***starting tc002_account_login test***");
		// for home page 
		try {
		HomePage hp= new HomePage(driver);
		hp.ClickMyAccount();
		hp.clicklogin();
		
		//for log in page 
		loginpage lp=new loginpage(driver);
		lp.enteremail(p.getProperty("email"));
		lp.enterpasswod(p.getProperty("password"));
		lp.clicklogin();
		
		// for my account page 
		myaccountpage mcp= new myaccountpage(driver);
		boolean target=mcp.ismyaccountexist();
		Assert.assertTrue(target);
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		logger.info("***Finishied tc002_account_login test***");
	}
	
	
	
	
}
