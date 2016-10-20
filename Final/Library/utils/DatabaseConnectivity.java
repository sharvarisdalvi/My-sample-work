package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseConnectivity extends DriverTestCase{
	Connection conn = null;
	Statement stmt = null;
	PrintWriter writer = null;	

	String jdbcDriver = reader.getCellData("DB details", "VALUE", 2);
	String dbUrl = reader.getCellData("DB details", "VALUE", 3);
	String dbIP = reader.getCellData("DB details", "VALUE", 4);
	String dbPort = reader.getCellData("DB details", "VALUE", 5);
	String dbName = reader.getCellData("DB details", "VALUE", 6);
	String userName = reader.getCellData("DB details", "VALUE", 7);
	String password = reader.getCellData("DB details", "VALUE", 8);
//	String query = reader.getCellData("DB details", "VALUE", 9);


	public void takeDBSnapshot (String query, String testCaseName) throws Exception {

		try{
			Class.forName(jdbcDriver);
			System.out.println	("Connecting to database...");
			conn = DriverManager.getConnection(dbUrl+dbIP+":"+dbPort+"/"+dbName,userName,password);			
			System.out.println("Creating statement...");
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(query);
			

			int columnCount = rs.getMetaData().getColumnCount();

			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet spreadsheet = workbook.createSheet("DB2 snapshot");
			XSSFRow row=spreadsheet.createRow(0);
			XSSFCell cell;
			for (int i=1;i<=columnCount; i++){
				cell=row.createCell(i-1);
				cell.setCellValue(rs.getMetaData().getColumnName(i));		    	  
			}

			int i=1;
			while(rs.next())
			{
				row=spreadsheet.createRow(i);
				for (int j=1;j<=columnCount;j++){
					cell=row.createCell(j-1);
					cell.setCellValue(rs.getString(j));
				}       
				i++;
			}
			for (int j=0;j<columnCount;j++){
				spreadsheet.autoSizeColumn(j);
			}
			FileOutputStream out = new FileOutputStream(
					new File("DB_Snapshots/DB2-"+testCaseName+".xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("DB2-"+testCaseName+".xlsx written successfully");
			rs.close();	
			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}		
	}
}