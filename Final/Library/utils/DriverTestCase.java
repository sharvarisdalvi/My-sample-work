package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.anthm.common.CommonLibs;

public class DriverTestCase {
	enum WebBrowser 
	{ Firefox, IE, Chrome }

	public static String screenShotPath = CommonLibs.FilePaths.screenShotPath;

	public static  WebDriver driver;
	public PropertyReader propertyReader;
	//	public ReadDataFromExcelSheet reader=new ReadDataFromExcelSheet("//PlanningAndConsolidation//com//adaptiveinsight//Data//WCR DATA SHEET.xlsx");
	public static ReadDataFromExcelSheet reader=new ReadDataFromExcelSheet(System.getProperty("user.dir")+"\\src\\test\\java\\com\\common\\CommonDatasheet.xlsx");





	public static void setup(String fileName, String browser, String url) throws Exception{


		//		String driverType,url;
		//		   driverType=reader.getCellDataUsingParameter("LogInDetails", "browser");
		//		   url=reader.getCellDataUsingParameter("LogInDetails", "url"); 
		//		   System.out.println("Opening the url --"+ url);
		//		   ExecutionLog.Log("Opening the url --"+ url);
		//Check if desired browser is Firefox
		if (WebBrowser.Firefox.toString().equals(browser)) 
		{ 
			//FirefoxProfile firefoxProfile = new FirefoxProfile();
			//driver = new FirefoxDriver(firefoxProfile); 
			
			 DesiredCapabilities dc=new DesiredCapabilities();
			  dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);
			  driver =new FirefoxDriver(dc);
			 
//			ProfilesIni profile = new ProfilesIni();
//
//			FirefoxProfile myprofile = profile.getProfile("default");
//
//			driver = new FirefoxDriver(myprofile);
		} 		

		//Check if desired browser is Internet Explorer
		else if (WebBrowser.IE.toString().equals(browser)) 
		{ 
			//Set property for IEDriverServer
			String path = getPath();
			File file = new File(path+"//Drivers//IEDriverServer.exe");			
						DesiredCapabilities capabilities = new DesiredCapabilities();
						capabilities.setCapability("ignoreZoomSetting", true);
						capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());



//						WebDriver driver = new InternetExplorerDriver(capabilities);

			//Accept all SSL Certificates
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 
			//Create driver object
//			driver = new InternetExplorerDriver();
						driver = new InternetExplorerDriver(capabilities); 
		}

		//Check if desired browser is Chrome
		else if (WebBrowser.Chrome.toString().equals(browser)) 
		{   
			String path = getPath();

			System.setProperty("webdriver.chrome.driver",path+"//Drivers//chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			//		   ChromeOptions options = new ChromeOptions();
			//		   options.addArguments("test-type");
			//		   capabilities.setCapability("chrome.binary",path+"//Drivers//chromedriver.exe");
			//		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//		   driver = new ChromeDriver( options);

			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(capabilities);


		}
		//If browser type is not matched, consider it as Firefox
		else 
		{   FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxProfile); 
		}
		
		// Capturing System and browser details
		InetAddress ipAddr = InetAddress.getLocalHost();
		ExecutionLog.Log(fileName, "Host Machine Name : " + ipAddr.getHostName());
		ExecutionLog.Log(fileName, "Host Machine Address : " + ipAddr.getHostAddress());	
		ExecutionLog.Log(fileName, "Operating System : " + System.getProperty("os.name"));
		ExecutionLog.Log(fileName, "Architecture : " + System.getProperty("os.arch"));
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName();
		String version = cap.getVersion().toString();
		ExecutionLog.Log(fileName, "Browser : " + browserName + ", version : " + version);
		//Maximize window
		driver.manage().window().maximize();

		//Delete cookies
		driver.manage().deleteAllCookies();		
		driver.navigate().to(url);

	}	
	
	/*public static String getBrowserAndVersion(WebDriver driver) {
		String browser_version = null;
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browsername = cap.getBrowserName();
		// This block to find out IE Version number
		if ("internet explorer".equalsIgnoreCase(browsername)) {
			String uAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
			System.out.println(uAgent);
			//uAgent return as "MSIE 8.0 Windows" for IE8
			if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
				browser_version = uAgent.substring(uAgent.indexOf("MSIE")+5, uAgent.indexOf("Windows")-2);
			} else if (uAgent.contains("Trident/7.0")) {
				browser_version = "11.0";
			} else {
				browser_version = "0.0";
			}
		} else
		{
			//Browser version for Firefox and Chrome
			browser_version = cap.getVersion();// .split(".")[0];
		}
		String browserversion = browser_version.substring(0, browser_version.indexOf("."));
		return browsername + " " + browserversion;
	}

	public static String OSDetector () {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		}else if (os.contains("mac")) {
			return "Mac";
		}else if (os.contains("sunos")) {
			return "Solaris";
		}else {
			return "Other";
		}
	}*/



	public void setup(String filepath) throws Exception{

		propertyReader = new PropertyReader();	
		String driverType,url;
		//		   driverType=reader.getCellDataUsingParameter("LogInDetails", "browser");
		driverType=propertyReader.readApplicationFile("BROWSER",filepath);
		url=propertyReader.readApplicationFile("URL",filepath);
		//		   url=reader.getCellDataUsingParameter("LogInDetails", "url"); 
		System.out.println("Opening the url --"+ url);
		ExecutionLog.Log("Opening the url --"+ url);
		//Check if desired browser is Firefox
		if (WebBrowser.Firefox.toString().equals(driverType)) 
		{ 
			//FirefoxProfile firefoxProfile = new FirefoxProfile();
			//driver = new FirefoxDriver(firefoxProfile); 
			/*
			 DesiredCapabilities dc=new DesiredCapabilities();
			  dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);
			  driver =new FirefoxDriver(dc);
			 */
			ProfilesIni profile = new ProfilesIni();

			FirefoxProfile myprofile = profile.getProfile("default");

			driver = new FirefoxDriver(myprofile);
		} 		

		//Check if desired browser is Internet Explorer
		else if (WebBrowser.IE.toString().equals(driverType)) 
		{ 
			//Set property for IEDriverServer
			String path = getPath();
			File file = new File(path+"//Drivers//IEDriverServer.exe");

			//			DesiredCapabilities capabilities = new DesiredCapabilities();
			//			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			//			WebDriver driver = new InternetExplorerDriver(capabilities);

			//Accept all SSL Certificates
			/*DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 */
			//Create driver object
			driver = new InternetExplorerDriver();
			//			driver = new InternetExplorerDriver(capabilities); 
		}

		//Check if desired browser is Chrome
		else if (WebBrowser.Chrome.toString().equals(driverType)) 
		{   
			String path = getPath();

			System.setProperty("webdriver.chrome.driver",path+"//Drivers//chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capabilities.setCapability("chrome.binary",path+"//Drivers//chromedriver.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver( options);


		}
		//If browser type is not matched, consider it as Firefox
		else 
		{   FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxProfile); 
		}

		//Maximize window
		driver.manage().window().maximize();

		//Delete cookies
		driver.manage().deleteAllCookies();		
		driver.navigate().to(url);

	}	


	/*@AfterTest
	public void afterMainMethod() {		

		//driver.quit();
	}*/

	public static String getScreenShotName(String file, boolean fail)
	{
		java.util.Date date= new java.util.Date();
		String timeStamp = new java.sql.Timestamp(date.getTime()).toString().replace(":", ".");
		String fileName;
		if (fail){
			fileName = "FAIL-"+file +"_"+timeStamp;
		}else{
			fileName = "PASS-"+file +"_"+timeStamp;
		}
		return fileName;
	}

	public static void captureScreenshot(String fileName, boolean fail) throws WebDriverException, IOException 
	{

		String takeScreenshotForFail = reader.getCellDataUsingParameter("LogInDetails", "captureFailScreen");
		String takeScreenshotForPass = reader.getCellDataUsingParameter("LogInDetails", "capturePassScreen");
		if(fail){
			if(takeScreenshotForFail.equalsIgnoreCase("Y")){
				String trimmedName = fileName.substring(0, fileName.length()-24);
				String screenshotName = getScreenShotName(trimmedName,fail );
				FileOutputStream out = new FileOutputStream(screenShotPath+"//" + screenshotName + ".jpg");
				out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
				out.close();				
			}
		}else{
			if(takeScreenshotForPass.equalsIgnoreCase("Y")){
				String trimmedName = fileName.substring(0, fileName.length()-24);
				String screenshotName = getScreenShotName(trimmedName,fail );
				FileOutputStream out = new FileOutputStream(screenShotPath+"//" + screenshotName + ".jpg");
				out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
				out.close();				
			}
		}
	}


	/* public static void captureScreenshot(String fileName, boolean fail) throws WebDriverException, IOException 
		{

				String trimmedName = fileName.substring(0, fileName.length()-24);
				String screenshotName = getScreenShotName(trimmedName,fail );
		        FileOutputStream out = new FileOutputStream(screenShotPath+"//" + screenshotName + ".jpg");
		        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		        out.close();
//		        String path = getPath();
//		        String  screen = "file://"+path+"/"+screenShotPath+"/"+screenshotName + ".jpg";
//		        ExecutionLog.Log(fileName,"<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
//		        Reporter.log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
		     }*/



	public  static WebDriver getWebDriver(){
		return driver;
	}

	//Get absolute path
	public static String getPath()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	}
	//Capturing screenshot on failure 
	/*public void captureScreenshot(String fileName) 
	{
		try 
		{
			String screenshotName = this.getFileName(fileName);
	        FileOutputStream out = new FileOutputStream("screenshots//" + screenshotName + ".jpg");
	        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	        out.close();
	        String path = getPath();
	        String  screen = "file://"+path+"/screenshots/"+screenshotName + ".jpg";
	        Reporter.log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
	     }
		 catch (Exception e) 
		 { }
	 }*/

	//Creating file name
	public  String getFileName(String file)
	{
		java.util.Date date= new java.util.Date();
		String timeStamp = new java.sql.Timestamp(date.getTime()).toString().replace(":", ".");			
		String fileName = file +"_"+timeStamp;
		return fileName;
	}

	//This allows the URL to be passed in from a Command line. 
	public void setupUrlParm(String filepath) throws Exception{

		propertyReader = new PropertyReader();	
		String driverType,url;

		driverType=propertyReader.readApplicationFile("BROWSER",filepath);
		// Pulls the URL from a CMD Line
		//url=propertyReader.readApplicationFile("URL",filepath);
		url=System.getProperty("URL");



		//Check if desired browser is Firefox
		if (WebBrowser.Firefox.toString().equals(driverType)) 
		{ 

			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"//MIME types Of MS Excel File.
					+ "application/pdf;" //MIME types Of PDF File.
					+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" //MIME types Of MS doc File.
					+ "text/plain;" //MIME types Of text File.
					+ "text/csv"); //MIME types Of CSV File.

			// 0 = desktop, 1 = default download folder , 2 = user defined location.
			firefoxProfile.setPreference("browser.download.folderList",1);

			//driver = new FirefoxDriver(firefoxProfile); 
			//DesiredCapabilities dc=new DesiredCapabilities();
			// dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);

			driver =new FirefoxDriver(firefoxProfile);
		} 		

		//Check if desired browser is Internet Explorer
		else if (WebBrowser.IE.toString().equals(driverType)) 
		{ 
			//Set property for IEDriverServer
			String path = getPath();
			File file = new File(path+"//Drivers//IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			//Accept all SSL Certificates
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			//Create driver object
			driver = new InternetExplorerDriver(); 
		}

		//Check if desired browser is Chrome
		else if (WebBrowser.Chrome.toString().equals(driverType)) 
		{   
			String path = getPath();

			System.setProperty("webdriver.chrome.driver",path+"//Drivers//chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capabilities.setCapability("chrome.binary",path+"//Drivers//chromedriver.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver( options);
		}
		//If browser type is not matched, consider it as Firefox
		else 
		{   FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxProfile); 
		}

		//Maximize window
		driver.manage().window().maximize();

		//Delete cookies
		driver.manage().deleteAllCookies();		
		driver.navigate().to(url);


	}

}
