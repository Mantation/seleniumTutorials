package appModules.testCases;

import Configurations.Config;
import Constants.Constants;
import Utilities.CommonUtils;
import Utilities.ExcelUtils;
import Utilities.WebDriverAdapter;
import appModules.functionalActions.Bookings_Action;
import appModules.functionalActions.Login_Action;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Bookings extends CommonUtils {
    private static Logger Log = Logger.getLogger(Bookings.class.getName());
    private Map<String, String> mapResults = null;
    private String executionTime;
    private String[] arrHeaders = null;
    private String strTestCaseName;
    private String strRunRow;
    private String strSignIn;
    int iCount = 0;

    @BeforeClass
    public void beforeClass(){
        executionTime = Config.getCurrentTimeStamp();
        CommonUtils.TestCaseName = "Bookings";
        new WebDriverAdapter(Constants.FF_BROWSER,Constants.AUT);
    }

    @Test(dataProvider = "EqualsAssertionsProvider")
    public void executeTest(String... data) throws Exception{
        if(iCount == 0){
            arrHeaders = data;
            iCount++;
        }

        mapResults = new HashMap<String, String>();
        ExcelUtils getValue = new ExcelUtils();
        strTestCaseName = getValue.getCellValue(arrHeaders,data,"TestCaseName");
        strRunRow = getValue.getCellValue(arrHeaders,data,"RunRow");
        strSignIn = getValue.getCellValue(arrHeaders,data,"SignIn");
        //If Runrow is set to Y
        if(strRunRow !=null && strRunRow.equalsIgnoreCase("y")){
            try {
                Log.info("Executin test: " + strTestCaseName + " -- Run Mode: " + strRunRow);
                //Check if sign in is Y
                if (strSignIn.equalsIgnoreCase("y")) {
                    Login_Action.Execute(arrHeaders, data, "");
                    Log.info("Executing Sign In test: " + strTestCaseName + " -- Run Mode: " + strRunRow);
                }
                Bookings_Action.Execute(arrHeaders, data, "");
            }catch (Exception e){
                Log.info("End of Test Execution: " + strTestCaseName + " -- Run Mode: " + strRunRow);
                CommonUtils.takeScreenshot(WebDriverAdapter.objBrowser, strTestCaseName);
                Log.error("ERROR: Test Failed");
                Assert.fail("ERROR: Test Failed");
            }
        }else{
            if(strTestCaseName.equalsIgnoreCase("TestCaseName")){
                ignoreTest();
                throw new SkipException("Skipping Test");
            }
        }
    }

    @Ignore
    public void ignoreTest(){
        Log.info("Skipping Execution test : " + strTestCaseName);
    }

    @AfterClass
    public void afterTest(){
        final String finalTime = Config.getCurrentTimeStamp();
        Log.info("Final Execution Time : " + finalTime);
    }

}
