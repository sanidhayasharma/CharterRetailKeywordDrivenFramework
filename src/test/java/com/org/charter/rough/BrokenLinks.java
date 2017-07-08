package com.org.charter.rough;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BrokenLinks
{
	
WebDriver driver;

public void BrokenLinkTest() throws InterruptedException
{
	driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
    driver.navigate().to("https://www.spectrum.com/");
    
    List<WebElement> l=driver.findElements(By.partialLinkText("LEARN"));
    int s=l.size();
    System.out.println(s);
    for(int i=1;i<=l.size();i++)
    {
    	
    	l.get(i).click();
    	executeTest(driver,"clu-modal",i);
    	break;
    	
    }
    driver.close();
		
}

public static void executeTest(WebDriver driver, String id,int i) throws InterruptedException
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("document.getElementById('"+id+"')");
	//String myWindowHandle = driver.getWindowHandle();
	//driver.switchTo().window(myWindowHandle);
	Thread.sleep(10000);
	String str=driver.findElement(By.xpath("//*[@id='"+id+"']/div/div/div[1]/div/div/div/h"+i+"")).getText();
	System.out.println("Clicking on the text "+str);
	driver.findElement(By.xpath("//*[@id='"+id+"']/div/div/div[1]/button")).click();
	
}
	
public static void main(String args[]) throws InterruptedException
{
	BrokenLinks b = new BrokenLinks();
	b.BrokenLinkTest();
}

}
