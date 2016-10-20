package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtil {	/**
	 * This method will locate the element on webpage with its Class Name. Here
	 * locator is class name of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	 static String parentId;
	 static String childId;
	 
	 
	 public static WebElement findWebElement (String locator, WebDriver driver){
		
		 WebElement element = null;
		 if (locator.startsWith("class=")){
			 locator=locator.replace("class=", "");
			 element = findElementByClassName(locator, driver);			 
		 }else if (locator.startsWith("id=")){
			 locator=locator.replace("id=", "");
			 element = findElementByID(locator, driver);
		 }else if (locator.startsWith("css=")){
			 locator=locator.replace("css=", "");
			 element = findElementByCssSelector(locator, driver);
		 }else if (locator.startsWith("linkText=")){
			 locator=locator.replace("linkText=", "");
			 element = findElementByLinkText(locator, driver);
		 }else if (locator.startsWith("partialLinkText=")){
			 locator=locator.replace("partialLinkText=", "");
			 element = findElementByPartialLinkText(locator, driver);
		 }else if (locator.startsWith("name=")){
			 locator=locator.replace("name=", "");
			 element = findElementByName(locator, driver);		 
		 }else if (locator.startsWith("xpath=")){
			 locator=locator.replace("xpath=", "");
			 element = findElementByXpath(locator, driver);
		 }else if (locator.startsWith("tagName=")){
			 locator=locator.replace("tagName=", "");
			 element = findElementByTagName(locator, driver);
		 }else{
			 System.out.println("Can not find element using locator - " + locator );
			 throw new WebDriverException("Incorrect Locator Format");
		 }
		return element;
			 
	 }
	 
	public static WebElement findElementByClassName(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByClassName"));
			throw new WebDriverException("Incorrect ClassName or Webdriver");
		}
		WebElement webElement = driver.findElement(By.className(locator));
		return webElement;

	}

	/**
	 * This method will locate the element on webpage with its ID . Here locator
	 * is ID of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static WebElement findElementByID(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByID"));
			throw new WebDriverException("Incorrect Id or Webdriver");
		}
		WebElement webElement = driver.findElement(By.id(locator));
		return webElement;

	}

	/**
	 * This method will locate the element on webpage with its CSS. Here locator
	 * is CSS of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static WebElement findElementByCssSelector(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByCssSelector"));
			throw new WebDriverException("Incorrect CssSelector or Webdriver");
		}
		WebElement webElement = driver.findElement(By.cssSelector(locator));
		return webElement;

	}

	/**
	 * This method will locate the element on webpage with its Link text. Here
	 * locator is Link text of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */

	public static WebElement findElementByLinkText(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByLinkText"));
			throw new WebDriverException("Incorrect LinkText or Webdriver");
		}
		WebElement webElement = driver.findElement(By.linkText(locator));
		return webElement;

	}
	
	/**
	 * This method will locate the element on webpage with its Partial Link text. Here
	 * locator is Partial Link text of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */

	public static WebElement findElementByPartialLinkText(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByLinkText"));
			throw new WebDriverException("Incorrect PartialLinkText or Webdriver");
		}
		WebElement webElement = driver.findElement(By.partialLinkText(locator));
		return webElement;

	}

	
	
	/**
	 * This method will locate the element on webpage with its Name. Here
	 * locator is name of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static WebElement findElementByName(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByName"));
			throw new WebDriverException("Incorrect Name or Webdriver");
		}
		WebElement webElement = driver.findElement(By.name(locator));
		return webElement;

	}

	/**
	 * This method will locate the element on webpage with its XPATH. Here
	 * locator is XPATH of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static WebElement findElementByXpath(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByXpath"));
			throw new WebDriverException("Incorrect Xpath or Webdriver");
		}
		WebElement webElement = driver.findElement(By.xpath(locator));
		return webElement;

	}

	/**
	 * This method will locate the element on webpage with its Tag Name. Here
	 * locator is tag name of webelement.it returns the list of element
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static List<WebElement> findElementsByTagName(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementsByTagName"));
			throw new WebDriverException("Incorrect TagName or Webdriver");
		}
		List<WebElement> webElements = driver.findElements(By.tagName(locator));
		return webElements;

	}
	
	public static List<WebElement> findWebElements(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementsByTagName"));
			throw new WebDriverException("Incorrect TagName or Webdriver");
		}
		List<WebElement> webElements = new ArrayList<>();
		
		 
		 if (locator.startsWith("class=")){
			 locator=locator.replace("class=", "");
			 webElements = driver.findElements(By.className(locator));	 
		 }else if (locator.startsWith("id=")){
			 locator=locator.replace("id=", "");
			 webElements = driver.findElements(By.id(locator));	
		 }else if (locator.startsWith("css=")){
			 locator=locator.replace("css=", "");
			 webElements = driver.findElements(By.cssSelector(locator));	
		 }else if (locator.startsWith("linkText=")){
			 locator=locator.replace("linkText=", "");
			 webElements = driver.findElements(By.linkText(locator));	
		 }else if (locator.startsWith("partialLinkText=")){
			 locator=locator.replace("partialLinkText=", "");
			 webElements = driver.findElements(By.partialLinkText(locator));	
		 }else if (locator.startsWith("name=")){
			 locator=locator.replace("name=", "");
			 webElements = driver.findElements(By.name(locator));		 
		 }else if (locator.startsWith("xpath=")){
			 locator=locator.replace("xpath=", "");
			 webElements = driver.findElements(By.xpath(locator));	
		 }else if (locator.startsWith("tagName=")){
			 locator=locator.replace("tagName=", "");
			 webElements = driver.findElements(By.tagName(locator));	
		 }else{
			 System.out.println("Can not find element using locator - " + locator );
			 throw new WebDriverException("Incorrect Locator Format");
		 }
		
		return webElements;

	}

	/**
	 * This method will locate the element on webpage with its Tag Name. Here
	 * locator is tag name of webelement.
	 * 
	 * @param locator
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static WebElement findElementByTagName(String locator, WebDriver driver) throws WebDriverException
	{
		if (driver == null || (locator == null || locator == ""))
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver or Locator is NULL/EMPTY in method :findElementByTagName"));
			throw new WebDriverException("Incorrect TagName or Webdriver");
		}
		WebElement webElement = driver.findElement(By.tagName(locator));
		return webElement;

	}

	/**
	 * This method will perform the click operation on any clickable webelement.
	 * 
	 * @param webElement
	 * @throws WebDriverException
	 */
	public static void click(WebElement webElement) throws WebDriverException
	{
		if (webElement == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "webElement is NULL/EMPTY in method :click"));
			throw new WebDriverException("Incorrect webElement to click");
		}
		webElement.click();
	}

	/**
	 * This method will clear the textbox area of the webelement passed as
	 * parameter.
	 * 
	 * @param webElement
	 * @throws WebDriverException
	 */
	public static void clear(WebElement webElement) throws WebDriverException
	{
		if (webElement == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "webElement is NULL/EMPTY in method :clear"));
			throw new WebDriverException("Incorrect webElement to clear");
		}
		webElement.clear();
	}

	/**
	 * This method will type the msg provided as parameter on the textbox area
	 * of passed webElement.
	 * 
	 * @param webElement
	 * @param msg
	 * @throws WebDriverException
	 */
	public static void sendKeys(WebElement webElement, String msg) throws WebDriverException
	{
		if (webElement == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "WebElement is NULL/EMPTY in method :sendKeys"));
			throw new WebDriverException("Incorrect webElement for sendkeys");
		}
		webElement.sendKeys(msg);
	}

	/**
	 * This method will click on the cancel button of any popup alert message
	 * 
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void dismissAlert(WebDriver driver) throws WebDriverException
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	/**
	 * This method will click on the accept button of any popup alert message
	 * 
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void acceptAlert(WebDriver driver) throws WebDriverException
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	/**
	 * This method is used by webdriver for pressing the back button on browser.
	 * 
	 * @param driver
	 */
	public static void pressBackButton(WebDriver driver)
	{
		driver.navigate().back();
	}

	/**
	 * This method will read the content of alert box and return the message to
	 * calling method.
	 * 
	 * @param driver
	 * @return
	 * @throws WebDriverException
	 */
	public static String getAlertContents(WebDriver driver) throws WebDriverException
	{
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	/**
	 * This method will refresh the driver.
	 * 
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void refreshPage(WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :refreshPage"));
			throw new WebDriverException("Driver not properly initialized to Refresh page");
		}
		driver.navigate().refresh();
	}

	public static void waitForWebElement (String locator, int seconds, WebDriver driver){
		
		 if (locator.startsWith("class=")){
			 locator=locator.replace("class=", "");
			 waitForElementByClassName(locator, seconds, driver);			 
		 }else if (locator.startsWith("id=")){
			 locator=locator.replace("id=", "");
			 waitForElementById(locator, seconds, driver);
		 }else if (locator.startsWith("css=")){
			 locator=locator.replace("css=", "");
			 waitForElementByCssSelector(locator, seconds, driver);
		 }else if (locator.startsWith("linkText=")){
			 locator=locator.replace("linkText=", "");
			 waitForElementByLinkText(locator, seconds, driver);
		 }else if (locator.startsWith("partialLinkText=")){
			 locator=locator.replace("partialLinkText=", "");
			 waitForElementByPartialLink(locator, seconds, driver);
		 }else if (locator.startsWith("name=")){
			 locator=locator.replace("name=", "");
			 waitForElementByName(locator, seconds, driver);		 
		 }else if (locator.startsWith("xpath=")){
			 locator=locator.replace("xpath=", "");
			 waitForElementByXpath(locator, seconds, driver);
		 }else if (locator.startsWith("tagName=")){
			 locator=locator.replace("tagName=", "");
			 waitForElementByTagName(locator, seconds, driver);
		 }else{
			 System.out.println("Can not find element using locator - " + locator );
			 throw new WebDriverException("Incorrect Locator Format");
		 }
		
			 
	 }
	
	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its ClassName
	 * 
	 * @param ClassName
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void waitForElementByClassName(String name, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :WaitForElementByClassName"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (name == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "ClassName is NULL/EMPTY in method :WaitForElementByClassName"));
			throw new WebDriverException("Incorrect name to wait");
		}
		By locator = By.className(name);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its Id
	 * 
	 * @param id
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void waitForElementById(String id, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementById"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (id == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "ID is NULL/EMPTY in method :waitForElementById"));
			throw new WebDriverException("Incorrect id to wait");
		}
		By locator = By.id(id);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its CSS.
	 * 
	 * @param css
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */

	public static void waitForElementByCssSelector(String css, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementByCssSelector"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (css == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "CSS value is NULL/EMPTY in method :waitForElementByCssSelector"));
			throw new WebDriverException("Incorrect css to wait");
		}
		By locator = By.cssSelector(css);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its Linktext
	 * 
	 * @param linkText
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void waitForElementByLinkText(String linkText, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementByLinkText"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (linkText == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "LinkText value is NULL/EMPTY in method :waitForElementByLinkText"));
			throw new WebDriverException("Incorrect css to wait");
		}
		By locator = By.linkText(linkText);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its Name
	 * 
	 * @param name
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void waitForElementByName(String name, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementByName"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (name == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Name value is NULL/EMPTY in method :waitForElementByName"));
			throw new WebDriverException("Incorrect css to wait");
		}
		By locator = By.name(name);
		WebDriverWait wait = new WebDriverWait(driver, second);

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its XPATH
	 * 
	 * @param xpath
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */

	public static void waitForElementByXpath(String xpath, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementByXpath"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (xpath == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "XPATH is NULL/EMPTY in method :waitForElementByXpath"));
			throw new WebDriverException("Incorrect css to wait");
		}
		By locator = By.xpath(xpath);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its TagName
	 * 
	 * @param tagName
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void waitForElementByTagName(String tagName, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementByTagName"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (tagName == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "tagName is NULL/EMPTY in method :waitForElementByTagName"));
			throw new WebDriverException("Incorrect css to wait");
		}
		By locator = By.tagName(tagName);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * This method takes number of second to wait for a webelement to appear on
	 * Webpage with its PartialLink
	 * 
	 * @param partialLink
	 * @param second
	 * @param driver
	 * @throws WebDriverException
	 */
	public static void waitForElementByPartialLink(String partialLink, int second, WebDriver driver) throws WebDriverException
	{
		if (driver == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "Driver is NULL/EMPTY in method :waitForElementByPartialLink"));
			throw new WebDriverException("Driver not properly initialized");
		}
		if (partialLink == null)
		{
			//TestLogger.log(new ErrorEvent(WebDriver.class, "PartialLink is NULL/EMPTY in method :waitForElementByPartialLink"));
			throw new WebDriverException("Incorrect css to wait");
		}
		By locator = By.partialLinkText(partialLink);
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * This method will wait for alert box for number of seconds passed as
	 * parameter.
	 * 
	 * @param driver
	 * @param seconds
	 * @return
	 */
	public static boolean waitForAlert(WebDriver driver, int seconds)
	{
		boolean isAlertFound = false;
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null)
		{
			isAlertFound = true;
		}
		return isAlertFound;
	}

	/**
	 * This method will wait for Javascript to be executed for number of seconds
	 * passed as parameter.
	 * 
	 * @param driver
	 * @throws WebDriverException
	 */

	public static void waitForJavaScript(WebDriver driver) throws WebDriverException
	{
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>()
				{
			public Boolean apply(WebDriver driver)
			{
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
				};
				try
				{
					Thread.sleep(2000);
					WebDriverWait wait = new WebDriverWait(driver, 20);
					Thread.sleep(2000);
					wait.until(pageLoadCondition);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}

	}

	/**
	 * This method maximized the window size.
	 * 
	 * @param driver
	 */

	public static void windowMaximizeAndFocus(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	/**
	 * This method take Id of dropdown box and index number of element to be
	 * selected.
	 * 
	 * @param index
	 * @param driver
	 * @param id
	 * @throws WebDriverException
	 */
	public static void SelectfromDropDown(int index, WebDriver driver, String id) throws WebDriverException
	{
		if (driver == null)
		{
			throw new WebDriverException("Driver not properly initialized");
		}
		Select dropDown = new Select(findElementByID(id, driver));
		dropDown.selectByIndex(index);
	}

	/**
	 * This method take Id of dropdown box and text values of element to be
	 * selected.
	 * 
	 * @param optionText
	 * @param driver
	 * @param id
	 * @throws WebDriverException
	 */

	public static void SelectfromDropDownVisibleText(String optionText, WebDriver driver, String locator) throws WebDriverException
	{
		if (driver == null)
		{
			throw new WebDriverException("Driver not properly initialized");
		}
//		Select dropDown = new Select(findElementByID(id, driver));
		Select dropDown = new Select(findWebElement(locator, driver));
		dropDown.selectByVisibleText(optionText);
	}

	/**
	 * This method check the checkbox for webelement for which Id is passed.
	 * 
	 * @param driver
	 * @param locator
	 * @throws WebDriverException
	 */
	public static void setCheckBox(WebDriver driver, String locator) throws WebDriverException
	{
		driver.findElement(By.id(locator)).click();
	}

	/**
	 * This method gives the keyboard command to select whole webpage i.e. Ctrl
	 * + A
	 * 
	 * @param driver
	 */
	public static void selectAll(WebDriver driver)
	{
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
	}

	/**
	 * This method gives the keyboard command to select area i.e. Ctrl + A
	 * 
	 * @param driver
	 */
	public static String select()
	{
		String select = Keys.chord(Keys.CONTROL, "a");
		return select;
	}

	/**
	 * This method gives the keyboard command to driver to copy text i.e. Ctrl +
	 * C
	 * 
	 * @return
	 */
	public static String copy()
	{
		String copy = Keys.chord(Keys.CONTROL, "c");
		return copy;
	}

	/**
	 * This method gives the keyboard command to driver to paste whole text i.e.
	 * Ctrl + V
	 * 
	 * @return
	 */
	public static String paste()
	{
		String paste = Keys.chord(Keys.CONTROL, "v");
		return paste;
	}

	/**
	 * This method gives the keyboard command to driver to press backspace
	 * 
	 * @param ele
	 * @param num
	 */
	public static void backspace(WebElement ele, int num)
	{
		for (int i = 0; i < num; i++)
		{
			ele.sendKeys(Keys.BACK_SPACE);
		}
	}

	/**
	 * This method will toggle the driver handle from main window to popup
	 * window.
	 * 
	 * @param driver
	 */
	public static void handlePopupWindow(WebDriver driver)
	{
		(new WebDriverWait(driver, 90)).until(new ExpectedCondition<Boolean>()
				{
			public Boolean apply(WebDriver d)
			{
				return d.getWindowHandles().size() > 1;
			}
				});

		for (String winHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
		}

	}

	/**
	 * This method returns the current window handle of the driver.
	 * 
	 * @param driver
	 * @return
	 */

	public static String getCurrentdriverHandler(WebDriver driver)
	{
		return (driver.getWindowHandle());
	}

	/**
	 * This method sorts the input list in ascending order and returns the
	 * sorted List
	 * 
	 * @param list
	 * @return list
	 */
	public static List<String> sortList(List<String> list)
	{
		Collections.sort(list);
		return list;
	}


	/**
	 * Selects values from drop down using index, found on basis of Xpath
	 * @param index
	 * @param driver
	 * @param Xpath
	 * @throws WebDriverException
	 */
	public static void SelectfromDropDownByXpath(int index, WebDriver driver, String Xpath)
			throws WebDriverException
			{
		if (driver == null)
		{
			throw new WebDriverException("Driver not properly initialized");
		}
		Select dropDown = new Select(findElementByXpath(Xpath, driver));
		dropDown.selectByIndex(index);
			}

	public static void SelectfromDropDownByXpPathUsingValue(String value, WebDriver driver, String Xpath)
			throws WebDriverException
	{
		if (driver == null)
		{
			throw new WebDriverException("Driver not properly initialized");
		}
		Select dropDown = new Select(findElementByXpath(Xpath, driver));
		dropDown.selectByValue(value);
	}
	/**
	 * Selects from drop down by visible text found on basis of Xpath
	 * @param optionText
	 * @param driver
	 * @param Xpath
	 * @throws WebDriverException
	 */
	public static void SelectfromDropDownVisibleTextByXpath(String optionText,
			WebDriver driver, String Xpath) throws WebDriverException
			{
		if (driver == null)
		{
			throw new WebDriverException("Driver not properly initialized");
		}
		Select dropDown = new Select(findElementByXpath(Xpath, driver));
		dropDown.selectByVisibleText(optionText);
			}

	/**
	 * This method always generate and return a unique number to the calling
	 * method. It generate a new number from current date. It appends Date,
	 * Month, year, Hour, minutes and seconds.
	 * 
	 * @return
	 */
	public static String generateUUID()
	{
		DateFormat dateFormat = new SimpleDateFormat("MddyyyyHHmmss");
		Date today = Calendar.getInstance().getTime();
		String UUID = dateFormat.format(today);
		return UUID;
}
	public static void dateSelectFrom(String Calander, String Datepicker ,WebDriver driver) throws AWTException
	{
		 
		if (driver == null)
		{
			throw new WebDriverException("Driver not properly initialized");
		}
		Sleeper.sleepTightInSeconds(2);
		System.out.println(driver);
		parentId=driver.getWindowHandle();
		WebElement date = driver.findElement(By.xpath(Calander));
		
		ExecutionLog.Log("Calander image click");
		date.click();
		
		
		for(String winHandle : driver.getWindowHandles()){
			childId=winHandle;
	        driver.switchTo().window(winHandle);}
		
		
		
		Sleeper.sleepTightInSeconds(7);
		WebdriverUtil.findElementByXpath(Datepicker, driver).click();
		ExecutionLog.Log("Date clicked");
		Sleeper.sleepTightInSeconds(3);

		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
	    Sleeper.sleepTightInSeconds(5);
	}
	public static void dateSelectTo(String ToCalender, String dateTo, WebDriver driver) throws AWTException
	{
		
		
	
		driver.switchTo().window(parentId);
		driver.getWindowHandles().remove(childId);
		//XPATH of 
		WebdriverUtil.findElementByXpath(ToCalender, driver).click();
		
		Sleeper.sleepTightInSeconds(5);
		for(String winHandle : driver.getWindowHandles()){
	        driver.switchTo().window(winHandle);}
		
		Sleeper.sleepTightInSeconds(4);
		WebdriverUtil.findElementByXpath(dateTo, driver).click();
		
		Sleeper.sleepTightInSeconds(3);

		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    Sleeper.sleepTightInSeconds(2);
	}
	
	public static void dateSelect(String xpathCalendar,String xpathDate,WebDriver driver) throws AWTException
	{
		Sleeper.sleepTightInSeconds(2);
		String parentId=driver.getWindowHandle();
		WebElement date = driver.findElement(By.xpath(xpathCalendar));
		//.//*[@id='calendar']"
		date.click();
		WebdriverUtil.handlePopupWindow(driver);

		String childId=driver.getWindowHandle();
		WebdriverUtil.waitForElementByXpath(xpathDate, 30, driver);
		WebdriverUtil.findElementByXpath(xpathDate, driver).click();
		driver.getWindowHandles().remove(childId);
		driver.switchTo().window(parentId);

		Sleeper.sleepTightInSeconds(5);

		/*driver.manage().window().maximize();
		Sleeper.sleepTightInSeconds(8);

			try
			{
				((JavascriptExecutor) driver)
				.executeScript("return javascript:winMain.document.getElementById('date').value='23-Jul-2015';;window.close();");


				System.out.println(WebdriverUtil.findElementByXpath("html/body/table[2]/tbody/tr/td[2]/table/tbody/form/tr/td/table[2]/tbody/tr/td", driver).getText());
			//WebdriverUtil.findElementByXpath(xpathDate, driver).click();
			//"html/body/form/table/tbody/tr[6]/td[2]/font/a"
			Sleeper.sleepTightInSeconds(3);
			System.out.println("========" + driver.getTitle());
			driver.close();
			Sleeper.sleepTightInSeconds(3);
			System.out.println("========" + driver.getTitle());
			if(driver.getPageSource().isEmpty())
			{
				System.out.println("inside inner try + page src empty");
				throw new Exception();
			}
			}catch(Exception ex)
			{

				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ENTER);
				Sleeper.sleepTightInSeconds(1);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Sleeper.sleepTightInSeconds(2);

				robot.keyPress(KeyEvent.VK_ENTER);
				Sleeper.sleepTightInSeconds(1);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Sleeper.sleepTightInSeconds(2);
				//driver.close();
			}*/
	}
	public static boolean checkDropdowninAmer(String dropDownList[],String xpath,WebDriver driver){

		//To check dropdown--

		try{

			//   String[] exp = dropDownList;
			WebElement dropdown = driver.findElement(By.xpath(xpath));  
			Select select = new Select(dropdown);  
			int checkElement=0;
			System.out.println(dropDownList.length);
			List<WebElement> options = select.getOptions(); 
			System.out.println(options.size());
			for(WebElement we:options)  
			{  
				for (int i=0; i<dropDownList.length; i++)
				{
					if (we.getText().equals(dropDownList[i]))
					{
						System.out.println(we.getText()+" equals "+dropDownList[i]);
						System.out.println("Matched"+i);
						System.out.println("CE"+checkElement);
						checkElement++;


					} 
				}
			}
			if(checkElement>dropDownList.length)
			{
				System.out.println("some error");
				return false;
			}
			else{
				return true;
			}


		}catch(Exception ex)
		{

			throw ex;
		}


	}

	public static boolean checkInvalidCard(String cardNum,String xpath, WebDriver driver)
	{
		if(WebdriverUtil.findElementByXpath(xpath, driver).getText().equalsIgnoreCase("'"+cardNum+"' is an invalid card."))
			return true;
		else
			return false;
	}


	public static String mandatoryFieldValidationAlertTextCapture(WebDriver driver)
	{
		String alertText="";
		Boolean check=WebdriverUtil.waitForAlert(driver, 10);
		if(check==true)
		{	
			alertText= WebdriverUtil.getAlertContents(driver);
		}
		return alertText;
	}


	public static boolean chkIsAlertValueCorrect(String alertText,String alertValueExpected,WebDriver driver) 
	{
		if(alertText.contains(alertValueExpected)) 
		{
			Sleeper.sleepTightInSeconds(4);
			WebdriverUtil.acceptAlert(driver);
			ExecutionLog.Log("Alert text matches");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void SelctDateinCalendar(String Date,WebDriver driver,String calenderimgXpath)
	{
		//For Date selection
		Calendar now = Calendar.getInstance();
		System.out.println(now.getTime().getDate());
		
		List<WebElement> calendarDate=driver.findElements(By.xpath("//*[@class='ui-state-default']"));
		if(Date.contains("/"))
		{
			Date=Date.replaceAll("/", "");
		}
		for(WebElement checkDate : calendarDate)
		{
			String dateVal=checkDate.getText().trim();
			if(dateVal.equalsIgnoreCase(Date.trim()))
			{
				System.out.println("done");
				checkDate.click();
				Sleeper.sleepTightInSeconds(5);

				break;
			
			}
		}
		}
		public static void SelctTodayDateinCalendar(String Date,WebDriver driver,String calenderimgXpath)
		{
			//For Date selection
			System.out.println("today date");
			Calendar now = Calendar.getInstance();
			System.out.println(now.getTime().getDate());
			WebdriverUtil.waitForElementByXpath("//*[contains(@class,'ui-state-hi')]", 40, driver);
			WebdriverUtil.findElementByXpath("//*[contains(@class,'ui-state-hi')]", driver).click();
			/*List<WebElement> calendarDate=driver.findElements(By.className("ui-state-default ui-state-highlight"));
			if(Date.contains("/"))
			{
				Date=Date.replaceAll("/", "");
			}
			for(WebElement checkDate : calendarDate)
			{
				String dateVal=checkDate.getText().trim();
				if(dateVal.equalsIgnoreCase(Date.trim()))
				{
					System.out.println("done");
					checkDate.click();
					Sleeper.sleepTightInSeconds(5);

					break;
				
				}
				*/
			}
	
	
	
	public static void BackwardClickMonthIcon(int timesTobeClicked, WebDriver driver,String monthXpath)
	{
		timesTobeClicked=-1*timesTobeClicked;
		for (int i=0;i<timesTobeClicked;i++)
		{
		WebdriverUtil.findElementByXpath(monthXpath, driver).click();
		
		}
		Sleeper.sleepTightInSeconds(5);
	}
	
	public static void ForwardClickMonthIcon(int timesTobeClicked, WebDriver driver,String monthXpath)
	{
		for (int i=0;i<=timesTobeClicked;i++)
		{
		WebdriverUtil.findElementByXpath(monthXpath, driver).click();
		}
		Sleeper.sleepTightInSeconds(5);
	}
	public static boolean checkDefaulttext(String dropdownText, WebDriver driver,String xpath ){
		

	
			System.out.println(dropdownText);
			
			WebElement dropdown = driver.findElement(By.xpath(xpath));  
			Select select = new Select(dropdown);  
			
			List<WebElement> options = select.getOptions();  
			WebElement option = select.getFirstSelectedOption();
			System.out.println(option.getText());
			if(dropdownText.trim().equals(option.getText())){
				return true;
			}
			else{
				return false;
			}
		
		
	}
	public static boolean checkDropdowninAmer1(List<String>list,int noOfElements,WebDriver driver ,String xpath){
		
		//To check dropdown--
		
		try{
			
			//String[] exp = {TransactionType1,TransactionType2,TransactionType3}; 
			WebElement dropdown = driver.findElement(By.xpath(xpath));  
			Select select = new Select(dropdown);  
			int checkElement=0;
			List<WebElement> options = select.getOptions();  
			for(WebElement we:options)  
			{  
				for (int i=1; i<list.size(); i++){
					if (we.getText().equals(list.get(i))){
						System.out.println("Matched");
						checkElement++;
						
					} }}
					if(checkElement>3)
					{
					System.out.println("some error");
					return false;
					}
					else{
						return true;
					}
						
					
				}catch(Exception ex)
				{
					
					throw ex;
				}


			}
	
	
	public static String acceptTheAlert(WebDriver driver) 
	{
		String alerttext="";
		Boolean check=WebdriverUtil.waitForAlert(driver, 120);
		if(check==true)
		{
			driver.switchTo().alert(); 


			alerttext= WebdriverUtil.getAlertContents(driver);

			return alerttext;
		}
		return alerttext;
	}

	public static void uploadFile(String filenAME,WebDriver driver) throws Exception
	{
		WebElement x=WebdriverUtil.findElementByXpath("//*[@class='amx1 tableBorder']/tbody/tr[1]/td[2]/input", driver);
		x.sendKeys(filenAME);
		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
		Sleeper.sleepTightInSeconds(5);
	}
	
	public static String browserTitleTextCapture(WebDriver driver)
	{

		String browserTitle= driver.getTitle().trim();
//		System.out.println(browserTitle);
//		ExecutionLog.Log(browserTitle);
		return browserTitle;
	}
	
	public static boolean chkIsTitleValueCorrect(String browserTitle,String titleValueExpected) 
	{
//		if(browserTitle.contains(titleValueExpected)) 
			if(browserTitle.equals(titleValueExpected)) 
		{
			Sleeper.sleepTightInSeconds(4);
//			ExecutionLog.Log("Title text matches");
			return true;
		}
		else
		{
			return false;
		}

	}


	public static boolean isAlertValueMatching(String alertTextAUT,
			String alertTextExpectedValue,WebDriver driver) 
	{

		if(alertTextAUT.contains(alertTextExpectedValue))
		{

			ExecutionLog.Log("Alert Message on not Filling Transaction Date /Processing Date occured || Alert message verified");
			WebdriverUtil.acceptAlert(driver);
			return true;
		}
		else
		{
			return false;
		}
	}
}
