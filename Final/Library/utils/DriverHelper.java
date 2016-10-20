package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

public class DriverHelper extends DriverTestCase {
	
	public static WebDriver driver;
	private Selenium selenium;

	public DriverHelper(WebDriver webdriver) {
		driver = webdriver;		
		//selenium = new WebDriverBackedSelenium(driver, "http://ch.qa.guidance.com/");
	}
	

	/*public Selenium getSelenium(){
		return selenium;
	}*/
	
	public void Log(String logMsg){
		System.out.println(logMsg);
	}
	
	public static WebDriver getWebDriver() 
	{
		return driver;
	}

	//Handle locator type
	public By ByLocator(String locator) 
	{
		By result = null;

		if (locator.startsWith("//")) 
		{ result = By.xpath(locator); }
		else if (locator.startsWith("css=")) 
		{ result = By.cssSelector(locator.replace("css=", "")); } 
		else if (locator.startsWith("name=")) 
		{ result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) 
		{ result = By.linkText(locator.replace("link=", "")); } 
		else 
		{ result = By.id(locator); }
		return result;
	}

	//Assert element present
	public Boolean isElementPresent(String locator) 
	{
		Boolean result = false;
		try 
		{
			
			getWebDriver().findElement(ByLocator(locator));
			result = true;
			
		} 
		catch (Exception ex) { }
		
		return result;
	}
    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            driver.switchTo().alert().accept();            
            return true;
        }//try
        catch(Exception e){
            return false;
        }//catch
    }
    public void dragAndDrop(String loc_SourceElement, String loc_TargetElement)
    {
     Actions act = new Actions(getWebDriver());
     WebElement SourceElement = getWebDriver().findElement(ByLocator(loc_SourceElement)); 
     WebElement TargetElement = getWebDriver().findElement(ByLocator(loc_TargetElement));
     act.clickAndHold(SourceElement).moveToElement(TargetElement).pause(2000).release(TargetElement).build().perform();
    }
	public void WaitForElementPresent(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getValue(String locator){
		WaitForElementPresent(locator, 20);
		String text = getWebDriver().findElement(ByLocator(locator)).getAttribute("value");
		return text;
	}

	public void WaitForElementEnabled(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotEnabled(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void WaitForElementVisible(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator)).isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotVisible(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator)).isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void mouseOver(String locator){		
		this.WaitForElementPresent(locator, 20);		
		// find Assignments menu
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		
		//build and perform the mouseOver with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());    
		builder.moveToElement(el).build().perform();
	}
	
    public void dragAndDrop(String loc_SourceElement, String loc_TargetElement, String DashbaordTab)
	    {
	     Actions act = new Actions(getWebDriver());
	     WebElement SourceElement = getWebDriver().findElement(ByLocator(loc_SourceElement)); 
	     WebElement TargetElement = getWebDriver().findElement(ByLocator(loc_TargetElement));
	     WebElement SupportElement = getWebDriver().findElement(ByLocator(DashbaordTab));
	     act.clickAndHold(SourceElement).moveToElement(SupportElement).pause(2000).moveToElement(TargetElement).pause(2000).release(TargetElement).build().perform();
	    }
	
    public void clickOn(String locator)
	{		
    	this.WaitForElementPresent(locator, 20);;
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = getWebDriver().findElement(ByLocator(locator));	
		el.click();
		
	}
	
    public boolean isEnabled(String locator)
  	{		
    	boolean result = false;
		if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
			result = true;
		}
		return result;
  	}
  	
	public void doubleClick(String locator)
	{		
		this.WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = getWebDriver().findElement(ByLocator(locator));	
		Actions action = new Actions(driver);
		action.doubleClick(el).perform();
	}
	public void rightClick(String locator)
	{		
	
		WebElement el = getWebDriver().findElement(ByLocator(locator));	
		Actions action = new Actions(driver);
		action.contextClick(el).build().perform();
	}
	public void sendKeys(String locator, String value){
		
		this.WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator));
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(value);
	}
	
	public void selectFrame(String locator){
		
		this.WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator));
		getWebDriver().switchTo().frame(locator);
		
	}

	public void selectDropDown(String locator, String targetValue){ 
		Assert.assertTrue(isElementPresent(locator));
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator))).selectByVisibleText(targetValue);
		
    }
	
	public boolean isTextPresent(String locator, String str){		
		String message = getWebDriver().findElement(ByLocator(locator)).getText();
		if(message.contains(str)){return true;}
		else {	return false; }
	}
	
	public String getText(String locator){
		WaitForElementPresent(locator, 20);
		String text = getWebDriver().findElement(ByLocator(locator)).getText();	
		return text;
	}
	
	public void acceptAlert(){
		
		Alert alert = getWebDriver().switchTo().alert();
		alert.accept();
		
	}

	public void scrollIntoView(String locator){
		
		WebElement elem = getWebDriver().findElement(ByLocator(locator));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", new Object[] { elem });
	}


	public String getAttributeValue(String locator, String AttributeName){
		
		String AttributeValue = getWebDriver().findElement(ByLocator(locator)).getAttribute(AttributeName);
		return AttributeValue;
	}


	public boolean isDisplayed(String locator){
		
		boolean result = false;
		if (getWebDriver().findElement(ByLocator(locator)).isDisplayed()) {
			result = true;
		}
		return result;
	}
	
	public void waitForSpinnerInvisible(String locator) throws InterruptedException{		
		
		while(isDisplayed(locator))
		{
			Thread.sleep(2000);
		}
	}



public void pressEnterOnelement(String locator){
	
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ENTER);
	
}



public void pressCtrlPlusXOnelement(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.CONTROL + "x");
}
public void pressCtrlPlusVOnelement(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.CONTROL+ "v");
}

public void pressCtrlPlusCOnelement(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.CONTROL + "c");
}

public void pressBackspaceInacell(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.BACK_SPACE);
}

public void pressEscapekey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ESCAPE);
}

public void pressRightarrow(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ARROW_RIGHT);
}
public void pressLeftarrow(String locator){
	 getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ARROW_LEFT);
	}
public void pressUparrow(String locator){
	 getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ARROW_UP);
	}
public void pressDownarrow(String locator){
	 getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ARROW_DOWN);
	 
	}
public void pressTabKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.TAB);
}
public void pressHomekey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.HOME);
}
public void pressEndKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.END);
}
public void pressPageUpKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.PAGE_UP);
}
public void pressPageDownKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.PAGE_DOWN);
}
public void pressShiftPlusTabKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.TAB);
}

public void pressControlPlusHomeKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.CONTROL, Keys.HOME);
}

public void pressControlPlussEndKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.CONTROL, Keys.END);
}

public void pressshift(String locator){
	
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT);
		
}

public void pressShiftPlusUparrow(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.UP);
  }

public void pressShiftPlusDownArrowKey(String locator) {
     getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.ARROW_DOWN);
	
	/*Actions act=new Actions(getWebDriver());
	act.keyDown(Keys.SHIFT).keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN).keyUp(Keys.SHIFT).perform();*/
	
	
	 /* Robot robot = new Robot();	
	 robot.keyPress(KeyEvent.SHIFT_MASK);
	 robot.keyPress(KeyEvent.VK_DOWN);
	robot.keyPress(KeyEvent.SHIFT_MASK);
	 robot.keyRelease(KeyEvent.VK_DOWN);
	 
	 robot.keyRelease(KeyEvent.SHIFT_DOWN_MASK);*/
  }

public void pressShiftPlusLeftarrow(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.ARROW_LEFT);
	
}
public void pressShiftPlusRightarrow(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.ARROW_RIGHT);

}

public void pressShiftPlusHomeKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.HOME);
	
  }

public void pressShiftPlusEndKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.END);

}
public void pressShiftPlusPageUpKey(String locator){
		getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.PAGE_UP,Keys.PAGE_UP);
  }

public void pressShiftPlusPageDownKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.PAGE_DOWN,Keys.PAGE_DOWN);

     }

public void pressShiftPlusControlPlusLeftArrow(String locator){
		
		getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.CONTROL, Keys.LEFT);
			 	
	}
	
public void pressShiftPlusControlPlusRightArrow(String locator){
		
		getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.CONTROL, Keys.RIGHT);
		
   }

public void pressShiftPlusControlPlusHomeKey(String locator){
	
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.CONTROL, Keys.HOME);
    }

public void pressShiftPlusControlPlusEndKey(String locator){
	
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.CONTROL, Keys.END);
    }

public void pressShiftPlusControlPlusAltPlusHome(String locator){
	
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.CONTROL, Keys.ALT, Keys.HOME);
   }

public void pressShiftPlusControlPlusAltPlusEnd(String locator){
	
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.CONTROL, Keys.ALT, Keys.END);
   }

public void ClickAndDragMouseKey(String sourceloc, String targetloc){
	 Actions act=new Actions(getWebDriver());
	 WebElement SourceElement = getWebDriver().findElement(ByLocator(sourceloc)); 
	 WebElement TargetElement = getWebDriver().findElement(ByLocator(targetloc));
	 act.clickAndHold(SourceElement).moveToElement(TargetElement).release(TargetElement).build().perform();

	}

public void pressShiftPlusClickKey(String locator){
	 Actions act=new Actions(getWebDriver());
	 act.keyDown(Keys.SHIFT).click(driver.findElement(By.xpath(locator))).keyUp(Keys.SHIFT).perform();

	}

public void pressDeleteKey(String locator){
	getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.DELETE);
   }

public void ClickandControlAndDragMouseKey(String sourceloc, String targetloc){
	 Actions act=new Actions(getWebDriver());
	 WebElement SourceElement = getWebDriver().findElement(ByLocator(sourceloc)); 
	 WebElement TargetElement = getWebDriver().findElement(ByLocator(targetloc));  
	 act.keyDown(Keys.CONTROL).clickAndHold(SourceElement).moveToElement(TargetElement).release(TargetElement).keyUp(Keys.CONTROL).build().perform();

	}

public void ClickandShiftAndDragMouseKey(String sourceloc, String targetloc){
	 Actions act=new Actions(getWebDriver());
	 WebElement SourceElement = getWebDriver().findElement(ByLocator(sourceloc)); 
	 WebElement TargetElement = getWebDriver().findElement(ByLocator(targetloc));
	 act.keyDown(Keys.SHIFT).clickAndHold(SourceElement).moveToElement(TargetElement).release(TargetElement).keyUp(Keys.SHIFT).build().perform();

	}

public void ClickandShiftPlusControlAndDragMouseKey(String sourceloc, String targetloc){
	 Actions act=new Actions(getWebDriver());
	 WebElement SourceElement = getWebDriver().findElement(ByLocator(sourceloc)); 
	 WebElement TargetElement = getWebDriver().findElement(ByLocator(targetloc));
	 act.keyDown(Keys.SHIFT).keyDown(Keys.CONTROL).clickAndHold(SourceElement).moveToElement(TargetElement).release(TargetElement).keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();

	}

//method for destroying the range of cells and pressing ESC key

public void destoyselectionrange(String locator)
		
		{
			
			getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.ARROW_DOWN, Keys.ARROW_DOWN);
			getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ESCAPE);
		}
		
// Verifying the quarter cells
	
 public void assertSelectedQuarterCell(String locator)
	
	{
	
		Assert.assertTrue(getWebDriver().findElement(By.xpath(locator)).isDisplayed(), "Quarter row's are not selected");
	}
	
//Select the quarter cells and then delete the values
	
	public void selectdelete(String locator)
	
	{
		
		getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.SHIFT, Keys.ARROW_DOWN, Keys.ARROW_DOWN);
		
		getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.DELETE);
	
	}
	
	public void selectDropDownByTag(String locator, String targetValue){ 
		Assert.assertTrue(isElementPresent(locator));
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator))).selectByValue(targetValue);
				
    }
	
	public void deselectValueInList(String locator){ 
		Assert.assertTrue(isElementPresent(locator));
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator))).deselectAll();
				
    }
	
	// Verifying the selected cell till total using RGB value
	
	public void assertHeaderTotalCell(String locator)
	
	{
	
		Assert.assertTrue(getWebDriver().findElement(By.xpath(locator)).isDisplayed(), "Total row's are not selected");
		getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ESCAPE);
		
	}
	
			
	// Verifying the selected from locked cell using RGB value
		
		public void assertLockedCellSelection(String locator)
		
		{
		
			Assert.assertTrue(getWebDriver().findElement(By.xpath(locator)).isDisplayed(), "Total row's are not selected");
			getWebDriver().findElement(By.xpath(locator)).sendKeys(Keys.ESCAPE);
			
		}
		
		public boolean isCheckBoxSelected(String locator) 
		{
			
			if ( driver.findElement(ByLocator(locator)).isSelected() )
			{
				return true;
			}
			else{
			return false;
			}
		}
		
		public boolean isDisabled(String locator)
	  	{		
	    	boolean result = false;
			if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
				result = true;
			}
			return result;
	  	}

}
