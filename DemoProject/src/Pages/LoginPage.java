package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{	
	WebDriver driver;	
	public LoginPage(WebDriver dr)
	{
		driver=dr;
	}
	
	/**
	 * This method is used to login to 
	 *
	 * @param username username of rediff user
	 * @param password password of rediff user
	 * @throws Exception 
	 */
	
	public void CreateNewAccount (String username,String Password,String FirstName,String LastName,String CompanyName,String AddressLine1,
			String AddressLine2,String City,String State,String PostCode,String OtherInformation,String PhoneNumber,
			String MobileNumber,String AddressAlias) throws Exception
	{	
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='login']")));
		driver.findElement(By.xpath("//a[@class='login']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
		driver.findElement(By.id("email_create")).sendKeys(username);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitCreate")));
		driver.findElement(By.id("SubmitCreate")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));		
		driver.findElement(By.id("id_gender1")).click();
		
		driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(LastName);
		driver.findElement(By.id("passwd")).sendKeys(Password);
		
		Select Day=new Select(driver.findElement(By.id("days")));
		Day.selectByValue("1");
		
		Select Month=new Select(driver.findElement(By.id("months")));
		Month.selectByIndex(9);
		
		Select Year=new Select(driver.findElement(By.id("years")));
		Year.selectByValue("1993");
		
		driver.findElement(By.id("firstname")).sendKeys(FirstName);
		driver.findElement(By.id("lastname")).sendKeys(LastName);
		
		driver.findElement(By.id("company")).sendKeys(CompanyName);
		driver.findElement(By.id("address1")).sendKeys(AddressLine1);
		
		driver.findElement(By.id("address2")).sendKeys(AddressLine2);
		driver.findElement(By.id("city")).sendKeys(City);
		
		Select StateSelect=new Select(driver.findElement(By.id("id_state")));
		StateSelect.selectByValue("32");
		
		driver.findElement(By.id("postcode")).sendKeys(PostCode);
		
		driver.findElement(By.id("other")).sendKeys(OtherInformation);
		
		driver.findElement(By.id("phone")).sendKeys(PhoneNumber);
		driver.findElement(By.id("phone_mobile")).sendKeys(MobileNumber);
		driver.findElement(By.id("alias")).sendKeys(AddressAlias);
		
		driver.findElement(By.id("submitAccount")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Log me out']")));
		driver.findElement(By.xpath("//a[@title='Log me out']")).click();		
	}	

	public void LoginWithExitingUser(String Username,String Password) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='login']")));
		driver.findElement(By.xpath("//a[@class='login']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(Username);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd")));
		driver.findElement(By.id("passwd")).sendKeys(Password);
		
		driver.findElement(By.id("SubmitLogin")).click();
	}
}
