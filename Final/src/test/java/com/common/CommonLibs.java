package com.common;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverTestCase;
import utils.ExecutionLog;
import utils.ReadDataFromExcelSheet;
import utils.WebdriverUtil;

import com.anthm.common.CommonLibs.LogInDetails;
import com.anthm.pages.AnthmHomePage;
import com.anthm.pages.AnthmLogInPage;
import com.anthm.pages.AnthmLogInPage.OR;
import com.anthm.pages.AnthmLogInPage.TestData;

public class CommonLibs extends DriverTestCase {


	static ReadDataFromExcelSheet reader=new ReadDataFromExcelSheet(System.getProperty("user.dir")+"\\src\\test\\java\\com\\common\\CommonDatasheet.xlsx");

	public static class FilePaths {
		public static String executionLogPath = reader.getCellDataUsingParameter("FilePaths", "executionLogPath");
		public static String screenShotPath = reader.getCellDataUsingParameter("FilePaths", "screenShotPath");
		public static String logInSuiteExcelPath = reader.getCellDataUsingParameter("FilePaths", "logInSuiteExcelPath");
		public static String homeSuiteExcelPath = reader.getCellDataUsingParameter("FilePaths", "homeSuiteExcelPath");
		
	}

	public static class LogInDetails{
		public static String url = reader.getCellDataUsingParameter("LogInDetails", "url");
		public static String browser = reader.getCellDataUsingParameter("LogInDetails", "browser");
		public static String userName1 = reader.getCellDataUsingParameter("LogInDetails", "username1");
		public static String password1 = reader.getCellDataUsingParameter("LogInDetails", "password1");
			}


	public static void closeTab(String fileName,String tabLCloseLocator, WebDriver driver)
			throws WebDriverException, IOException {
//		driver.switchTo().defaultContent();
		WebElement closeIcon = WebdriverUtil.findWebElement(tabLCloseLocator,
				driver);
		WebdriverUtil.click(closeIcon);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		} catch (TimeoutException e) {

		}		
	}

	public static boolean openApplication (String logFileName) throws Exception{	
		setup(logFileName, LogInDetails.browser, LogInDetails.url);
		/*if (LogInDetails.browser.equals("IE")){
			if(driver.getTitle().contains("Certificate Error")){
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
		}*/
		if(WebdriverUtil.findWebElement(AnthmHomePage.OR.Anthemlogo, driver).isDisplayed()){
			System.out.println(WebdriverUtil.browserTitleTextCapture(driver));
			ExecutionLog.logAndCapture(logFileName, "Successfully opened URL - " +LogInDetails.url+"in "+LogInDetails.browser , false);
			return true;
		}else{
			ExecutionLog.logAndCapture(logFileName, "Failed to open URL - " +LogInDetails.url+"in "+LogInDetails.browser , true);
			return false;
		}
	}

	public static boolean logInApplication (String fileName, String username, String password, WebDriver driver) throws WebDriverException, IOException{
		AnthmLogInPage.enterCredentials(fileName, username, password, driver);
		Sleeper.sleepTight(5);
		return AnthmLogInPage.clickAndVerifyLogIn(fileName, driver);
	}

	public static String enterTextField (String fileName, String fieldLocator,String fieldLabel, String textToEnter, WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(fieldLocator, driver).isDisplayed()){
			ExecutionLog.logAndCapture(fileName, fieldLabel+" Field is Present", false);
			WebElement textField = WebdriverUtil.findWebElement(fieldLocator, driver);
			WebdriverUtil.clear(textField);
			WebdriverUtil.sendKeys(textField, textToEnter);
//			textField.sendKeys(Keys.TAB);
			ExecutionLog.logAndCapture(fileName, "'"+textToEnter+"' entered in "+fieldLabel+ " Field", false);
			return textField.getAttribute("value");
		}else{
			ExecutionLog.logAndCapture(fileName, fieldLabel+" Field is not Present", true);
			return null;
		}
	}


	public static boolean logOutApp (String fileName, WebDriver driver) throws WebDriverException, IOException{
		WebElement profileLink = WebdriverUtil.findWebElement(OR.profileLink, driver);
		WebdriverUtil.click(profileLink);
		Sleeper.sleepTightInSeconds(5);
		WebElement logOutLink = WebdriverUtil.findWebElement(OR.logOutLink, driver);
		WebdriverUtil.click(logOutLink);	
		Sleeper.sleepTightInSeconds(5);
		if(driver.getTitle().contains(TestData.LogOutPageTitle)){
			ExecutionLog.logAndCapture(fileName, "Successfully Logged Out from the Application", false);
			return true;
		}else{
			ExecutionLog.logAndCapture(fileName, "Failed to Log Out", true);
			return false;
		}			
	}

	
	public static void closeWindow(String fileName, WebDriver driver ){
		driver.quit();
			}

	
	public static void switchToFrame (String frameId, WebDriver driver){
		driver.switchTo().frame(frameId);
	}
	
	public static void switchToFrameByLocator (String frameLocator, WebDriver driver){
		driver.switchTo().frame(WebdriverUtil.findWebElement(frameLocator, driver));
	}

	public static boolean verifyErrorPopUp (String fileName, String expectedMessage, WebDriver driver) throws WebDriverException, IOException{
		String alertMessage = alertTextCapture(fileName, driver).trim();
		if (alertMessage.equals(expectedMessage.trim())){
			ExecutionLog.logAndCapture(fileName, "Proper error message is present : "+alertMessage, false);
			return true;
		}else{
			ExecutionLog.logAndCapture(fileName, "Proper error message is not present", true);
			return false;
		}
	}


	public static String alertTextCapture(String fileName,WebDriver driver) throws WebDriverException, IOException{
		String alertText="";
		Boolean check=WebdriverUtil.waitForAlert(driver, 10);
		if(check==true)
		{	
			alertText= WebdriverUtil.getAlertContents(driver);
			WebdriverUtil.acceptAlert(driver);
		}else{			
			ExecutionLog.logAndCapture(fileName, "Alert box not found!!!", true);
		}	
		return alertText;
	}

	public static String selectDropdown (String fileName, String dropDownLocator, String optionText, String dropdownName, WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(dropDownLocator, driver).isDisplayed()){
			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is present", false);
			Select dropdown = new Select(WebdriverUtil.findWebElement(dropDownLocator, driver));
			dropdown.selectByVisibleText(optionText);
			Sleeper.sleepTightInSeconds(2);	
//			String value = dropdown.getFirstSelectedOption().getText();
			ExecutionLog.logAndCapture(fileName, optionText+" - selected", false);
//			return value;
			return optionText;
		}else{
			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is not present", true);
			return null;
		}
	}
	
	
	public static String selectCheckbox (String fileName, String CheckboxLocator, String CheckboxName, WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(CheckboxLocator, driver).isDisplayed()){
			ExecutionLog.logAndCapture(fileName,CheckboxName+" CheckBox is present", false);
			WebElement checkbox = WebdriverUtil.findWebElement(CheckboxLocator, driver);
			String optionCheck = checkbox.getText();
			WebdriverUtil.click(checkbox);
			ExecutionLog.logAndCapture(fileName, optionCheck+" - selected", false);
			return optionCheck;
		
		}else{
			ExecutionLog.logAndCapture(fileName,CheckboxName+" CheckBox is not present", true);
			return null;
		}
	}

	public static String selectDropdownByIndex (String fileName, String dropDownLocator, int index, String dropdownName, WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(dropDownLocator, driver).isDisplayed()){
			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is present", false);
			Select dropdown = new Select(WebdriverUtil.findWebElement(dropDownLocator, driver));
			dropdown.selectByIndex(index);		 
			Sleeper.sleepTightInSeconds(2);			
			String value = dropdown.getFirstSelectedOption().getText();
			ExecutionLog.logAndCapture(fileName, "Option no : "+index+" value : "+value+" selected", false);
			return value;
		}else{
			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is not present", true);
		}
		return null;
	}
	
	public static String selectDropdown (String fileName, String dropDownLocator, String optionText, WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(dropDownLocator, driver).isDisplayed()){
//			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is present", false);
			Select dropdown = new Select(WebdriverUtil.findWebElement(dropDownLocator, driver));
			dropdown.selectByVisibleText(optionText);
//			String value = dropdown.getFirstSelectedOption().getText();
			ExecutionLog.logAndCapture(fileName, optionText+" - selected", false);
			Sleeper.sleepTightInSeconds(2);	
//			return value;	
			return optionText;
		}else{
//			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is not present", true);
			return null;
		}
	}

	public static String selectDropdownByIndex (String fileName, String dropDownLocator, int index, WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(dropDownLocator, driver).isDisplayed()){
//			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is present", false);
			Select dropdown = new Select(WebdriverUtil.findWebElement(dropDownLocator, driver));
			dropdown.selectByIndex(index);		 
			String value = dropdown.getFirstSelectedOption().getText();
			ExecutionLog.logAndCapture(fileName, "Option no : "+index+" value : "+value+" selected", false);
			Sleeper.sleepTightInSeconds(2);			
			return value;
		}else{
//			ExecutionLog.logAndCapture(fileName,dropdownName+" Dropdown is not present", true);
		}
		return null;
	}

//	public static void verifySpecificLogIn (String fileName, boolean EMEA, String userName, String pwd,  WebDriver driver) throws Exception{
//		String operatorLinkText = WebdriverUtil.findWebElement(AnthmOperatorProfilePage.OR.operatorLink, driver).getText().trim();
//		if (EMEA){
//			if (!operatorLinkText.contains("EMEA")){
//				if(driver!=null){
//					driver.close();
//					driver.quit();
//				}				
//				CommonLibs.openApplication(fileName);
//				CommonLibs.logInApplication(fileName, userName, pwd, driver);				
//			}			
//		}else{
//			if (!operatorLinkText.contains("JAPA")){
//				if(driver!=null){
//					driver.close();
//					driver.quit();
//				}				
//				CommonLibs.openApplication(fileName);
//				CommonLibs.logInApplication(fileName, userName, pwd, driver);				
//			}	
//		}
//	}

	
	
	public static String  mouseHoverElement(String fileName,String locator,WebDriver driver) throws WebDriverException, IOException{
		if(WebdriverUtil.findWebElement(locator, driver).isDisplayed()){
		
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(locator));
		action.moveToElement(we).build().perform();
	
		}else{
			ExecutionLog.logAndCapture(fileName,locator+" locator is not present", true);
		}
		return null;
	} 
}

