package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass
{
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups={"Regression","Sanity","Master"})
	@Parameters({"os","browser"})
	public void setup( String os , String br ) throws IOException 
	{
		// loading config properties file 
		
		FileReader file= new FileReader("./src/test/resources/config.properties");
		p= new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities= new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("window")) 
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else 
			{
				System.out.println("no matching browser");
				return;
			}
			//browser
			switch(br.toLowerCase()) 
			{
				case "chrome":capabilities.setBrowserName("chrome");break;
				case "edge": capabilities.setBrowserName("Microsoftedge"); break;
				case "firefox": capabilities.setBrowserName("firefox");  break;
				default:System.out.println("no matching browser");return; 
			} 
			driver= new RemoteWebDriver(new URL("need to pass the grid url/wd/hub"),capabilities);
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) 
			{
				case "chrome":driver= new ChromeDriver();break;
				case "edge": driver= new EdgeDriver();break; 
				case "firefox": driver= new FirefoxDriver();break;
				default:System.out.println("Invalid browser name ...");return; 
			} 
		}
						
	//	driver =new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); // reading url from propertiese file 
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"Regression","Sanity","Master"})
	public void teardown() 
	{
		driver.quit();
	}
	@SuppressWarnings("deprecation")
	public String randomstring() 
	{
		String genataredstring=RandomStringUtils.randomAlphabetic(5);
		return genataredstring;
	}
	 @SuppressWarnings("deprecation")
	public String randomnumber() 
	 {
		String genatarednub=RandomStringUtils.randomAlphanumeric(10);
		return genatarednub;
	 }
	 @SuppressWarnings("deprecation")
	public String randomAphanumric() 
	 {
		String genataredString=RandomStringUtils.randomAlphabetic(3);
		String genatarednub=RandomStringUtils.randomAlphanumeric(10);
		return(genataredString+"@"+genatarednub);
	 }
	public String capturescreen(String tname) throws IOException
	{
		String timestamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesscreenshot= (TakesScreenshot)driver;
		 File sourcefile= takesscreenshot.getScreenshotAs(OutputType.FILE);
		 
		 String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+ tname+"_"+timestamp+".png";
		 File targetfile= new File(targetfilepath);
		 sourcefile.renameTo(targetfile);
		 return targetfilepath;
	}
	
	
	
	
}
