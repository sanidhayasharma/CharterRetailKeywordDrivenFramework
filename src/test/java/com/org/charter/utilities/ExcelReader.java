package com.org.charter.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader 
{
	private String path;
	private FileInputStream fis;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private FileOutputStream fos;
	public ExcelReader(String path)
	{
		this.path=path;
		try
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("File is not loaded sucessfully");
		}
	}
	
	
	public boolean IsSheetExist(String sheetname)
	{
		int index=workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			index=workbook.getSheetIndex(sheetname.toUpperCase());
			if(index==-1)
			{
				return false;
			}
		}
		return true;
	}
	
	public int GetColumnNumber(String sheetname)
	{
		int index=workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			return -1;
		}
		sheet=workbook.getSheetAt(index);
		row=sheet.getRow(0);
		if(row==null)
		{
			return -1;
		}
		int count=row.getLastCellNum();
		return count;
	}
	
	public int GetRowNumber(String sheetname)
	{
		int index=workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			return -1;
		}
		sheet=workbook.getSheetAt(index);
		int count=sheet.getLastRowNum();
		return count+1;
	}
	
	public String getCellData(String sheetname,int ro,int col)
	{
		String value;
		int index=workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			return null;
		}
		sheet=workbook.getSheetAt(index);
		row=sheet.getRow(ro);
		if(row==null)
		{
			return null;
		}
		cell=row.getCell(col);
		if(cell==null)
		{
			return null;
		}
		if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			System.out.println("Inside Numeric Cell");
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		value=cell.getStringCellValue();
		return value;
	}
	
	public boolean setCellValue(String sheetname,int ro, int col,String data) throws FileNotFoundException
	{
		int index=workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			return false;
		}
	    sheet=workbook.getSheetAt(index);
	    if(sheet== null)
	    {
	    	return false;
	    }
	    row=sheet.getRow(ro);
	    if(row==null)
	    {
	    	row=sheet.createRow(ro);
	    }
	    cell=row.getCell(col);
	    if(cell == null)
	    {
	        cell=row.createCell(col);    
	    }
		cell.setCellValue(data);
		try
		{
		fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
		}
		catch(Exception e)
		{
			System.out.println("Data is not writing back into the Spreadsheet");
			e.printStackTrace();
			return false;
		}
		return true;
		}
	
	/*public boolean setCellValueString(String sheetname,String action_keyword, int col,String data) throws FileNotFoundException
	{
		int index=workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			return false;
		}
	    sheet=workbook.getSheetAt(index);
	    if(sheet== null)
	    {
	    	return false;
	    }
	   
	    for(int i=0;i<sheet.getLastRowNum();i++)
	    {
	    	if(sheet.getRow(i).getCell(4).getStringCellValue().equalsIgnoreCase(action_keyword))
	    	{
	    		row=sheet.getRow(i);
	    		break;
	    	}
	    }
	    cell=row.getCell(col);
	    if(cell == null)
	    {
	        cell=row.createCell(col);    
	    }
		cell.setCellValue(data);
		try
		{
		fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
		}
		catch(Exception e)
		{
			System.out.println("Data is not writing back into the Spreadsheet");
			e.printStackTrace();
			return false;
		}
		return true;
		}*/
		
}

