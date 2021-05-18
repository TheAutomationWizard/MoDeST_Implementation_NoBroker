package pages;

import java.time.Duration;
import org.openqa.selenium.Point;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import modest.coreUtility.ElementsHandler;
import utility.baseTest;

@SuppressWarnings("rawtypes")
public class basePage  extends ElementsHandler {
	
	public basePage(int waitTime)
	{
		super(waitTime);
	}

	baseTest bt = new baseTest();
	ElementsHandler eH = new ElementsHandler();

	
	
	/**************************      NON-APPLICATION SPECIFIC BUSINESS LOGICS   
	 * @throws Exception ********************/
	
	
	/**
	 * grantPermissions - Usage is to allow different kind of permissions required during app launch.
	 * @return {boolean} true/false whether able to grant all access , false if fails to land on app homepage.
	 * @throws Exception
	 */
	@Step ("Grants Permission after app launch")	
	public boolean grantPermissions() throws Exception {
		
		if(checkForElementToBeVisible(bt.obj().privacyPolicy, 10000)) {
			clickElement(bt.obj().privacyPolicyContinueBtn);
		   System.out.println("privacy policy accepted.");
		}
		if(checkForElementToBeVisible(bt.obj().accessAlerts, 10000)) {
			clickElement(bt.obj().allowAccess);
			System.out.println("contacts/other access granted.");
		}
		if(checkForElementToBeVisible(bt.obj().accessAlerts, 10000)) {
			clickElement(bt.obj().allowAccess);
			 System.out.println("storage/others access granted.");
		}
		
		if(checkForElementToBeVisible(bt.obj().accessAlerts, 10000)) {
			clickElement(bt.obj().allowLocOnlyWhileUsingApp);
			 System.out.println("location access allowed only while app in use.");
		}

		if(checkForElementToBeVisible(bt.obj().noBrokerLogo, 10000)) {
			System.out.println("HomePage displayed succesfully. Access granted succesfully.");
			saveScreenshotPNG(driver());
			return true;
		}
		return false;
	}
	
	
	/**
	 * clickByCorodinate - Click on screen at a particular coordinate
	 * @param point
	 */
	
	public void clickByCorodinate (Point point) {
		new TouchAction(driver())
        .press(PointOption.point(point.getX(),point.getY()))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(20)))
        .release().perform();
	}
	
	
}
