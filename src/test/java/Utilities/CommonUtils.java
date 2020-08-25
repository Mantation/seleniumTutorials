package Utilities;

import Constants.Constants;
import appModules.functionalActions.Login_Action;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;


import static Configurations.Config.getCurrentTimeStamp;

public class CommonUtils {
    private static Logger Log = Logger.getLogger(CommonUtils.class.getName());
    public static String TestCaseName;
    final static int RunRow = 2;
    public static Map<String, String> mapTestNG_Params = null;
    public static LinkedHashMap<String, Integer> mapDataSet = null;

    public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception{

        //check if testcasename is empty
        if(sTestCaseName.isEmpty()){
            sTestCaseName = "APPLICATION_" + getCurrentTimeStamp();
        } else{
            sTestCaseName = sTestCaseName + getCurrentTimeStamp();
        }
        //capture screenshot
        try{
            //create file
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //create destination file
            File destFile = new File(Constants.REPORTS_FOLDER+File.separator+ Constants.SCREENSHOT_FOLDER+File.separator+sTestCaseName+".png");
            //copy file to destination
            FileUtils.copyFile(scrFile, destFile);
        }catch ( Exception e){
            Log.error(e);
            Log.error("failed to take screenshot");
            throw  new Exception();
        }

    }

    @DataProvider(name = "EqualsAssertionsProvider")
    public Object[][] createConcatenationAssertionSet( Method objMethod) throws Exception{
        return datasheetArr();//, getDeclaringClass(objMethod));
    }

    private String[][] datasheetArr(){
        int numRows, numCols;
        String datasheetArr[][] = null;
        try{
            //get datashet file
            String strDataFile = Constants.INPUT_DATA_FOLDER+"Data_"+TestCaseName +".xls";
            Log.info("Reading data file : " + strDataFile );
            mapDataSet = new LinkedHashMap<String, Integer>();
            //create an input Stream
            InputStream objFIS = new FileInputStream(strDataFile);
            if(objFIS !=null){
                Workbook workbook = Workbook.getWorkbook(objFIS);

                //Specify sheetname
                String strSheetName = Constants.TEST_ENVIRONMENT;
                Sheet st = workbook.getSheet(strSheetName);
                //Check if sheet exists
                if(st == null){
                    Log.info("["+strSheetName+"] - Sheet is not found, proceeding with the default sheet");
                    st = workbook.getSheet(0);
                }
                numRows = st.getRows();
                numCols = st.getColumns();
                int totalRuns = 0;
                //check total number of rows to be run
                for (int i = 0; i < numRows; i++) {
                    String strRunRow = st.getCell(RunRow, i).getContents();
                    if(strRunRow.equalsIgnoreCase("RunRow")||strRunRow.equalsIgnoreCase("Y")){
                        totalRuns++;
                    }
                }
                datasheetArr = new String[totalRuns][numCols];

                int iDataRows = 0;

                for (int iExcelRow = 0; iExcelRow < numRows; iExcelRow++) {

                    String strRunRow = st.getCell(RunRow, iExcelRow).getContents();
                    if(iExcelRow == 0 && strRunRow.equalsIgnoreCase("RunRow") || iExcelRow > 0 && strRunRow.equalsIgnoreCase("Y") ){
                        int iDataColumn = 0;
                        for (int iExcelCol = 0; iExcelCol < numCols; iExcelCol++) {
                            datasheetArr[iDataRows][iDataColumn] = st.getCell(iExcelCol,iExcelRow).getContents();
                            iDataColumn++;
                        }
                        mapDataSet.put(datasheetArr[iDataRows][0],iExcelRow);
                        iDataRows ++;
                    }
                }
            }else{
                Log.error("Error opening data file : "+ strDataFile);
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.error("Datasheet Load error");
        }

    return  datasheetArr;

    }


}
