package com.scripts;

import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.CommonLibs;
import com.pages.HomePage;
import com.pages.HomePage.TestData;

import utils.DriverTestCase;
import utils.ExecutionLog;

public class HomeScript extends DriverTestCase{
	private String S_METHODNAME = "";
	private String S_LOGGINGINITIALCONSTANTVALUE = "ENTERING THE TEST METHOD :: ";
	private String S_LOGGINGEXITINGCONSTANTVALUE = "EXITING THE TEST METHOD :: ";
	private String className = this.getClass().getSimpleName();
	private String logFileName = "";
	private String logName = "";
	
	
	@BeforeTest(alwaysRun = true)
	public void setUpTestSuiteMethod() throws Exception {
		S_METHODNAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logName = className + "_" + S_METHODNAME;
		logFileName = ExecutionLog.createLogFile(logName);

		try {
			ExecutionLog.Log(logFileName, S_LOGGINGINITIALCONSTANTVALUE	+ S_METHODNAME);
			Assert.assertTrue(CommonLibs.openApplication(logFileName),	"Application Log In page load");
			/*Assert.assertTrue(CommonLibs.logInApplication(logFileName,
					CommonLibs.LogInDetails.userName1,
					CommonLibs.LogInDetails.password1, driver),
					"Application Log In");
			*/ExecutionLog.Log(logFileName, S_LOGGINGEXITINGCONSTANTVALUE	+ S_METHODNAME);
		} catch (Exception ex) {
			ExecutionLog.logAndCapture(logFileName, ex.toString(), true);
			throw ex;
		}
	}
	
	@Test
	public void homeTestCase01() throws Exception{
		
		S_METHODNAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logName = className + "_" + S_METHODNAME;
		logFileName = ExecutionLog.createLogFile(logName);

		try {
			ExecutionLog.Log(logFileName, S_LOGGINGINITIALCONSTANTVALUE	+ S_METHODNAME);
			
	//	String expectedTitle = HomePage.TestData.title;
	//	Assert.assertTrue(HomePage.verifyHomePagetitle(logFileName, HomePage.TestData.Anthemtitle, driver),"Home Page title matches");
		Assert.assertTrue(HomePage.verifyAnthemLogo(logFileName, driver),"Anthem Logo is present");
			Sleeper.sleepTight(5);
		/*CommonLibs.closeWindow(logFileName, driver);*/
		
		}
		catch (Exception ex) {
			ExecutionLog.logAndCapture(logFileName, ex.toString(), true);
			throw ex;
		}
}
	
	
	@Test
	public void homeTestCase02() throws Exception{
		
		S_METHODNAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logName = className + "_" + S_METHODNAME;
		logFileName = ExecutionLog.createLogFile(logName);

		try {
			ExecutionLog.Log(logFileName, S_LOGGINGINITIALCONSTANTVALUE
					+ S_METHODNAME);
			
		Assert.assertTrue(HomePage.verifyHeaderTabs(logFileName, HomePage.TestData.headertabs, driver),"header tab matches");
	//	Assert.assertTrue(HomePage.verifybottomtabs(logFileName, driver), "All tabs present");
		
		driver.close();
		}
		catch (Exception ex) {
			ExecutionLog.logAndCapture(logFileName, ex.toString(), true);
			throw ex;
		}
}
	
	/*@Test
	public void homeTestCase03() throws Exception{
		
		S_METHODNAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logName = className + "_" + S_METHODNAME;
		logFileName = ExecutionLog.createLogFile(logName);

		try {
			ExecutionLog.Log(logFileName, S_LOGGINGINITIALCONSTANTVALUE
					+ S_METHODNAME);
		
		}
		catch (Exception ex) {
			ExecutionLog.logAndCapture(logFileName, ex.toString(), true);
			throw ex;
		}
}*/
	
}