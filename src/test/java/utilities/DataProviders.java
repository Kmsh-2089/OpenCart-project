package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	// Dataprovider1
	@DataProvider(name="loginData")
	public String[][] getdata() throws IOException 
	{
		String path=System.getProperty("user.dir")+"\\testData\\data.xlsx";// getting the path of excel file 
		
		exelutility xutils=new exelutility(path);// creating object 
		
		 int totalrows=xutils.getrowcount("sheet1");// total row 
		 int totalcells=xutils.getcellcount("sheet1", 1);// total cell 
		 
		 String[][] logindata= new String[totalrows][totalcells] ;
		 for(int i=1;i<=totalrows;i++) // for rows 
		 {
			 for(int j=0;j<totalcells;j++) // for columns 
			 {
				 logindata[i-1][j]=xutils.getcelldata("sheet1", i, j);
			 }
		 } 
		return logindata;// return two dimension array 
		
		
	}
	
	
	
	
	
}
