package utility;

import java.io.IOException;
import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import modest.coreUtility.BaseTest;
import modest.coreUtility.Common_Utility;
import modest.coreUtility.DriverSupplier;
import modest.coreUtility.ElementsHandler;

public class baseTest extends BaseTest implements Configuration_file	
{

	public static ThreadLocal<Locators> 		 	  		  obj_ = new ThreadLocal<Locators>();
	public Common_Utility CU = new Common_Utility();
	private   static Instant 		startTime ;
	private static int testMethodCounter =0;
	private ElementsHandler eH = new ElementsHandler();
	
	public void intiateAutomation() throws Exception {
		setScreenshottakeCriteria(true, false);
		Ready_Set_Go("",false, isAndroid, isiOS, isANDROID_iOS);
		obj_.set(new Locators(getDevicePlatform()));
		testMethodCounter++;
	}

	/**
	 * @function - obj => used to get the initialized object with Locators according
	 *           to nature of device (iOS or Android) based on thread calling the
	 *           method.
	 * @return - Locators object.
	 */
	public Locators obj() {
		return obj_.get();
	}

	
	
	@BeforeSuite
	public void Initial_Setup() throws IOException {
		startTime = Instant.now();
		readConfigurationProperites("", true, isAndroid, isiOS, isANDROID_iOS);
		DriverSupplier.Get_All_Connected_Devices(isAndroid, isiOS, isANDROID_iOS);
	}

	
	
	@AfterSuite
	public void Tear_Down() throws IOException, InterruptedException {
		if (testMethodCounter > 0)
			CU.generate_report(ServeReport);

		System.out.print("\n**********************************************************");
		System.out.print("\nTest Suite Execution Duration " + calcTimeDifference(startTime));
		System.out.println("\n**********************************************************\n");
	}
	
	
	
	 public By returnDynamicLocator ( By locator, String[] replaceText, String locatorType) {
		 
		 String originalLocator = locator.toString().split("By."+locatorType.toLowerCase().trim()+": ")[1];
		 
		 for (String s : replaceText)
			 originalLocator = originalLocator.replaceFirst("replaceText", s);
		 
		 locatorType = locatorType.toLowerCase();
		 switch (locatorType) {
		  
		 case "xpath"		:  return By.xpath(originalLocator);
		 case "id"			:  return By.id(originalLocator);
		 case "name" 		:  return By.name(originalLocator);
		 case "classname" 	:  return By.className(originalLocator);
		 case "cssselector"	:  return By.cssSelector(originalLocator);
		 case "tagname"		:  return By.tagName(originalLocator);
		 case "linktext"	:  return By.linkText(originalLocator);
		 case "partiallinktext"	:  return By.partialLinkText(originalLocator);
		 
		 default : System.out.println("Invalid locator type. Only xpath, id, name, classname, tagname, linktext and partiallinktext are allowed. ");
		  return null;
		 }
		 
	 }

	 
		/**
		 * parseExcelData - Usage is to parse string value from excel to form array of string data for single key
		 * @param testData {String} - value read from dataprovider
		 * @return - {String[]} - array of string with multiple data belonging to one header
		 */
		public String[] parseExcelData(String testData) {
			if (testData.equalsIgnoreCase("")) {
				System.out.println("Test Data Empty.");
				return new String[] {};
			}
			return  testData.split("[,]");
		}	

		
		/**
		 * swipeWhileNotFound - Used to swipe screen in either direction up/down to look for an element
		 * @param objectLocator - {By} locator of the element being searched for
		 * @param swipeDirection - {Boolean} true/false for up/down swipe
		 * @return - {boolean} if element with locator details is found : true, false otherwise
		 * @throws Exception
		 */
		public boolean swipeWhileNotFound(By objectLocator, boolean swipeDirection) throws Exception {
			int counter = 0;
			while (true) {
				try {
					if (counter++ == 100)
						return false;
					driver().findElement(objectLocator);
					if (swipeDirection)
						eH.swipeVertical(0.5, 0.6, 0.4, 500);
					else {
						eH.swipeVertical(0.5, 0.4, 0.6, 500);
					}
					return true;
				} catch (NoSuchElementException no) {
					if (swipeDirection)
						eH.swipeVertical(0.5, 0.6, 0.4, 500);
					else {
						eH.swipeVertical(0.5, 0.4, 0.6, 500);
					}
				}
			}
		}


}
