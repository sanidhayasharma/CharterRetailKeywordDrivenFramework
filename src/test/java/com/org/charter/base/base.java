package com.org.charter.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.org.charter.config.Constants;
import com.org.charter.utilities.ExcelReader;

public class base 
{
private Properties properties; 
private FileInputStream fis;
private String baseURL;
private Logger log;
private ExcelReader reader;
private WebDriver driver;
private int brow;
	
public WebDriver getDriver()
{
	System.out.println("_______"+driver);
	return driver;
}

public void setDriver(int browser, String baseURL)
{
	switch(browser)
	{
	case 1:
		driver=initFirefoxDriver(baseURL);
		System.out.println("______________Launching Firefox Browser______________");
		break;
		
	case 2:
		driver=initIEDriver(baseURL);
		System.out.println("______________Launching Internet Explorer Browser______________");
		break;
		
	case 3:
		driver=initChromeDriver(baseURL);
		System.out.println("______________Launching Chrome Browser______________");
		break;
		
	default:
		driver=initFirefoxDriver(baseURL);
		System.out.println("______________Launching Default Browser______________");
		break;
	}
}

public WebDriver initFirefoxDriver(String baseURL)
{
	System.out.println("______________Firefox Driver Selected______________");
	//System.setProperty("webdriver.gecko.driver", "E:\\SeleniumStuff\\SeleniumImportant1\\GeckoDriver\\13\\geckodriver.exe");
	driver= new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.navigate().to(baseURL);
	//driver.get(baseURL);
	return driver;
}

public WebDriver initIEDriver(String baseURL)
{
	System.out.println("______________Internet Explorer Driver Selected______________");
	driver= new InternetExplorerDriver();
	driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.navigate().to(baseURL);
	//driver.get(baseURL);
	return driver;
}

public WebDriver initChromeDriver(String baseURL)
{
	System.out.println("______________Firefox Driver Selected______________");
	driver= new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.navigate().to(baseURL);
	//driver.get(baseURL);
	return driver;
}
public int getBrowserDetails(String browser)
{
	try
	{
	
	if(browser.equalsIgnoreCase("firefox"))
	{
		System.out.println("__________Firefox Selected as a Driver____________");
		return 1;
	}
	else if(browser.equalsIgnoreCase("ie"))
	{
		System.out.println("__________Internet Explorer Selected as a Driver__________");
		return 2;
	}
	else if(browser.equalsIgnoreCase("chrome"))
	{
		System.out.println("___________Chrome Selected as a Driver_____________");
		return 3;
	}
	else
	{
		System.out.println("___________Driver not Selected____________");
		return 0;
	}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Driver not Selected");
		return 0;
	}
}

public Properties getInitializedProperties()
{
	try
	{
		fis = new FileInputStream("E:\\SeleniumStuff\\Selenium Workspace Neon\\CharterRetailKeywordFramework\\src\\test\\java\\com\\org\\charter\\properties\\OR.properties");
		properties = new Properties();
		properties.load(fis);
		System.out.println("________________Properties Initialized_________________");
		return properties;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("________________Properties Not Initialized_______________");
	
	}
	return properties;
}

public ExcelReader getExcelReaderInitialized()
{
	try
	{
		reader= new ExcelReader(Constants.Excelsheet_path);	
		System.out.println("Excel Reader Initialized");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Excel Reader not Initialized");
	}
	return reader;
}

public Logger getLoggerIntialized()
{
	try
	{
		log=Logger.getLogger("devpinoyLogger");
		PropertyConfigurator.configure("E:\\SeleniumStuff\\Selenium Workspace Neon\\CharterRetailKeywordFramework\\src\\test\\java\\com\\org\\charter\\properties\\Log4j.properties");
	    log.info("_________________Logger has been Intialized__________________");
	}
	catch(Exception e)
	{
		System.out.println("Logger has not been Intialized");
		e.printStackTrace();
	}
	return log;
}

@BeforeTest
@Parameters("browser")
public void launchBrowser(@Optional("firefox")String browsing)
{
try
{
	getLoggerIntialized();
	brow=getBrowserDetails(browsing);
	//getInitializedProperties();
	//getExcelReaderInitialized();
	//baseURL=properties.getProperty("AUTURL");
	baseURL=Constants.AUTURL;
	setDriver(brow,baseURL);
	
}
catch(Exception e)
{
	e.printStackTrace();
	System.out.println("____________Launching Browser Exception_____________");
}

}

@AfterTest
public void tearDown()
{
	
	driver.quit();
	
}

}
