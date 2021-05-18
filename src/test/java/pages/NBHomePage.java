package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class NBHomePage extends basePage{

	public NBHomePage(int waitTime) {
		super(waitTime);
	}

	
	
	/**
	 * openBuySearchContext - Used to open search page, while buying a property
	 * @return	- {boolean} true if able to open the searchPage , false otherwise
	 * @throws Exception
	 */
	@Step ("Open search context for buying a property")
	public boolean openBuySearchContext() throws Exception {

		if (checkForElementToBeVisible(bt.obj().buyTab, 10000)) {
			clickElement(bt.obj().buyTab);

			if (checkForElementToBeVisible(bt.obj().searchPropertyHP, 10000))
				clickElement(bt.obj().searchPropertyHP);

			if (waitForElementInVisibility(bt.obj().burgerMenuBtn, 5000)) {
				if (checkForElementToBeVisible(bt.obj().searchProperty, 10000)) {
					System.out.println("Search property to buy, detailed search page displayed.");
					saveScreenshotPNG(driver());
					return true;
				} else {
					System.out.println("Failed to open search field.");
					return false;
				}
			} else {
				System.out.println("Failed to open search field.");
				return false;
			}
		}
		else {
			System.out.println("Buy Tab isn't available.");
			return false;
		}
	}
	
	/**
	 * openBuySearchContext - Usage is to perform a property search with different kind of search criterias
	 * @param cityOFChoice - {String} city where user wants to look for a property
	 * @param localitiesList - {String[]} an array of all localities, user wishes to add while looking up 
	 * @param includeNearByProperties - {boolean} true to include nearby property, false otherwise
	 * @param numberofBedroom - { String[] } accepts roomone, bhkone, bhktwo, bhkthree,  bhkfour and plusbhkfour as array of inputs to select corresponding bedrooms
	 * @return - {boolean}  true if able to execute and obtain properties list, false otherwise
	 * @throws Exception
	 */
	@Step ("Searching for a property, in city : \"{0}\"")
	public boolean searchForAProperty ( String cityOFChoice, String[] localitiesList , boolean includeNearByProperties, String[] numberofBedroom) throws Exception {
		
		if (checkForElementToBeVisible(bt.obj().preSelectedLocaiton, 10000)) {

			if (getText(bt.obj().preSelectedLocaiton).trim().equalsIgnoreCase(cityOFChoice))
				System.out.println("City of choice is selected by default.");
			else {
				clickElement(bt.obj().preSelectedLocaiton);
				swipeVertical(0.5, 0.4, 0.6, 500);
				swipeVertical(0.5, 0.4, 0.6, 500);

				By coc = bt.returnDynamicLocator(bt.obj().cityOfChoice, new String[] { cityOFChoice }, "xpath");
				if (checkForElementToBeVisible(coc, 10000)) {
					clickElement(coc);

					if (waitForElementToContainText(bt.obj().preSelectedLocaiton, cityOFChoice, 5000))
						System.out.println("city of choice selected.");
					else {
						System.out.println("failed to select city of choice from the available list.");
						return false;
					}
				}
			}
			saveScreenshotPNG(driver());
			Point   nearByLocalityCBPoint  = driver().findElement(bt.obj().includeNearByPropertiesCB).getLocation();
			String isLocalitySelected  = getAttribute(bt.obj().includeNearByPropertiesCB, "checked");
			
			// Select localities
			int addedLocationCounter = 0;
			for (String locality : localitiesList) {
				if (checkForElementToBeVisible(bt.obj().addLocalitySearchBox, 10000)) {
					enterText(bt.obj().addLocalitySearchBox, locality, true);

					// In case we don't want a hard wait here..... there are multiple ways of achieing so, one is to use background color 
					// at the co-ordinate of CB. Since if a result appers will overlay the CB the color will be differnet from the other case.
					// Since this is only a demo TC, im skipping this to save time, otherwise in regular scenarios such validation will become must 
					// and should never be skipped.
					Thread.sleep(2000); // wait for the results to populate..
					
					clickByCorodinate(nearByLocalityCBPoint);
					if (getAttribute(bt.obj().includeNearByPropertiesCB, "checked").equalsIgnoreCase(isLocalitySelected)) {
						System.out.println("Locality Selected.");
					}
					else {
						System.out.println("Localities didn't populate within time.");
						clickByCorodinate(nearByLocalityCBPoint);
						continue;
					}
					addedLocationCounter++; // increase the locations added by 1
					List<WebElement> addedLocations = driver().findElements(bt.obj().addedLocations);

					Loop1: if (addedLocations.size() == addedLocationCounter) {
						for (int i = 0; i < addedLocations.size() ; i++) {
							if (addedLocations.get(i).getText().contains(locality)) {
								System.out.println("Locality added is :" + locality);
								break Loop1;
							}
							if (i == addedLocations.size() - 1) {
								System.out.println("Location details not available for location name :" + locality);
								addedLocationCounter--;
								break Loop1;
							}
						}
					}

				}
			}
			
				if(!getAttribute(bt.obj().includeNearByPropertiesCB, "checked").equalsIgnoreCase(includeNearByProperties+"")) {
					clickElement(bt.obj().includeNearByPropertiesCB);
				
					if(getAttribute(bt.obj().includeNearByPropertiesCB, "checked").equalsIgnoreCase(includeNearByProperties+"")) {
						System.out.println("Clicked succesfully on include nearby properties.");
					}
				}else {
					System.out.println("Include nearby properties is already in desired state.");
				}
				
				if (getAttribute(bt.obj().includeNearByPropertiesCB, "checked").equalsIgnoreCase(true+"") & addedLocationCounter==0) {
					System.out.println("include nearBy localities is enabled. but no localities has been added. Disabling the inclusion.");
					clickByCorodinate(nearByLocalityCBPoint);
				}

				
				saveScreenshotPNG(driver());
				
				
				System.out.println(numberofBedroom.length);
				By[] bedlist = new By[numberofBedroom.length];
				for (int i=0; i< numberofBedroom.length; i++) {
						bedlist[i] = bt.returnDynamicLocator(bt.obj().numberOfBedRoomsTabs, new String[] {numberofBedroom[i].toLowerCase()}, "id");
				}
				
				int counter =0, resetCounter=0;
				Loop2: while (true) {
					counter = 0;
					swipeVertical(0.5, 0.6, 0.4, 500);
					for (By loc : bedlist) {
						if (driver().findElements(loc).size() > 0)
							counter++;
						else {
							continue Loop2;
						}
					}
					System.out.println(bedlist.length);
					if (counter == bedlist.length)
						break Loop2;
					if (resetCounter++ == 5) {
						System.out.println("Failed to bring bedrooms tab on screen.");
						return false;
					}
				}				
				
				
				for (By loc : bedlist) {
					clickElement(loc);
				}
				saveScreenshotPNG(driver());
				clickElement(bt.obj().searchProperty);
				if (checkForElementToBeVisible(bt.obj().allPropertyTitles, 12000)) {
					System.out.println("Successfully searched to by properties. List of available properties opened.");
					return true;
				}
				System.out.println("Failed to load the list of properties for given search criteria.");
				return false;
		}
		else {
			System.out.println("Buy a Property detailed search page not displayed.");
			return false;
		}
	}
	
	
	
	
}
