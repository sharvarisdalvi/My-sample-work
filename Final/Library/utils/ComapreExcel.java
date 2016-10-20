package utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ComapreExcel {
	 public boolean compareDB_UI_ExcelSheets(String fileName, String testCaseName,String sheetName1, String sheetName2) {
	    	boolean flag=false;
	        try {
	            // get input excel files
	            /*FileInputStream excellFile1 = new FileInputStream(new File(
	                    "DB_Snapshots/DB2-"+testCaseName+".xlsx"));
	            FileInputStream excellFile2 = new FileInputStream(new File(
	            		"DB_Snapshots/DB2-"+testCaseName+".xlsx"));*/
	        	
	        	FileInputStream excellFile1 = new FileInputStream(new File(fileName));
//	            FileInputStream excellFile2 = new FileInputStream(new File(fileName));

	            // Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
//	            XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

	            // Get first/desired sheet from the workbook
	            XSSFSheet sheet1 = workbook1.getSheet(sheetName1);
	            XSSFSheet sheet2 = workbook1.getSheet(sheetName2);

	            
	            flag =compareTwoSheets(sheet1, sheet2);
	            // Compare sheets
	            if(flag) {
	                ExecutionLog.Log("\n\nThe two excel sheets are Equal");
	                //Reporter.Log(String filename,"\n\nThe two excel sheets are Equal");
//	                return true;
	            } else {
	            	ExecutionLog.Log("\n\nThe two excel sheets are not Equal");
	                //Reporter.Log(String filename,"\n\nThe two excel sheets are not Equal");
//	                return false;
	            }
	            
	            //close files
	            excellFile1.close();
//	            excellFile2.close();
//	            return flag;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return flag;

	    }

	    
	    // Compare Two Sheets
	    public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2) {
	        int firstRow1 = sheet1.getFirstRowNum()+1;
	        int lastRow1 = sheet1.getLastRowNum();
	        int lastRow2 = sheet2.getLastRowNum();
	        if (lastRow1!=lastRow2){
	        	return false;
	        }
	        boolean equalSheets = true;
	        for(int i=firstRow1; i <= lastRow1; i++) {
	            
	            ExecutionLog.Log("\n\nComparing Row "+i);
	            //Reporter.Log(String filename,"\n\nComparing Row "+i);
	            
	            XSSFRow row1 = sheet1.getRow(i);
	            XSSFRow row2 = sheet2.getRow(i);
	            if(!compareTwoRows(row1, row2)) {
	                equalSheets = false;
	                ExecutionLog.Log("Row "+i+" - Not Equal");
	                //Reporter.Log(String filename,"Row "+i+" - Not Equal");
	                break;
	            } else {
	                ExecutionLog.Log("Row "+i+" - Equal");
	                //Reporter.Log(String filename,"Row "+i+" - Equal");
	            }
	        }
	        return equalSheets;
	    }

	    // Compare Two Rows
	    public static boolean compareTwoRows(XSSFRow row1, XSSFRow row2) {
	        if((row1 == null) && (row2 == null)) {
	            return true;
	        } else if((row1 == null) || (row2 == null)) {
	            return false;
	        }
	        
	        int firstCell1 = row1.getFirstCellNum();
	        int lastCell1 = row1.getLastCellNum();
	        boolean equalRows = true;
	        
	        // Compare all cells in a row
	        for(int i=firstCell1; i <= lastCell1; i++) {
	            XSSFCell cell1 = row1.getCell(i);
	            XSSFCell cell2 = row2.getCell(i);
	            ExecutionLog.Log("cell1="+cell1+"     cell2="+cell2);
	            //Reporter.Log(String filename,"cell1="+cell1+"     cell2="+cell2);
	            if(!compareTwoCells(cell1, cell2)) {
	                equalRows = false;
	                ExecutionLog.Log("       Cell "+i+" - NOt Equal");
	                //Reporter.Log(String filename,"       Cell "+i+" - NOt Equal");
	                break;
	            } else {
	            	 ExecutionLog.Log("       Cell "+i+" - Equal");
	            	 //Reporter.Log(String filename,"       Cell "+i+" - Equal");
	            }
	        }
	        return equalRows;
	    }

	    // Compare Two Cells
	    public static boolean compareTwoCells(XSSFCell cell1, XSSFCell cell2) {
	        if((cell1 == null) && (cell2 == null)) {
	            return true;
	        } else if((cell1 == null) || (cell2 == null)) {
	            return false;
	        }
	        
	        boolean equalCells = false;
	        
	        // Only compares the values of the cells....
	        if (cell1.getStringCellValue().trim().equals(
	                cell2.getStringCellValue().trim())) {
	            equalCells = true;
	        }
	        
	/*        int type1 = cell1.getCellType();
	        int type2 = cell2.getCellType();
	        if (type1 == type2) {
	            if (cell1.getCellStyle().equals(cell2.getCellStyle())) {
	                // Compare cells based on its type
	                switch (cell1.getCellType()) {
	                case HSSFCell.CELL_TYPE_FORMULA:
	                    if (cell1.getCellFormula().equals(cell2.getCellFormula())) {
	                        equalCells = true;
	                    }
	                    break;
	                case HSSFCell.CELL_TYPE_NUMERIC:
	                    if (cell1.getNumericCellValue() == cell2
	                            .getNumericCellValue()) {
	                        equalCells = true;
	                    }
	                    break;
	                case HSSFCell.CELL_TYPE_STRING:
	                    if (cell1.getStringCellValue().equals(cell2
	                            .getStringCellValue())) {
	                        equalCells = true;
	                    }
	                    break;
	                case HSSFCell.CELL_TYPE_BLANK:
	                    if (cell2.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
	                        equalCells = true;
	                    }
	                    break;
	                case HSSFCell.CELL_TYPE_BOOLEAN:
	                    if (cell1.getBooleanCellValue() == cell2
	                            .getBooleanCellValue()) {
	                        equalCells = true;
	                    }
	                    break;
	                case HSSFCell.CELL_TYPE_ERROR:
	                    if (cell1.getErrorCellValue() == cell2.getErrorCellValue()) {
	                        equalCells = true;
	                    }
	                    break;
	                default:
	                    if (cell1.getStringCellValue().equals(
	                            cell2.getStringCellValue())) {
	                        equalCells = true;
	                    }
	                    break;
	                }
	            } else {
	                return false;
	            }
	        } else {
	            return false;
	        }*/
	        return equalCells;
	    }
}
