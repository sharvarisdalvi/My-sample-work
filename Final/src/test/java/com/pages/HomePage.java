package com.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.anthm.common.CommonLibs;

import utils.DriverHelper;
import utils.ExecutionLog;
import utils.ReadDataFromExcelSheet;
import utils.WebdriverUtil;
import utils.WebdriverUtil;

public class HomePage extends DriverHelper {
	
public HomePage(WebDriver driver){
	super(driver);
}

static String excelPath = CommonLibs.FilePaths.homeSuiteExcelPath;
static ReadDataFromExcelSheet reader = new ReadDataFromExcelSheet(System.getProperty("user.dir") + excelPath);

public static class OR {
	public static  String Companies = reader.getCellDataUsingParameter("OR", "Companies");
	public static  String Legal =  reader.getCellDataUsingParameter("OR","Legal");
	public static  String Privacy = reader.getCellDataUsingParameter("OR","Privacy");;
	public static  String Associates = reader.getCellDataUsingParameter("OR","Associates");;
	public static  String Suppliers = reader.getCellDataUsingParameter("OR","Suppliers");;
	public static  String SiteMap = reader.getCellDataUsingParameter("OR","SiteMap");;
	public static  String ContactUs = reader.getCellDataUsingParameter("OR","ContactUs");;
	public static String Anthemlogo = reader.getCellDataUsingParameter("OR", "Anthemlogo");
	public static String headerTabs=reader.getCellDataUsingParameter("OR", "headerTabs");
	public static String impvHealth=reader.getCellDataUsingParameter("OR", "impvHealth");
	public static String latestNews=reader.getCellDataUsingParameter("OR", "latestNews");
	public static String innovtionAthm=reader.getCellDataUsingParameter("OR", "innovtionAthm");
	public static String anthemTitle=reader.getCellDataUsingParameter("OR", "anthemTitle");
	}

public static class TestData{
	public static String Anthemtitle=reader.getCellDataUsingParameter("TestData", "Anthemtitle");
	public static String title=reader.getCellDataUsingParameter("TestData", "title");
	public static String headertabs=reader.getCellDataUsingParameter("TestData", "headertabs");
}


public static boolean verifyHomePagetitle(String fileName,String expectedTitle,WebDriver driver) throws WebDriverException, IOException{
	String actualTitle = WebdriverUtil.browserTitleTextCapture(driver);
	if (WebdriverUtil.chkIsTitleValueCorrect(actualTitle, expectedTitle)) {
		ExecutionLog.logAndCapture(fileName, "Browser Title " + actualTitle	+ " matches with expected - " + expectedTitle, false);
		return true;
	} else {
		ExecutionLog.logAndCapture(fileName, "Browser Title " + actualTitle	+ " does not matches with expected - " + expectedTitle, false);
		return false;
	}
}


public static boolean verifyHeaderTabs(String fileName,String expectedFields,WebDriver driver) throws WebDriverException, IOException{
	WebElement row = WebdriverUtil.findWebElement(OR.headerTabs, driver);
	List<WebElement> element=row.findElements(By.tagName("li"));
	List<String> expectedElements=new ArrayList<String>(Arrays.asList(expectedFields.split("_")));
	
	int counter=0;
	for(int i=0;i<element.size();i++){
		if(element.get(i).getText().trim().contains(expectedElements.get(i).trim())){
			ExecutionLog.logAndCapture(fileName, expectedElements.get(i) + " is present in the Tab", false);
			counter=counter+1;
		}else{
			ExecutionLog.logAndCapture(fileName, expectedElements.get(i) + " is not present in the Tab", true);
			return false;
		}
	}
		if(counter==element.size()){
			return true;
		}else{
			return false;
		}
	
	}	
 public static boolean verifybottomtabs(String fileName,WebDriver driver) throws WebDriverException, IOException{
	 
	 int counter=0;
	 if(WebdriverUtil.findWebElement(OR.Legal, driver).isDisplayed()){
		 ExecutionLog.logAndCapture(fileName, "Legal tab  is present", false);
		} else {
			ExecutionLog.logAndCapture(fileName, "Legal tab  is not present",
					true);
			counter = counter + 1;
		}
	 if(WebdriverUtil.findWebElement(OR.Privacy, driver).isDisplayed()){
		 ExecutionLog.logAndCapture(fileName, "Privacy tab is present", false);
		} else {
			ExecutionLog.logAndCapture(fileName, "Privacy tab is not present",
					true);
			counter = counter + 1;
		}
	 if(WebdriverUtil.findWebElement(OR.Associates, driver).isDisplayed()){
		 ExecutionLog.logAndCapture(fileName, "Associates tab  is present", false);
		} else {
			ExecutionLog.logAndCapture(fileName, "Associates tab  is not present",
					true);
			counter = counter + 1;
		}
	 
	 if(WebdriverUtil.findWebElement(OR.Suppliers, driver).isDisplayed()){
		 ExecutionLog.logAndCapture(fileName, "Suppliers tab is present", false);
		} else {
			ExecutionLog.logAndCapture(fileName, "Suppliers tab is not present",
					true);
			counter = counter + 1;
		}
	 if(WebdriverUtil.findWebElement(OR.SiteMap, driver).isDisplayed()){
		 ExecutionLog.logAndCapture(fileName, "SiteMap tab is present", false);
		} else {
			ExecutionLog.logAndCapture(fileName, "SiteMap tab  is not present",
					true);
			counter = counter + 1;
		}
	 
	 if(WebdriverUtil.findWebElement(OR.ContactUs, driver).isDisplayed()){
		 ExecutionLog.logAndCapture(fileName, "ContactUs is present", false);
		} else {
			ExecutionLog.logAndCapture(fileName, "ContactUs is not present",
					true);
			counter = counter + 1;
		}
	
	 
	 if(counter>0){
		 ExecutionLog.logAndCapture(fileName, counter	+ " no of elements not displayed", true);
			return false;
		} else {
			return true;
		}
	 
	 
 }


public static boolean verifyAnthemLogo(String fileName, WebDriver driver) throws WebDriverException, IOException {
	if(WebdriverUtil.findWebElement(OR.Anthemlogo, driver).isDisplayed()){
		ExecutionLog.logAndCapture(fileName,"Anthem  Application logo is present",false);
		return true;
	} else {
		ExecutionLog.logAndCapture(fileName,"Anthem Application logo is not present", true);
		return false;
	}
}

public static void verifyMousehoverdropdowns(String fileName,WebDriver driver ) throws WebDriverException, IOException{
	
if(WebdriverUtil.findWebElement(OR.headerTabs, driver).isDisplayed()){
		
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(OR.Companies));
		action.moveToElement(we).build().perform();
	
		}else{
			ExecutionLog.logAndCapture(fileName," locator is not present", true);
		}
		return;
	} 
	
	
	
} 
 
 
 









