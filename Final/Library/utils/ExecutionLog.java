package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.anthm.common.CommonLibs;

public class ExecutionLog extends DriverTestCase{	

	
	public static String execLogPath = CommonLibs.FilePaths.executionLogPath;
	public static String screenShotPath = CommonLibs.FilePaths.screenShotPath;
	
	public  WebDriver driver;
	
/*	static //static String txt="<html><body>"+txt+"</html></body>";
	FileWriter	 fstream;
	static{
		 ExecutionLog executionLog = new ExecutionLog();	
		 String dateTime = executionLog.getDate();
		 String fileName = executionLog.getFileName();
		 try {
			  fstream = new FileWriter(System.getProperty("user.dir")+"\\ExecutionLog\\"+fileName+".txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void logAndCapture(String filename, String text, boolean fail) throws WebDriverException, IOException{
		DriverTestCase.captureScreenshot(filename, fail);
		Log(filename, text);		
	}
	
	public static String createLogFile(String testSuitName, WebDriver driver){
		ExecutionLog executionLog = new ExecutionLog();	
		String fileName = executionLog.getFileName(testSuitName, driver);		
		try {
			FileWriter fstream = new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static String createLogFile(String testSuitName){
		ExecutionLog executionLog = new ExecutionLog();	
		String fileName = executionLog.getFileName(testSuitName);		
		try {
			FileWriter fstream = new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	
	
	
	
	
	
	 public static void Log(String filename, String text)
	 {
		 Reporter.log(text);
		 ExecutionLog executionLog = new ExecutionLog();	
		 String dateTime = executionLog.getDate();
//		 String fileName = executionLog.getFileName();
		 try
		 {			 
		    // Create file 
		    

			FileWriter fstream = new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+filename+".txt",true);
				
			
		    
		    BufferedWriter out = new BufferedWriter(fstream);
		    text = dateTime +" [info]  "+ text;
		    out.write(text);
		    out.newLine();
		    
		    //Close the output stream
		    if(fstream !=null){
		    out.close();		    
		 }}
		 catch (Exception e)
		 { System.err.println("Error: " + e.getMessage()); }
		
		
	 }
	 
	 public static void Log(String text)
	 {
		 Reporter.log(text);
		 ExecutionLog executionLog = new ExecutionLog();	
		 String dateTime = executionLog.getDate();
		 String fileName = executionLog.getFileName();
		 try
		 {			 
		    // Create file 
		    

			FileWriter fstream = new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt",true);
				
			
		    
		    BufferedWriter out = new BufferedWriter(fstream);
		    text = dateTime +" [info]  "+ text;
		    out.write(text);
		    out.newLine();
		    
		    //Close the output stream
		    if(fstream !=null){
		    out.close();		    
		 }}
		 catch (Exception e)
		 { System.err.println("Error: " + e.getMessage()); }
		
		
	 }
	 
	 public static void LogExceptionMessage(String filename, Exception e) throws IOException
	 {
		 
		 Reporter.log(e.toString());
		 ExecutionLog executionLog = new ExecutionLog();
		 String dateTime = executionLog.getDate();
		 ExecutionLog.Log(filename, dateTime +" [info]  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error message >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		 
//		 String fileName = executionLog.getFileName();
		 PrintWriter pw;		
		 try 
		 {
			pw = new PrintWriter(new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+filename+".txt", true));
			e.printStackTrace(pw);
			pw.close();
		 } 
		 catch (FileNotFoundException e1)
		 { e1.printStackTrace(); }		
	 }
	 
	 public static void LogExceptionMessage(Exception e) throws IOException
	 {
		 
		 Reporter.log(e.toString());
		 ExecutionLog executionLog = new ExecutionLog();
		 String dateTime = executionLog.getDate();
		 ExecutionLog.Log(dateTime +" [info]  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error message >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		 
		 String fileName = executionLog.getFileName();
		 PrintWriter pw;		
		 try 
		 {
			pw = new PrintWriter(new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt", true));
			e.printStackTrace(pw);
			pw.close();
		 } 
		 catch (FileNotFoundException e1)
		 { e1.printStackTrace(); }		
	 }
	 
	 public static void LogErrorMessage(String filename, Error e) throws IOException
	 {
		 Reporter.log(e.toString());
		 ExecutionLog executionLog = new ExecutionLog();
		 String dateTime = executionLog.getDate();
		 ExecutionLog.Log(filename, dateTime +" [info]  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error message >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		 
//		 String fileName = executionLog.getFileName();
		 PrintWriter pw;
		 try 
		 {
			pw = new PrintWriter(new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+filename+".txt", true));
			e.printStackTrace(pw);
			pw.close();
		 } 
		 catch (FileNotFoundException e1)
		 { e1.printStackTrace(); }		
	 }
	 
	 public static void LogErrorMessage(Error e) throws IOException
	 {
		 Reporter.log(e.toString());
		 ExecutionLog executionLog = new ExecutionLog();
		 String dateTime = executionLog.getDate();
		 ExecutionLog.Log(dateTime +" [info]  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error message >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");		 
		 String fileName = executionLog.getFileName();
		 PrintWriter pw;
		 try 
		 {
			pw = new PrintWriter(new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt", true));
			e.printStackTrace(pw);
			pw.close();
		 } 
		 catch (FileNotFoundException e1)
		 { e1.printStackTrace(); }		
	 }
	 
	 public  String getFileName()
	 {
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		 Calendar cal = Calendar.getInstance();		 
		 String fileName = "Report-"+dateFormat.format(cal.getTime());
		 fileName= fileName + "-- "+cal.getTime().getHours()+"-hr - "+cal.getTime().getMinutes() +"min";
		 return fileName;
	 }
	 
	 public  String getFileName(String file, WebDriver driver)
		{
		 	Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName();
			String version = cap.getVersion().toString();			
			java.util.Date date= new java.util.Date();
			String timeStamp = new java.sql.Timestamp(date.getTime()).toString().replace(":", ".");			
//			String fileName = "Report-"+file +"_"+timeStamp;
			String fileName = file +"_"+browserName+"-"+version+"_"+timeStamp;
			return fileName;
		 }
	 
	/* public  String getScreenShotName(String file)
		{
			java.util.Date date= new java.util.Date();
			String timeStamp = new java.sql.Timestamp(date.getTime()).toString().replace(":", ".");
			String fileName;
			if (file.contains("FAIL")){
				fileName = "FAIL-"+file +"_"+timeStamp;
			}else{
			fileName = "PASS-"+file +"_"+timeStamp;
			}
			return fileName;
		 }*/
	 	 
	 public String getDate()
	 {
		 DateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
		 Calendar cal = Calendar.getInstance();	
		 String dateTime =  dateFormat.format(cal.getTime());
		 return dateTime;
	 }
	 
	 public static void LogAddClass(String text)
	 {
		 Reporter.log(text);
		 ExecutionLog executionLog = new ExecutionLog();	
		 String dateTime = executionLog.getDate();
		 String fileName = executionLog.getFileName();
		 try
		 {			 
		    // Create file 
		    FileWriter fstream = new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt",true);
		    BufferedWriter out = new BufferedWriter(fstream);
		    text = dateTime +" [info]  "+" Execution Started of Test Class "+ text;
		    out.newLine();
		    out.write("*****************************************************************************");
		    out.newLine();
		    out.write(text);
		    out.newLine();
		    out.write("*****************************************************************************");
		    out.newLine();
		    //Close the output stream
		    out.close();		    
		 }
		 catch (Exception e)
		 { System.err.println("Error: " + e.getMessage()); }
	 }
	 
	 public static void LogEndClass(String text)
	 {
		 Reporter.log(text);
		 ExecutionLog executionLog = new ExecutionLog();	
		 String dateTime = executionLog.getDate();
		 String fileName = executionLog.getFileName();
		 try
		 {			 
		    // Create file 
		    FileWriter fstream = new FileWriter(System.getProperty("user.dir")+execLogPath+"\\"+fileName+".txt",true);
		    BufferedWriter out = new BufferedWriter(fstream);
		    text = dateTime +" [info]  End Execution of Test Script "+ text;		    
		    out.write(text);
		    out.newLine();
		    out.write("*****************************************************************************");
		    out.newLine();
		    //Close the output stream
		    out.close();		    
		 }
		 catch (Exception e)
		 { System.err.println("Error: " + e.getMessage()); }
	 }
	 
	 
	 
	 
	/* public void captureScreenshot(String fileName) 
		{
			try 
			{
				String screenshotName = this.getScreenShotName(fileName);
		        FileOutputStream out = new FileOutputStream(screenShotPath+"//" + screenshotName + ".jpg");
		        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		        out.close();
		        String path = getPath();
		        String  screen = "file://"+path+"/"+screenShotPath+"/"+screenshotName + ".jpg";
		        ExecutionLog.Log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
		        Reporter.log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
		     }
			 catch (Exception e) 
			 {
				 
			 }
		 }*/
	 
	//Get absolute path
		public static String getPath()
		{
			String path ="";		
			File file = new File("");
			String absolutePathOfFirstFile = file.getAbsolutePath();
			path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
			return path;
		}
	 
	 
	public static void SystemLog()
	 {
		 
		 ExecutionLog executionLog = new ExecutionLog();	
		 String dateTime = executionLog.getDate();
		 String fileName = executionLog.getFileName();
		 try
		 {			 
		    // Create file 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    FileWriter fstream = new FileWriter(System.getProperty("user.dir")+"\\SystemLog\\"+fileName+".txt",true);
		    BufferedWriter out = new BufferedWriter(fstream);
		 
		    try {
		        String inputLine = null;
//		        do {
		            inputLine=in.readLine();
		            System.out.println(inputLine);
		            out.write(inputLine);
		            out.newLine();
//		        } while (!inputLine.equalsIgnoreCase("eof"));
		        System.out.print("Write Successful");
		    } catch(IOException e1) {
		        System.out.println("Error during reading/writing");
		    } finally {
		        out.close();
		        in.close();
		    }	    
		 }
		 catch (Exception e)
		 { System.err.println("Error: " + e.getMessage()); }
		
		

		   
		   
	 }
	 
	 public static void main(String[] str){	 
//		 Log("Test execution");		 
	 }
	  
}
