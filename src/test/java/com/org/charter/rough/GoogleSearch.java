package com.org.charter.rough;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSearch 
{
	
	public static void main(String args[]) throws InterruptedException
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.google.in");
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("document.getElementById('gs_htif0').removeAttribute('disabled');");
		//driver.findElement(By.xpath("//div[@id='sb_ifc0']/div/input[@id='lst-ib']")).sendKeys("Selenium");
		driver.findElement(By.id("lst-ib")).sendKeys("Sanidhaya");
		Thread.sleep(10000);
		List<WebElement> l=driver.findElements(By.xpath("//li[@class='sbsb_c gsfs']"));
		System.out.println(l.size());
		for(int i=0;i<l.size()-1;i++)
		{
			System.out.println(l.get(i).getText());
			
		}
		
	}

}
