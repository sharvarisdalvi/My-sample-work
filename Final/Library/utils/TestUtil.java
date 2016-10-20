package utils;

import java.util.Hashtable;

public class TestUtil extends DriverTestCase {

    public static boolean isExecutable(String testname, ReadDataFromExcelSheet xls) {

        for (int rowNum = 2; rowNum <= xls.getRowCount("Testcases"); rowNum++) {

            if (xls.getCellData("Testcases", "TCID", rowNum).equals(testname)) {

                if (xls.getCellData("Testcases", "Runmode", rowNum).equals("Y"))

                    return true;
                else
                    return false;
            }

        }
        return false;


    }

    

    public static Object[][] getdata(String testName, ReadDataFromExcelSheet xls) {
        //first find the row num from which test starts
        //number of columns in test
        //number of rows in test
        //put the data in hastable and put hastable in array

        //first find the row num from which test starts
        int testStartRowNum = 0;
        for (int rNum = 1; rNum <= xls.getRowCount("TestData"); rNum++) {
            if (xls.getCellData("TestData", 0, rNum).equals(testName)) {
                testStartRowNum = rNum;
                break;
            }

        }
        System.out.println("Test " + testName + " Start from " + testStartRowNum);
        //number of columns in test
        int colStartRownum = testStartRowNum + 1;
        int totalcols = 0;
        while (!xls.getCellData("TestData", totalcols, colStartRownum).equals("")) {
            totalcols++;
        }
        System.out.println("***********************************");
        System.out.println("Total Column in test " + testName + " are " + totalcols);

        //number of rows in test
        int dataStartRowNum = testStartRowNum + 2;
        int totalRows = 0;
        while (!xls.getCellData("TestData", 0, dataStartRowNum + totalRows).equals("")) {
            totalRows++;

        }
        System.out.println("Total Column in test " + testName + " are " + totalRows);
        System.out.println("***********************************");

        //extract data //put the data in Hashtable and put Hashtable in array
        Object[][] data = new Object[totalRows][1];
        int index = 0;
        Hashtable<String, String> table = null;

        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
            table = new Hashtable<String, String>();
            for (int colNum = 0; colNum <= totalcols; colNum++) {
                table.put(xls.getCellData("TestData", colNum, colStartRownum)
                        , xls.getCellData("TestData", colNum, rNum));
                System.out.print(xls.getCellData("TestData", colNum, rNum) + "---");
            }
            data[index][0] = table;
            index++;

            System.out.println();
        }

        System.out.println("Done");
        return data;

    }

   
    

}	
