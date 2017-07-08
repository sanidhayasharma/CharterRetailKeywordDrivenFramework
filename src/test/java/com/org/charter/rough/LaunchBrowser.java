package com.org.charter.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.org.charter.base.base;
import com.org.charter.config.Constants;
import com.org.charter.utilities.ExcelReader;


public class LaunchBrowser
{
	private ExcelReader reader;	
	@Test
	public void doTest()
	{
		//System.setProperty("webdriver.gecko.driver", "E:\\SeleniumStuff\\SeleniumImportant1\\GeckoDriver\\15\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		driver.get("https://www.spectrum.com/");
		driver.findElement(By.xpath("//div[@class='react-autosuggest__container']/input")).sendKeys("67542");
		driver.findElement(By.id("apt")).sendKeys("67542");
		driver.findElement(By.id("zip")).sendKeys("67542");
		//e.sendKeys("9798");
		
		
		/*reader=getExcelReaderInitialized();
		for(int i=1;i<reader.GetRowNumber("TestSuite");i++)
		{
				if(reader.getCellData("TestSuite",i,Constants.Col_RunMode).equalsIgnoreCase("Y"))
				{
					System.out.println("______________Running Test Case "+reader.getCellData("TestSuite",i,Constants.Col_TestcaseName)+"___________");
				}
				else
				{
					throw new SkipException("______________Skipped Test Case "+reader.getCellData("TestSuite",i,Constants.Col_TestcaseName)+"___________");
				}
		}*/
		
	}

}
