package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Tunneling extends DriverTestCase{
	Connection conn = null;
	Session session = null;
	boolean isAuthenticated;
	PrintWriter writer = null;
	
	String taxiHostName = reader.getCellData("Tunneling", "VALUE", 2);
	String userNameForTaxi = reader.getCellData("Tunneling", "VALUE", 3);
	String passwordForTaxi = reader.getCellData("Tunneling", "VALUE", 4);
	String mainServerIP = reader.getCellData("Tunneling", "VALUE", 5);
	String mainServerUsername = reader.getCellData("Tunneling", "VALUE", 6);
	String mainServerPassword = reader.getCellData("Tunneling", "VALUE", 7);
	String logQuery = reader.getCellData("Tunneling", "VALUE", 8);
	String captureLog = reader.getCellData("Tunneling", "VALUE", 9);

	public void doServerTunneling () throws Exception {
		
		if(captureLog.equalsIgnoreCase("y") || captureLog.equalsIgnoreCase("yes")){
		
//		conn = new Connection(taxiHostName);
		conn = new Connection(mainServerIP);
		conn.connect();
//		isAuthenticated = conn.authenticateWithPassword(userNameForTaxi, passwordForTaxi);
		isAuthenticated = conn.authenticateWithPassword(mainServerUsername, mainServerPassword);
		session = conn.openSession();
		if (isAuthenticated){
		System.out.println("Capturing the log --- ");
		session.requestPTY("bash");
		session.startShell();
		writer = new PrintWriter(session.getStdin());
		
		/*writer.println("ssh " + mainServerIP + " -l " + mainServerUsername);
		writer.flush();
		Thread.sleep(10000);
		
		writer.println(mainServerPassword);
		writer.flush();
		Thread.sleep(5000);*/
		
		writer.println(logQuery);
		writer.flush();
		Thread.sleep(5000);
		}else{
		System.out.println("Error in capturing the log ! ");
		}
		}else{
			System.out.println("Server log capturing is skipped !!! ");
		}
	}
	
	public void closeServerConnection() throws Exception{
		
		if(captureLog.equalsIgnoreCase("y") || captureLog.equalsIgnoreCase("yes")){
		writer.println("exit");
		writer.flush();
		Thread.sleep(2000);	
		
		writer.close();
		session.close();
		conn.close();		
		}
	}
	
	public void displayPuttyOutput() throws Exception{
		if(captureLog.equalsIgnoreCase("y") || captureLog.equalsIgnoreCase("yes")){
		InputStream stdout = null;
		BufferedReader stdoutReader = null;
		stdout = new StreamGobbler(session.getStdout());
		stdoutReader = new BufferedReader(new InputStreamReader(stdout));
		String oneLine;
		
		String filename=getFileName();
		System.out.println(filename);
		FileWriter fstream = new FileWriter(System.getProperty("user.dir")+"\\SystemLog\\"+filename+".txt",true);
	    BufferedWriter out = new BufferedWriter(fstream);
		while ((oneLine = stdoutReader.readLine()) != null){
			System.out.println(oneLine);			
		    out.write(oneLine);
		    out.newLine();		    
		}
		out.close();
		stdout.close();
		stdoutReader.close();
		}
	}	
	
//	public  String getFileName()
//	 {
//		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
//		 Calendar cal = Calendar.getInstance();		 
//		 String fileName = "Report-"+dateFormat.format(cal.getTime());
//		 fileName= fileName + "--"+cal.getTime().getHours()+"-hr-"+cal.getTime().getMinutes() +"min";
//		 return fileName;
//	 }
	
    public  String getFileName()
    {
           java.util.Date date= new java.util.Date();
           String timeStamp = new Timestamp(date.getTime()).toString().replace(":", ".");                     
           String fileName = "Report" +"_"+timeStamp;
           return fileName;
    }



}
