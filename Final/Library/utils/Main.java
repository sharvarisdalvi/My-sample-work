package utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Main {
	
	   public String getClassName(){
		   String className = this.getClass().getSimpleName();
		   return className;
	   }
		public static void main(String[] args) throws WebDriverException, IOException {
//			ReadDataFromExcelSheet reader=new ReadDataFromExcelSheet(System.getProperty("user.dir")+"\\EMEA_src\\com\\CARDSG\\common\\CARDSG Data Sheet.xlsx");
//			Tunneling tunneling = new Tunneling();
//			CompareExcel compExcel = new CompareExcel();
//			DatabaseConnectivity db = new DatabaseConnectivity();
//			Main main = new Main();
//			System.out.println(main.getClassName());
//			
//			System.out.println("All Properties: "+System.getProperties().toString());
//			
//			CARDSgLogInPage logInPage = new CARDSgLogInPage();
//			logInPage.printFromExcel();
//			 String celValue = reader.getCellDataUsingParameter("LogInDetails","password1");
//			 System.out.println("This is the value : " +celValue);
			System.out.println(getCurrentDate("", "EST", "MM/dd/yyyy", null));
			System.out.println(getCurrentDate("", "EST", "MM/dd/yyyy hh:mm aaa", null));
			System.out.println(getMerdianJulianDate("", "EST", null));
			
		}
		
		public static String getCurrentDate (String fileName, String expectedtimeZone, String dateFormat, WebDriver driver) throws WebDriverException, IOException{
			String[] ids = TimeZone.getAvailableIDs();
			if(Arrays.asList(ids).contains(expectedtimeZone)){
				Date date = new Date();
				DateFormat df = new SimpleDateFormat(dateFormat);			
				df.setTimeZone(TimeZone.getTimeZone(expectedtimeZone));
				String dateToShow =  df.format(date);
				return dateToShow;		
			}else{
//				ExecutionLog.logAndCapture(fileName,"Time Zone - "+expectedtimeZone+" is not available", false);
				return null;
			}
		}
		
		public static String getMerdianJulianDate (String fileName, String expectedtimeZone, WebDriver driver) throws WebDriverException, IOException{
			String[] ids = TimeZone.getAvailableIDs();
			if(Arrays.asList(ids).contains(expectedtimeZone)){
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(expectedtimeZone));
				int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);  
				String meridianJulianDt = "M"+dayOfYear;		
				return meridianJulianDt;
			}else{
				ExecutionLog.logAndCapture(fileName,"Time Zone - "+expectedtimeZone+" is not available", false);
				return null;
			}
		}

	}

