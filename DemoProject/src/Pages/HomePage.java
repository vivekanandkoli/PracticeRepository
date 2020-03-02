package Pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Sdk.Variables_Declaration;

public class HomePage 
{
	WebDriver driver;
	Variables_Declaration vd=new Variables_Declaration();
	public HomePage(WebDriver dr)
	{
		driver=dr;
	}
	
	public void SelectProduct()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='block_top_menu']//a[contains(text(),'Women')]")));
			driver.findElement(By.xpath("//div[@id='block_top_menu']//a[contains(text(),'Women')]")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Blouse']")));
			Actions action=new Actions(driver);		
			WebElement product=driver.findElement(By.xpath("//img[@title='Blouse']"));			
			action.moveToElement(product).perform();
			
			driver.findElement(By.xpath("//*[@id='center_column']/ul/li[2]/div/div[1]/div/a[2]/span")).click();			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='fancybox-iframe']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']")));
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-plus']")));
			driver.findElement(By.xpath("//i[@class='icon-plus']")).click();			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
			driver.findElement(By.name("Submit")).click();			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	public void ProductCheckout()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ajax_block_cart_total']")));	
			vd.ProductPrice= driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();
			System.out.println("ProductCostOnCheckoutPage =  "+vd.ProductPrice);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Proceed to checkout')]")));
			driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	public void CheckoutFromSummaryPage()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='total_price']")));
			String ProductCostOnSummaryPage= driver.findElement(By.xpath("//span[@id='total_price']")).getText();
			System.out.println("ProductCostOnSummaryPage =  "+ProductCostOnSummaryPage);
			assertEquals(ProductCostOnSummaryPage, vd.ProductPrice);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='center_column']/p[2]/a[1]/span")));
			driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]/span")).click();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	public void CheckoutFromAddressPage()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='processAddress']")));
			driver.findElement(By.xpath("//button[@name='processAddress']")).click();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	public void CheckoutFromShippingPage()
	{
		try
		{			
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='processCarrier']")));
			driver.findElement(By.id("cgv")).click();		
			
			driver.findElement(By.xpath("//button[@name='processCarrier']")).click();				
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	public void CheckoutFromPaymentPage()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='total_price']")));
			
			String ProductCostOnPaymentPage= driver.findElement(By.xpath("//span[@id='total_price']")).getText();
			System.out.println("ProductCostOnPaymentPage =  "+ProductCostOnPaymentPage);
			assertEquals(ProductCostOnPaymentPage, vd.ProductPrice);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Pay by bank wire']")));
			driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	public void ProfileAndOrderHistoryPage()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button/span")));
			driver.findElement(By.xpath("//*[@id='cart_navigation']/button/span")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='View my customer account']")));
			driver.findElement(By.xpath("//*[@title='View my customer account']")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='center_column']/div/div[1]/ul/li[1]/a/span")));
			driver.findElement(By.xpath("//*[@id='center_column']/div/div[1]/ul/li[1]/a/span")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='order-list']/tbody/tr[1]/td[3]/span")));
			String productPriceOnOrderHistory=driver.findElement(By.xpath("//*[@id='order-list']/tbody/tr[1]/td[3]/span")).getText();			
			
			System.out.println("productPriceOnOrderHistory =  "+productPriceOnOrderHistory);
			assertEquals(productPriceOnOrderHistory, vd.ProductPrice);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
