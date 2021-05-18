package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.qameta.allure.Step;

public class PropertyReportsPage  extends basePage{

	public PropertyReportsPage(int waitTime) {
		super(waitTime);
	}
	
	
	
	/**
	 * createWrongInfoReport - Usage is to create a wrongInfo reoprt for a given property 
	 * @param phoneNumber - {String} phonenumber of the account which is used to create report
	 * @param password - {String} password of the account which is used to create report
	 * @param checksDetails - {String[]} list of all checkboxes to be clicked
	 * @return - {boolean} true if able to create a wronginfo report , false otherwise
	 * @throws Exception
	 */
	@Step ("creating a report for user with Phonenumber : \"{0}\" and password : \"{1}\"")
	public boolean createWrongInfoReport(String phoneNumber, String password, String[] checksDetails) throws Exception {
		raiseReport("WrongInfo");

		if (!checkForElementToBeVisible(bt.obj().whatsWrongHeader, 2000)) {
			if (signIn(phoneNumber, password)) {
				if (checkForElementToBeVisible(bt.obj().whatsWrongHeader, 10000)) {
					System.out.println("What's wrong? page is displayed.");
				}
			}
		}
		for( String s : checksDetails) {
			
			if (s.equalsIgnoreCase("Location"))
				clickElement(bt.obj().locationCB);
			else if (s.equalsIgnoreCase("Fake Photos"))
				clickElement(bt.obj().fakePhotosCB);
			else if (s.equalsIgnoreCase("BHK Type"))
				clickElement(bt.obj().BHKTypeCB);
			else if (s.equalsIgnoreCase("Availability Date"))
				clickElement(bt.obj().availabilityDate);
			else if (s.equalsIgnoreCase("Price"))
				clickElement(bt.obj().priceCB);
			else if (s.equalsIgnoreCase("Other"))
				clickElement(bt.obj().OtherCB);
		}
		saveScreenshotPNG(driver());
		clickElement(bt.obj().ReportBtn);
		
		if (checkForElementToBeVisible(bt.obj().feedbackMsg, 10000)) {
			System.out.println("What's wrong info provided sucessfully.");
			saveScreenshotPNG(driver());
			return true;
		}
			return false;	
	}
	
	
	/**
	 * signIn - Usage is to login/signin into the app
	 * @param phoneNumber - {String} phonenumber of the account which is used to create report
	 * @param password - {String} password of the account which is used to create report
	 * @return - {boolean} true if the sign is is successfull, false otherwise.
	 * @throws Exception
	 */
	public boolean signIn( String phoneNumber, String password) throws Exception {
		if (checkForElementToBeVisible(bt.obj().enterPhoneNumber, 10000))
			enterText(bt.obj().enterPhoneNumber, phoneNumber, true);
		
		if (checkForElementToBeVisible(bt.obj().ivePassword, 120000)) {
			clickElement(bt.obj().ivePassword);
			if (checkForElementToBeVisible(bt.obj().enterPassword, 10000))
				enterText(bt.obj().enterPassword, password, true);
			else {
				System.out.println("Unable to enter phone number.");
				return false;
			}
		}
			clickElement(bt.obj().continueBtn);
		return true;
		

	}
	
	
	/**
	 * raiseReport - Usage is to bring the reoprt to be raised into viewable screen.
	 * @param reportItem - {String} the type of report to be created
	 * @return - 
	 * @throws Exception
	 */
	
	public boolean raiseReport(String reportItem) throws Exception {
		By reportLocator = null;
		
		switch(reportItem.toLowerCase() ) {
		
		case "wronginfo" :
			reportLocator = bt.obj().wrongInfoBtn; break;
			
		default : System.out.println("Only WrongInfo can be opened as of now.");
				return false;
		}
		
		while(true) {
			swipeVertical(0.5, 0.6, 0.4, 500);
			
			try {
				driver().findElement(reportLocator);
				swipeVertical(0.5, 0.6, 0.4, 500);
				break;
			}
			catch (NoSuchElementException no) {
			}
		}
		
		clickElement(reportLocator);
		
		if (checkForElementToBeVisible(bt.obj().enterPhoneNumber, 2000))
			return true;
		else if (checkForElementToBeVisible(bt.obj().whatsWrongHeader, 5000)) {
			System.out.println("What's wrong? page is displayed."); 
		return true;
		}
		return false;
	}
	
	
	/**
	 * suggestAnEdit - Usage is to perform an edit suggestion for a wrong info property
	 * @param EditType - {String} name of the edit to be done
	 * @param configurationValue - {String} value for the edit selected
	 * @return - {boolean} true/false , whether or not able to perform the desired edit.
	 * @throws Exception
	 */
	@Step ("Suggest an Edit. Edit details are - Edit for :\"{0}\" with new value as : \"{1}\"")
	public boolean suggestAnEdit(String EditType, String configurationValue) throws Exception {
		
		
		if (checkForElementToBeVisible(bt.obj().suggestAnEditHeader, 10000)) {
			System.out.println("Suggest An Edit, Page is displayed");
		
			if(EditType.equalsIgnoreCase("What is the correct configuration?"))	{
				if(bt.swipeWhileNotFound(bt.obj().whatisCorrectConfiguration, true)) {
					saveScreenshotPNG(driver());
					clickElement(bt.obj().whatisCorrectConfiguration);
					
					if (checkForElementToBeVisible(bt.obj().configurationContainer, 10000)) {
						swipeInElement(driver().findElement(bt.obj().configurationContainer) , true, 0.5, 0.4, 0.6, 500); 
					
						if(configurationValue.equalsIgnoreCase("4+ BHK") | configurationValue.equalsIgnoreCase("4 BHK"))
							swipeInElement(driver().findElement(bt.obj().configurationContainer) , true, 0.5, 0.6, 0.4, 500);
						
						clickElement(bt.returnDynamicLocator(bt.obj().correctConfiguration, new String[] {configurationValue}, "xpath"));
						saveScreenshotPNG(driver());
					}
					
					// Add a Note
					if(bt.swipeWhileNotFound(bt.obj().addANote, true)) {
							enterText(bt.obj().addANote, "Changed Configuration to "+configurationValue, false);
					}
				
					clickElement(bt.obj().suggestEditSaveChangesBtn);
					
					if(waitForElementInVisibility(bt.obj().loadingMsg, 12000)) 
						if (checkForElementToBeVisible(bt.obj().ThankYoufortheFeedBack, 10000)) {
							saveScreenshotPNG(driver());
							return true;
							}
						else {
							System.out.println("Thank you msg not shown.");
							return false;
						}
					else {
							System.out.println("Waited too long for Thank you for the feedback popup. Timeout.");return false;
					}
				}
				else {
					System.out.println("Unable to perform suggested edit? Not available on screen");
					return false;
				}
			}
				
			
			
		}
	
		
		
		
		return false;
	}
	
	
	
	
	
}
