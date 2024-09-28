package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class exelutility 
{
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook workbook;
	public  XSSFSheet sheet;
	public  XSSFRow row;
	public  XSSFCell cell;
	String path;
	
	public exelutility(String path) 
	{
		this.path=path;
	}
	
	public int getrowcount(String xlsheet ) throws IOException 
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(xlsheet);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	
	}
	public int getcellcount(String xlsheet, int rownum) throws IOException 
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		 int cellcount=row.getLastCellNum();
		 workbook.close();
		fi.close();
		return cellcount;
	}
	public String getcelldata(String xlsheet, int rownum,int cellnum) throws IOException 
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		String data;
		try 
		{
			data=cell.toString();
		//	DataFormatter formatter= new DataFormatter();
		//	data =formatter.formatCellValue(cell);
		}
		catch(Exception e) 
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	
}
