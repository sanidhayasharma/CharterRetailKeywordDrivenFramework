package com.org.charter.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test 
{
	public static void main(String args[])
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.spectrum.com/");
		driver.findElement(By.className("localization__input-submit")).click();
		
	}

}
