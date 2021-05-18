package pages;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class PropertyDetailsPage  extends basePage{

	public PropertyDetailsPage(int waitTime) {
		super(waitTime);
	}
	
	
	
	public void selectPropertyByName() {
		
	}
	
	
	/**
	 * selectPropertyByIndex - Usage is to open a property present at a given index in the properties list of search result
	 * @param index - {int} the index of property which is to be selected
	 * @return - {boolean} true if able to open the property from list false otherwise.
	 * @throws Exception
	 */
	@Step ("Selecting the property present at index : \"{0}\"")
	public boolean selectPropertyByIndex(int index) throws Exception {

		LinkedHashSet<String> propertiesDetails = new LinkedHashSet<String>();
		System.out.println("Title\t\t\t\tPrice");

		if (checkForElementToBeVisible(bt.obj().allPropertyTitles, 10000)) {
			int counter = 0;
			while (true) {
				List<WebElement> allPropertyContainer = driver().findElements(bt.obj().allPropertyContainer);

				for (WebElement wE : allPropertyContainer) {

					String fullDetails = "";

					if (checkForElementToBeVisible(bt.obj().PropertyTitleinContainer, 1000)) {
						try {
							fullDetails = wE.findElement(bt.obj().PropertyTitleinContainer).getText();
							//	System.out.println(fullDetails);
							if (fullDetails.trim().equalsIgnoreCase("")) {
								break;
							}
						} catch (NoSuchElementException e) {
							if (counter++ == index * 4)
								return false;
							continue;
						}
						// System.out.println("Property Title is:"+fullDetails);
					}

					int counte1r = 0;
					while (true) {
						try {
							if (counte1r++ == 10)
								return false;
							wE.findElement(bt.obj().PropertyPriceinContainer);
							break;
						} catch (NoSuchElementException no) {
							// scroll if price not visible
							swipeVertical(0.5, 0.6, 0.4, 500);
						}
					}

					fullDetails = fullDetails + "#" + wE.findElement(bt.obj().PropertyPriceinContainer).getText();
					
					if(fullDetails.split("[#]")[0].trim().equalsIgnoreCase("")) {
						break;
					}
					if (!propertiesDetails.contains(fullDetails)) {
						System.out.println("=>" + fullDetails.split("[#]")[0] + "\t" + fullDetails.split("[#]")[1]);
						propertiesDetails.add(fullDetails);
					}
					
				}

				if (propertiesDetails.size() == index) {
					System.out.println("Required Property is on-screen");
					break;
				} else {
					swipeVertical(0.5, 0.6, 0.4, 500);
				}
				if (counter++ == index * 4) {
					System.out.println("Failed to find the required list of buildings.");
					return false;
				}

			}

			Iterator<String> iter = propertiesDetails.iterator();
			counter = 0;
			String propretyTitle = "";

			for (int i = 0; i < index; i++) {
				propretyTitle = iter.next().split("[#]")[0];
			}
			saveScreenshotPNG(driver());
			clickElement(bt.returnDynamicLocator(bt.obj().specificPropertyTitle, new String[] { propretyTitle }, "xpath"));

			if (checkForElementToBeVisible(bt.obj().openPropertyTitle, 10000)) {
				String openedProppertyTitle = driver().findElement(bt.obj().openPropertyTitle).getText().trim();
				if (propretyTitle.trim().equalsIgnoreCase(openedProppertyTitle.split(",")[0])) {
					System.out.println("Property at index :" + index + " is opened and validated");
					saveScreenshotPNG(driver());
					return true;
				} else {
					System.out.println("Some other property opened than the one expected");
					return false;
				}
			}
		}

		return true;

	}	

	

	
}
