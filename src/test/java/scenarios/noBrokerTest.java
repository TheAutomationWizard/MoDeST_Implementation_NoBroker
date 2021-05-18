package scenarios;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.read.biff.BiffException;

import org.testng.Assert;
import org.testng.ITestContext;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Listeners;
import modest.coreUtility.DriverSupplier;
import modest.coreUtility.ExcelLib;
import modest.reporting.modestReporting;
import pages.NBHomePage;
import pages.PropertyDetailsPage;
import pages.PropertyReportsPage;
import utility.baseTest;




	@Listeners(modestReporting.class)
	public class noBrokerTest extends baseTest {

		NBHomePage			homePage 	= new NBHomePage(50000);
		PropertyDetailsPage propertyPage= new PropertyDetailsPage(50000);
		PropertyReportsPage propReportPg= new PropertyReportsPage(40000);
		
		SoftAssert 						 assertion = new SoftAssert();

		
		@Test(dataProvider = "data", invocationCount=1)
		public void NoBrokerTestCase(ITestContext context, String phoneNumber, String password, String cityofChoice, String localities, String bedrooms, String index, String checks,  String EditType, String configurationValue) throws Exception
		{
			
		/****************        Launch -> Login -> Initialize flight          ****************/
			Assert.assertTrue(homePage.grantPermissions(), "Failed to grant permissions" );
			CU.stepPrinter("Successfully granted required permissions.", "PASS");
			
			Assert.assertTrue(homePage.openBuySearchContext(), "Search Page Openend : Failed" );
			CU.stepPrinter("Successfully opened : Buy Property Search Page.", "PASS");
			
			Assert.assertTrue(homePage.searchForAProperty(cityofChoice, parseExcelData(localities), true, parseExcelData(bedrooms) ), "Search For a Property : Failed." );
			CU.stepPrinter("Successfully searched : Buy Property with mulitple details.", "PASS");
			
			Assert.assertTrue(propertyPage.selectPropertyByIndex(Integer.parseInt(index) ), "Select a Property from list at given index : FAILED" );
			CU.stepPrinter("Select a Property from list at given index.", "PASS");
			
			
			
			Assert.assertTrue(propReportPg.createWrongInfoReport(phoneNumber, password, parseExcelData(checks) ), "create a wrong info report for a property : FAILED" );
			CU.stepPrinter("Succesfully created a wrong info report for a property.", "PASS");
			
			Assert.assertTrue(propReportPg.suggestAnEdit(EditType, configurationValue), "Change configuration in suggest an edit page? : FAILED" );
			CU.stepPrinter("Suggest an Edit? chaged configuration", "PASS");
			

		}
		
		
		
		
		@BeforeMethod(alwaysRun = true)	
		public void setUp(ITestContext context) throws Exception {
			intiateAutomation();
		}
		
		
		@DataProvider(parallel = false) 
		public Object[][] data() throws BiffException, IOException {
				ExcelLib xl= new ExcelLib("NoBrokerAssignment",this.getClass().getSimpleName());
				return xl.getTestdata();
		}


		
		@AfterMethod(alwaysRun=true)
		public void tearDown(ITestContext context) throws FileNotFoundException	{
		  DriverSupplier.UDID_Resetter_digital(UDID()); 
		  DriverSupplier.kill_APPIUM(service() , driver());
//		  driver().quit(); service().stop();
		}

	
}
