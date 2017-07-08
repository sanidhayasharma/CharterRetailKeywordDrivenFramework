package com.org.charter.testcases;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.charter.base.base;
import com.org.charter.config.ActionKeywords;
import com.org.charter.config.Constants;
import com.org.charter.utilities.ExcelReader;

public class TestCase_SubmitHSDOnlyOrder extends base
{
private ExcelReader reader;	
private String sTestCaseName;
private String sActionKeyword;
private String sPageObject;
private String sData;
private WebDriver driver;
private Properties properties;
private Class c;
private Method []m;
private ActionKeywords actionKey;
private Logger log;


public TestCase_SubmitHSDOnlyOrder()
{
	//actionKey= new ActionKeywords();
	c=ActionKeywords.class;
	m=c.getMethods();
}
	
@BeforeMethod
public void TestRunMode() throws FileNotFoundException
{
	driver=getDriver();
	properties=getInitializedProperties();
	reader=getExcelReaderInitialized();
	log=getLoggerIntialized();
	for(int i=1;i<reader.GetRowNumber("TestSuite");i++)
	{
		if(reader.getCellData("TestSuite",i,Constants.Col_TCName).equalsIgnoreCase("Submit_HSD_Order_002"))
		{
			if(reader.getCellData("TestSuite",i,Constants.Col_RunMode).equalsIgnoreCase("Y"))
			{
				System.out.println("______________Running Test Case "+reader.getCellData("TestSuite",i,Constants.Col_TestcaseName)+"___________");
			}
			else
			{
				ActionKeywords.skip=true;
				reader.setCellValue("TestSuite",i,Constants.Col_TestSuite_Result,"SKIP"); 
				throw new SkipException("______________Skipped Test Case "+reader.getCellData("TestSuite",i,Constants.Col_TestcaseName)+"___________");
			}
		}
	}
}
	
@Test
public void TestSubmitHSDOrder() throws Exception
{
	
	for(int i=1;i<reader.GetRowNumber("TestSuite");i++)
	{
		sTestCaseName=reader.getCellData("TestSuite",i,Constants.Col_TestcaseName);
		for(int j=1;j<reader.GetRowNumber("Submit_HSD_Order_002");j++)
		{
			if(reader.getCellData("Submit_HSD_Order_002", j,Constants.Col_TestSteps).equalsIgnoreCase(sTestCaseName))
			{
				sActionKeyword=reader.getCellData("Submit_HSD_Order_002",j,Constants.Col_ActionKey);
				sPageObject=reader.getCellData("Submit_HSD_Order_002",j,Constants.Col_PageObject);
				sData=reader.getCellData("Submit_HSD_Order_002", j,Constants.Col_Data);
				System.out.println("____"+sActionKeyword+"____"+sPageObject+"___"+sData);
				executeScript();
				if(ActionKeywords.fail)
	  			{
	  				System.out.println("Inside Fail Test Case Step");
	  				reader.setCellValue("Submit_HSD_Order_002",j,Constants.Col_TestStep_Result,"FAIL");
	  				throw new Exception("Stopping The script....!!!");
	  			}
	  			else if(ActionKeywords.skip)
	  			{
	  				System.out.println("Inside Skip Test Case Step");
	  				reader.setCellValue("Submit_HSD_Order_002",j,Constants.Col_TestStep_Result,"SKIP");
	  			}
	  			else
	  			{
	  				System.out.println("Inside Pass Test Case Step");
	  				reader.setCellValue("Submit_HSD_Order_002",j,Constants.Col_TestStep_Result,"PASS");
	  			}
			}
			
		}
	}
}

public void executeScript() throws Exception
{
	
  	for(int i=0;i<m.length;i++)
  	{
  		if(m[i].getName().equalsIgnoreCase(sActionKeyword))
  		{
  			System.out.println(m[i].getName()+"______"+sActionKeyword+"_____"+sPageObject);
  			m[i].invoke(new ActionKeywords(driver,properties,log),sPageObject,sData);
  			break;
  		}
  		
  	}
	
}

@AfterMethod
public void writeTestCaseStatus() throws Exception
{
	System.out.println("__________________________Inside After Method _____________________");
	if(ActionKeywords.fail)
		{
			for(int i=1;i<reader.GetRowNumber("TestSuite");i++)
			{
				 if(reader.getCellData("TestSuite", i, 0).equalsIgnoreCase("Submit_HSD_Order_002"))
				 {
					 reader.setCellValue("TestSuite",i,Constants.Col_TestSuite_Result,"FAIL"); 
					 throw new Exception("Stopping The script....!!!");
				 }
			}
		}
	else
		{
			for(int i=1;i<reader.GetRowNumber("TestSuite");i++)
			{
				 if(reader.getCellData("TestSuite", i, 0).equalsIgnoreCase("Submit_HSD_Order_002"))
				 {
					 reader.setCellValue("TestSuite",i,Constants.Col_TestSuite_Result,"PASS"); 
				 }
				
			}
		}
}

}
