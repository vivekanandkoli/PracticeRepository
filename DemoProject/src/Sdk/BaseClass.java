package Sdk;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import TestDataReaders.PropertiesFileReader;

public class BaseClass 
{
	public WebDriver driver;
	String url,browser;
	String implicit_wait;
	
	@BeforeClass
	public void intialization()
	{
		
		PropertiesFileReader pfr=new PropertiesFileReader();
		pfr.loadPropertiesFile();
		url=pfr.getPropertyValue("url");
		browser= pfr.getPropertyValue("browser");
		implicit_wait=pfr.getPropertyValue("implicit.wait");
		 
		WebdriverFactory wdf=new WebdriverFactory();
		driver=wdf.getdriver(browser);
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicit_wait),TimeUnit.SECONDS );
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
