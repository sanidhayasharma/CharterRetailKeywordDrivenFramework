package com.org.charter.rough;

import java.io.FileNotFoundException;

import com.org.charter.base.base;
import com.org.charter.utilities.ExcelReader;

public class ExcelWriter extends base
{
	ExcelReader reader;
	boolean b;
	public void writeToExcel() throws FileNotFoundException
	{
		reader= new ExcelReader("E:\\SeleniumStuff\\Selenium Workspace Neon\\CharterRetailKeywordFramework\\src\\test\\java\\com\\org\\charter\\repository\\CharterRetailTestData1.xlsx");
	    //b=reader.setCellValue("TestSuite", 2, 3, "Pass");
		//reader.setCellValueString("Submit_HSD_Order_002","click_Button",6, "PASS");
	    System.out.println(b);
	}
	
	public static void main(String args[]) throws FileNotFoundException
	{
		ExcelWriter ex= new ExcelWriter();
		ex.writeToExcel();
	}

}
