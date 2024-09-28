package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class Extents_report_manager implements ITestListener
{
		public ExtentSparkReporter sparkrepoter;// Handle UI Of the report 
		public ExtentReports extent;// handle the populate common info in the report 
		public ExtentTest test;//creating test case entry and update status of the test in the reports 
		String repName;
		public void onStart(ITestContext testcontext) 
		{
			// TO ADD TIMESTAMP  to the report along with the date 
			/* SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt= new Date();
			String currentdatetimestamp=df.format(dt); */
			
			String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp in single line
			repName="Test-Report-"+repName+".html";
			sparkrepoter = new ExtentSparkReporter(".\\reports\\"+repName);// need to pass location of the folder
			sparkrepoter.config().setDocumentTitle("opencart Automation Report");//title of the report
			sparkrepoter.config().setReportName("opencart Fuctional Testing");// name of the report
			sparkrepoter.config().setTheme(Theme.STANDARD);
			
			extent= new ExtentReports();
			extent.attachReporter(sparkrepoter);
			extent.setSystemInfo("Application", "opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("sub module", "Customer");
			extent.setSystemInfo("user name",System.getProperty("user.name"));
			extent.setSystemInfo("Enviorment", "QA");
			
			String os=testcontext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("operating system", "os");
			String browser=testcontext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", "browser");
			List<String>includegroups=testcontext.getCurrentXmlTest().getIncludedGroups();
			if(!includegroups.isEmpty()) 
			{
				extent.setSystemInfo("Groups",includegroups.toString());
			}	
		}
		public void onTestSuccess(ITestResult result) 
		{
			test=extent.createTest(result.getTestClass().getName());//create new entry in the report
			test.assignDevice(result.getMethod().getGroups());// for getting the groups
			test.log(Status.PASS,result.getName()+"GOT SUCCESSFULLY EXECUTATED");// update the status pass/fails/skipped	   
		}
		public void onTestFailure(ITestResult result) 
		{
			test=extent.createTest(result.getTestClass().getName());
			test.assignDevice(result.getMethod().getGroups());
			test.log(Status.FAIL,result.getName()+"got failed");
			test.log(Status.FAIL,result.getThrowable().getMessage());
			
			try {
				String imgpath=new BaseClass().capturescreen(result.getName());
				test.addScreenCaptureFromPath(imgpath);
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
	    }
		public void onTestSkipped(ITestResult result) 
		{
			test=extent.createTest(result.getTestClass().getName());
			test.assignDevice(result.getMethod().getGroups());
			test.log(Status.SKIP,result.getName()+"Got Skipped");
			test.log(Status.SKIP,result.getThrowable().getMessage());
		}
		public void onFinish(ITestContext context) 
		{
			extent.flush(); 	
			String pathofExtentreport=System.getProperty("user.dir")+"\\reports\\"+repName;
			File  extentReport = new File(pathofExtentreport);
			try{
				Desktop.getDesktop().browse(extentReport.toURI());
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
			
			/*try {
				URL url= new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
				// ceate the email  massage
				ImageHtmlEmail email= new ImageHtmlEmail();
				email.setDataSourceResolver(new DataSourceUrlResolver(url));
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("vaidyakamesh@gmail.com","Rushu@2089"));
				email.setSSL(true);
				email.setFrom("vaidyakamesh@gmail.com");// sender
				email.setSubject("Test Results");
				email.setMsg("please find attach report");
				email.addTo("awchatnayan@gmail.com");// receiver
				email.attach(url, "extent Report", "please check the report...");
				email.send();	
				}
			catch(Exception e)
				{
					e.printStackTrace();
				}*/
			
		}
		
}

