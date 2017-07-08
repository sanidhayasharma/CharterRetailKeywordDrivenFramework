package com.org.charter.config;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.org.charter.base.base;

public class ActionKeywords
{
private WebDriver driver;
private Properties properties;
private Logger log;
public static boolean skip=false;
public static boolean fail=false;

public ActionKeywords(WebDriver driver, Properties properties, Logger log)
{
	this.driver=driver;
	this.properties=properties;
	this.log=log;
}

public void input_addressLine_1(String object,String data)
{
	try
	{
		//System.out.println("inside the method===================indresh============="+object);
		//System.out.println("properties.getProperty(object)============"+properties.getProperty("AddressLine1_Xpath"));
		Thread.sleep(1000);
		//System.out.println(properties.getProperty(object)+"__"+Constants.Landing_addressline1);
		driver.findElement(By.xpath(properties.getProperty(object))).sendKeys(data);
	}
	catch(Exception e)
	{
		fail=true;
		e.printStackTrace();
		System.out.println("Address Line 1 was not entered in the Field");
	}
}

public void input_addressLine_2(String object,String data)
{
	try
	{
		System.out.println("____input_addressLine_2______");
		driver.findElement(By.id(properties.getProperty(object))).sendKeys(data);
	}
	catch(Exception e)
	{
		fail=true;
		e.printStackTrace();
		System.out.println("Address Line 2 was not entered in the Field");
	}
}

public void input(String object,String data) throws Exception
{
	Thread.sleep(3000);
	try
	{
		if(object.endsWith("_xpath")){
			log.info("Typing a keyword:-- "+ object);
			driver.findElement(By.xpath(properties.getProperty(object))).sendKeys(data);
		}
		else if(object.endsWith("_ID")){
			log.info("Typing a keyword:-- "+ object);
			driver.findElement(By.id(properties.getProperty(object))).sendKeys(data);
		}
		else if(object.endsWith("_name")){
			log.info("Typing a keyword:-- "+ object);
			driver.findElement(By.name(properties.getProperty(object))).sendKeys(data);
		}
		else if(object.endsWith("_css")){
			log.info("Typing a keyword:-- "+ object);
			driver.findElement(By.cssSelector(properties.getProperty(object))).sendKeys(data);
		}
		else if(object.endsWith("_ClassName"))
		{
			log.info("Typing a keyword:-- "+ object);
			driver.findElement(By.className(properties.getProperty(object))).sendKeys(data);
		}
	}
	catch(Exception e)
	{
		fail=true;
		e.printStackTrace();
		log.info("Unable to send the Keyqord into the input box");
		throw new Exception("Stopping Script....!!");
	}
}

public void input_zipcode(String object,String data)
{
	try
	{
		driver.findElement(By.id(properties.getProperty(object))).sendKeys(data);
	}
	catch(Exception e)
	{
		fail=true;
		e.printStackTrace();
		System.out.println("Zip Code was not entered in the Field");
	}
}

public void click_Button(String object, String data) throws InterruptedException
{
	Thread.sleep(3000);
	try
	{
		if(object.endsWith("_xpath")){
			log.info("Clicking on:-- "+ object);
			driver.findElement(By.xpath(properties.getProperty(object))).click();
		}
		else if(object.endsWith("_ID")){
			log.info("Clicking on:-- "+ object);
			driver.findElement(By.id(properties.getProperty(object))).click();
		}
		else if(object.endsWith("_name")){
			log.info("Clicking on:-- "+ object);
			driver.findElement(By.name(properties.getProperty(object))).click();
		}
		else if(object.endsWith("_css")){
			log.info("Clicking on:-- "+ object);
			driver.findElement(By.cssSelector(properties.getProperty(object))).click();
		}
		else if(object.endsWith("_ClassName"))
		{
			log.info("Clicking on:-- "+ object);
			driver.findElement(By.className(properties.getProperty(object))).click();	
		}
		
	}
	catch(Exception e)
	{
		fail=true;
		e.printStackTrace();
		log.error("Click on button has been denied");
		System.out.println("Click On Button has been Denied "+e.getMessage());
	}
	
}

public void Check_Current_Page(String object,String data) throws Exception
{
	Thread.sleep(20000);
		if(driver.getCurrentUrl().contains(properties.getProperty("Location_Error")))
		{
			System.out.println("The Address Entered was Incorrect");
			throw new Exception("Stopping Script..!!!");
		}
		else if(driver.getCurrentUrl().contains(properties.getProperty("Address_Not_Match")))
		{
			System.out.println("The Address Entered was not matched, Now handling it");
			try
			{
			WebDriverWait wait = new WebDriverWait(driver,7L);
			wait.until(ExpectedConditions.elementToBeClickable(By.className(properties.getProperty("New_House_checkbox_ClassName"))));
			click_Button("New_House_checkbox_ClassName",data);
			wait.until(ExpectedConditions.elementToBeClickable(By.className(properties.getProperty("New_House_Continue_Button_ClassName"))));
			click_Button("New_House_Continue_Button_ClassName",data);
			}
			catch(Exception e)
			{
				fail=true;
				System.out.println("Address Not Match Page:______Click button was denied____");
				e.printStackTrace();
			}
		}
		else if(driver.getCurrentUrl().contains(properties.getProperty("Retrieve-a-Cart")))
		{
			log.info("_________Retrieve-a-Cart-Page, Now handling it________");
			try
			{
				VerifyPhoneNumber("PhoneNumber_xpath",Constants.Reterive_PhoneNumber);
				click_Button("Proceed_Order_ID",data);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				fail=true;
				log.error("Retrieve-a-Cart-Page element is Throwing an Exception");
				throw new Exception("Stopping Script....!!");
			}
			
		}
		else
		{
			System.out.println("__________________Inside Else Block_______________");
		}
}

public void WaitPageToLoad(String object, String data)
{
	try
	{
		System.out.println("___________Waiting for Page to Load_____________");
		int time=Integer.parseInt(data);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	
	}
	catch(Exception e)
	{
		fail=true;
		e.printStackTrace();
		System.out.println("_________Waited for specfied Time Duration but Page has not been Loaded_________");
	}
}

public void VerifyPhoneNumber(String object, String data) throws Exception
{
	String phoneNumber;
	log.info("Verifying the Phone Number "+data);
	try
	{
		Thread.sleep(10000);
		phoneNumber=driver.findElement(By.xpath(properties.getProperty(object))).getText();
		System.out.println(phoneNumber);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		fail=true;
		log.error("Phone Number Element is not Present on the Page");
		throw new Exception("Stopping Script....!!");
	}
	try
	{
		Assert.assertEquals(phoneNumber, data);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		fail=true;
		log.error("Phone Number has not been verified on Reterive Cart Page");
		throw new Exception("Stopping Script....!!");	
	}
	
}

public void verifyAmount(String object,String data) throws Exception
{
	String amount="";
	try
	{
		if(object.endsWith("_xpath")){
			log.info("Getting Text :-- "+ object);
			amount=driver.findElement(By.xpath(properties.getProperty(object))).getText();
		}
		else if(object.endsWith("_ID")){
			log.info("Getting Text :-- "+ object);
			amount=driver.findElement(By.id(properties.getProperty(object))).getText();
		}
		else if(object.endsWith("_name")){
			log.info("Getting Text :-- "+ object);
			amount=driver.findElement(By.name(properties.getProperty(object))).getText();
		}
		else if(object.endsWith("_css")){
			log.info("Getting Text :-- "+ object);
			amount=driver.findElement(By.cssSelector(properties.getProperty(object))).getText();
		}
		else if(object.endsWith("_ClassName"))
		{
			log.info("Getting Text :-- "+ object);
			amount=driver.findElement(By.className(properties.getProperty(object))).getText();
		}
		try
		{
			Assert.assertEquals(amount,data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			fail=true;
			log.error("Not able to assert the Expected amount");
			throw new Exception("Stopping Script....!!");
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		fail=true;
		log.error("Amount has not been verified");
		throw new Exception("Stopping Script....!!");	
	}
	
	
}

public void switchToModalWindow(String object,String data) throws Exception
{
	try
	{
		/*if (driver.getWindowHandles().size() == 2) {
            for (String window : driver.getWindowHandles()) {
            	System.out.println(window);
                if (!window.equals("")) {
                    driver.switchTo().window(window);
                    System.out.println("Modal dialog found");
                    break;
                }
            }
        }*/

		driver.findElement(By.xpath(properties.getProperty(object))).click();
		log.info("Modal Window Agree button has been selected");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		fail=true;
		log.error("Modal Window has not been focused");
		throw new Exception("Stopping Script...!!!");
	}
}

}
