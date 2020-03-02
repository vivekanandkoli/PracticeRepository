package TestPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.LoginPage;
import Sdk.BaseClass;
import Sdk.Variables_Declaration;
import TestDataReaders.ExcelFileReader;
import TestDataReaders.TestDataCollection;
import TestDataReaders.TestDataReader;
import TestDataReaders.TestDataReaderFactory;

public class TestClass extends BaseClass
{
	Variables_Declaration vd=new Variables_Declaration();	
	LoginPage lp=new LoginPage(driver);
	
	@BeforeClass
	public void ReadDataUserDetails() throws Exception
	{		
		TestDataReader tdr=TestDataReaderFactory.getInstance().open("test_data\\user-accounts.yml");
		TestDataCollection tdc=tdr.getCollection("login");		
		vd.Username = tdc.getString("Email");
		vd.Password=tdc.getString("Password");		
	}	

	@BeforeMethod
	public void ReadDataTestData() throws Exception
	{		
		String path = System.getProperty("user.dir");		
		String TestDataExcel=path+"\\test_data\\TEST_DATA.xls";		
		ExcelFileReader efr=new ExcelFileReader(TestDataExcel);
		String SheetName="Demo";
		
		vd.FirstName=efr.getCellData(SheetName, "First Name",vd.i);
		vd.LastName=efr.getCellData(SheetName, "Last Name",vd.i);
		vd.CompanyName=efr.getCellData(SheetName, "Company Name",vd.i);
		vd.AddressLine1=efr.getCellData(SheetName, "Address Line1",vd.i);
		vd.AddressLine2=efr.getCellData(SheetName, "Address Line2",vd.i);
		vd.City=efr.getCellData(SheetName, "City",vd.i);
		vd.State=efr.getCellData(SheetName, "State",vd.i);
		vd.PostCode=efr.getCellData(SheetName, "Post Code",vd.i);
		vd.OtherInformation=efr.getCellData(SheetName, "Other Information",vd.i);
		vd.PhoneNumber=efr.getCellData(SheetName, "Phone Number",vd.i);
		vd.MobileNumber=efr.getCellData(SheetName, "Mobile Number",vd.i);
		vd.AddressAlias=efr.getCellData(SheetName, "Address Alias",vd.i);		
	}

	@Test
	public void CreateNewAccount() throws Exception
	{		
		LoginPage lp=new LoginPage(driver);
		lp.CreateNewAccount(vd.Username, vd.Password,vd.FirstName,vd.LastName,vd.CompanyName,vd.AddressLine1,vd.AddressLine2,vd.City,
				vd.State,vd.PostCode,vd.OtherInformation,vd.PhoneNumber,vd.MobileNumber,vd.AddressAlias);			
	}
	
	@Test(dependsOnMethods = "CreateNewAccount")
	public void LoginwithExitingUser() throws Exception
	{
		LoginPage lp=new LoginPage(driver);
		HomePage hp=new HomePage(driver);
		lp.LoginWithExitingUser(vd.Username,vd.Password);
		hp.SelectProduct();
		hp.ProductCheckout();
		hp.CheckoutFromSummaryPage();
		hp.CheckoutFromAddressPage();
		hp.CheckoutFromShippingPage();
		hp.CheckoutFromPaymentPage();
		hp.ProfileAndOrderHistoryPage();		
	}	
}

