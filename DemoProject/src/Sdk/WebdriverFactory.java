package Sdk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class created for creating driver
 * @author Vivekanand.Koli
 *
 */
public class WebdriverFactory 
{
	WebDriver driver;
	
	public WebDriver getdriver(String browser)
	{		
		if(browser.equals("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver","executables\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		
			return driver;
	}
}
