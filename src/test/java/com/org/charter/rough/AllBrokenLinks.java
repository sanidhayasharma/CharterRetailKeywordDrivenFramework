package com.org.charter.rough;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AllBrokenLinks 
{
	
	
	
	public static void main(String []args) throws IOException
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("http://toolsqa.com/automation-practice-switch-windows/");
		List<WebElement> elements=driver.findElements(By.tagName("a"));
		for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).getAttribute("href")!=null)
			{
			//System.out.println(elements.get(i).getAttribute("href"));
			testHTTPLink(elements.get(i).getAttribute("href"));
			System.out.println("URL: " + elements.get(i).getAttribute("href")+ " returned " + testHTTPLink(elements.get(i).getAttribute("href")));
			}
		}
		driver.close();
	}
	
	public static String testHTTPLink(String url) throws IOException
	{
		String str="";
		URL u = new URL(url);
		try
		{
			HttpURLConnection connection=(HttpURLConnection)u.openConnection();
			connection.connect();
			str=connection.getResponseMessage();
			
			connection.disconnect();
			return str;
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		
	}

}
