package appModules.functionalActions;

import Utilities.ExcelUtils;
import appModules.pageObjects.login_Page;
import globalMethods.Methods;
import org.apache.log4j.Logger;


public class Login_Action {
    private static Logger Log = Logger.getLogger(Login_Action.class.getName());
    static ExcelUtils getValue = new ExcelUtils();

    public static void Execute(String[] headers, String[] data, String columnName ) throws Exception {
        String userName = getValue.getCellValue(headers,data,"UserName");
        String passWord = getValue.getCellValue(headers,data,"Password");
        String testCase = getValue.getCellValue(headers,data,"TestCaseName");
        String rowNumber = getValue.getCellValue(headers,data,"RowNumber");
        String runRow = getValue.getCellValue(headers,data,"RunRow");

        try{
            Log.info("'''''''''''''''''''''''''''''''''''''LOGON''''''''''''''''''''''''''''''''''''''''''''''");
            Log.info("'''''''''''''''''''''''''''''''''''''Clearing username and Password Fields''''''''''''''''''''''''''''''''''''''''''''''");
            Methods.setTextBoxValue(login_Page.username(),"");
            Methods.setTextBoxValue(login_Page.password(),"");
            Methods.sleep(2000);
            //Login
            Methods.setTextBoxValue(login_Page.username(),userName);
            Methods.setTextBoxValue(login_Page.password(),passWord);
            Methods.ClickElement(login_Page.loginButton());
            Methods.sleep(7000);
            Log.info("'''''''''''''''''''''''''''''''''''''LOGON - SUCCESS''''''''''''''''''''''''''''''''''''''''");

        }catch (Exception e){
            Log.error("Unable to Login:");
            throw(e);
        }

    }
}
