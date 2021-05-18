package utility;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;



public class Locators {

	
	
/**
 * 	Describe all the locators below	as "By"
 * 	Segregate all locators against pages they are located in ..for easier maintenance.
 */

	
	
	public By privacyPolicy, privacyPolicyContinueBtn, accessAlerts, allowAccess, allowLocOnlyWhileUsingApp, noBrokerLogo, burgerMenuBtn, buyTab, searchPropertyHP ;
	
	public By locationDropDown, preSelectedLocaiton, cityOfChoice, addLocalitySearchBox, addedLocations, includeNearByPropertiesCB, numberOfBedRoomsTabs, searchProperty, loadingResults;
	
	public By allPropertyTitles, specificPropertyTitle, allPropertySubTitles, specificPropertyPrice, specificProppertyContainer, allPropertyContainer, PropertyPriceinContainer, PropertyTitleinContainer ;
	
	public By openPropertyTitle, conatactOwnerBtn, wrongInfoBtn;
	
	public By enterPhoneNumber, autoLoginMsg, ivePassword, enterPassword, continueBtn ;
	
	public By whatsWrongHeader, locationCB, fakePhotosCB, BHKTypeCB, availabilityDate, priceCB, OtherCB, ReportBtn, feedbackMsg ;
	
	public By suggestAnEditHeader, whatisCorrectConfiguration, configurationContainer, correctConfiguration, addANote, suggestEditSaveChangesBtn, loadingMsg, ThankYoufortheFeedBack ;
	
/**
 * 		Assign values for all locators ==  based on the platform type.
 * 		Use Common objects to define locators if test flow is dependent on some extra
 * 		execution platform like base is Android with web application inclusion as extra. 
 * 	
 */
	

// Assign locator values to all the objects for Android device...	
	private void setAndroidObjects() 
	{

		
		
		// Sign in Screen 
		
		privacyPolicy 				= By.id("com.nobroker.app:id/privacyPolicyTextView");
		privacyPolicyContinueBtn	= By.id("com.nobroker.app:id/bottomPermission");
		accessAlerts				= By.id("com.android.permissioncontroller:id/permission_message");
		allowAccess					= By.id("com.android.permissioncontroller:id/permission_allow_button");
		allowLocOnlyWhileUsingApp   = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
		
		// Home Page Locators
		noBrokerLogo				= By.id("com.nobroker.app:id/logo");
		burgerMenuBtn				= MobileBy.AccessibilityId("Navigate up");
		buyTab						= By.id("com.nobroker.app:id/buyLayoutText");
		searchPropertyHP			= By.id("com.nobroker.app:id/searchEditHome");
		
		
		
		// Search Property Page Locators
		locationDropDown			= By.id("com.nobroker.app:id/spinnergo");
		preSelectedLocaiton			= By.id("android:id/text1");
		cityOfChoice				= By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='replaceText']");
		addLocalitySearchBox		= By.id("com.nobroker.app:id/localityAutoCompleteTxt");
		addedLocations 				= By.xpath("//android.view.ViewGroup[@resource-id='com.nobroker.app:id/fl_multiple_locations']/android.widget.CheckBox");
		includeNearByPropertiesCB   = By.id("com.nobroker.app:id/nearByRadio");   // use checked property to get whether its checked or not.
		numberOfBedRoomsTabs		= By.id("com.nobroker.app:id/replaceText");  // accepts 1) roomone 2) bhkone, bhktwo, bhkthree,  bhkfour, plusbhkfour
		searchProperty				= By.id("com.nobroker.app:id/searchProperty");
		loadingResults				= By.xpath("//android.widget.TextView[@resource-id='com.nobroker.app:id/property_list_result' and @text='loading...']");
		
		
		
		
		// Search Result Page 
		
		allPropertyTitles			= By.id("com.nobroker.app:id/title");
		specificPropertyTitle		= By.xpath("//android.widget.TextView[@resource-id='com.nobroker.app:id/title' and @text='replaceText']");
		allPropertySubTitles		= By.id("com.nobroker.app:id/sub_title");
		specificPropertyPrice		= By.xpath("(//android.widget.TextView[@resource-id='com.nobroker.app:id/title' and @text='replaceText']/ancestor::android.widget.FrameLayout[@resource-id='com.nobroker.app:id/cv']//android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[1]//android.widget.TextView)[1]");
		specificProppertyContainer  = By.xpath("//android.widget.TextView[@resource-id='com.nobroker.app:id/title' and @text='replaceText']/ancestor::android.widget.FrameLayout[@resource-id='com.nobroker.app:id/cv']");
		
		allPropertyContainer		= By.xpath("//android.widget.FrameLayout[@resource-id='com.nobroker.app:id/cv']");
		PropertyPriceinContainer    = By.xpath("//android.widget.TextView[@resource-id='com.nobroker.app:id/property_cost']");
		PropertyTitleinContainer	= By.xpath("//android.widget.TextView[@resource-id='com.nobroker.app:id/title']");
		
		
		
		// Property Details 
		openPropertyTitle 			= By.id("com.nobroker.app:id/basic_info_title");
		conatactOwnerBtn			= By.id("com.nobroker.app:id/contactOwnerInDetail");
		wrongInfoBtn				= By.id("com.nobroker.app:id/tv_report_wrong_info");
		
		
		// Sign-up details
		enterPhoneNumber 			= By.id("com.nobroker.app:id/et_signup_phone");
		autoLoginMsg				= By.id("android:id/message");
		ivePassword					= By.id("com.nobroker.app:id/rb_signup_pwd");
		enterPassword				= By.id("com.nobroker.app:id/et_signup_pwd");
		continueBtn					= By.id("com.nobroker.app:id/btn_signup");
		
		
		// Wrong : Detiails
		whatsWrongHeader			= By.xpath("//android.widget.TextView[@text=\"What's wrong?\"]");
		locationCB					= By.id("com.nobroker.app:id/cb_location");
		fakePhotosCB				= By.id("com.nobroker.app:id/cb_fake_photos");
		BHKTypeCB					= By.id("com.nobroker.app:id/cb_bhk_type");
		availabilityDate			= By.id("com.nobroker.app:id/cb_availability_date");
		priceCB						= By.id("com.nobroker.app:id/cb_price");
		OtherCB						= By.id("com.nobroker.app:id/cb_other");
		ReportBtn					= By.id("com.nobroker.app:id/btn_report");
		
		
		// Suggest An Edit Page
		suggestAnEditHeader			= By.xpath("//android.widget.TextView[@text='Suggest an edit']");
		feedbackMsg					= By.xpath("//*[@text='Thank you for the feedback']");
		whatisCorrectConfiguration  = By.xpath("//android.widget.Spinner[@resource-id='com.nobroker.app:id/sp_bhk_type']/android.widget.TextView");
		configurationContainer		= By.xpath("//android.widget.ListView");
		correctConfiguration        = By.xpath("//android.widget.TextView[@text='replaceText']");
		suggestEditSaveChangesBtn   = By.id("com.nobroker.app:id/btn_save");
		addANote 					= By.xpath("//android.widget.EditText[@resource-id='com.nobroker.app:id/edt_others' and @text='Add a note']");
		
		loadingMsg 					= By.xpath("//android.widget.TextView[@resource-id='android:id/message' and @text='loading...']");
		ThankYoufortheFeedBack      = By.xpath("//android.widget.TextView[@text='Thank you for the feedback']");
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	Assign locator values to all the objects for iOS device...	
	private void setiOSObjects() {
//		LoginPageTitle = By.id("this is set with iOS element locator value.");
	}
//	Assign locator values to all the objects for Web browsers...	
	private void setWebObjects() {
//		LoginPageTitle = By.name("This is set for web element locator value");
	}

//	Assign Common Objects Here...extensively used in case of dependency on Web App if any. To set extra set of webpp locators..  	
	private void setCommonObjects() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**     This is FIXED for all projects. ______________PLEASE DO NOT MODIFY___________     **/	
	
	//	Constructor to assign objects based on the nature of the device.	
	public Locators(String DevicePlatform) {

		if (DevicePlatform.equalsIgnoreCase("Android")) {
			setAndroidObjects();
			setCommonObjects();
		} else if (DevicePlatform.equalsIgnoreCase("iOS")) {
			setiOSObjects();
			setCommonObjects();
		} else {
			setWebObjects();
			setCommonObjects();
		}
	}
	
}
