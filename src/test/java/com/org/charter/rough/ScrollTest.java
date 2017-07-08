package com.org.charter.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScrollTest 
{
	
	
	public static void main(String args[])
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.spectrum.com/");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("document.getElementById('b90146d1-d2de-4a1e-be4f-923026421d4b').style.borderColor='Red'");
		//driver.findElement(By.xpath("//a[@data-linkname='CBO_500']")).click();
		System.out.println("Scrolling the window");
		
	}

}
