package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationpage;
import pageobjects.HomePage;
import testBase.BaseClass;

public class tc001_AccountRegistrationTest extends BaseClass
{	
	@Test(groups={"Sanity","Master"})
	public void verify_account_registrartion() 
	{
		logger.info("*** Starting tc001_AccountRegistrationTest***");
		try 
		{
		HomePage hp= new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("cliked on my account");
		hp.clickRegister();
		logger.info("cliked on my register");
		AccountRegistrationpage Rp= new AccountRegistrationpage(driver);
		logger.info("providing customer information");
		
		Rp.Setfirstname(randomstring().toUpperCase());
		Rp.Setlasttname(randomstring().toUpperCase());
		Rp.Setemail(randomstring()+"@gmail.com");
		Rp.Settelephone(randomnumber());
		
		// for passing same password
		String text=randomAphanumric(); 
		
		Rp.Setpassword(text);
		Rp.Setconfirmpassword(text);
		Rp.clickCheckPolicy();
		Rp.clickcontinue();
	logger.info("valiadating the massage");
		String conmsg=Rp.getconfirmationMsg();
		Assert.assertEquals(conmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("test failed");
		}
		logger.info("*** Finished tc001_AccountRegistrationTest***");
		
	}

}
