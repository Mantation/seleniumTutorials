package appModules.functionalActions;

import Utilities.ExcelUtils;
import appModules.pageObjects.login_Page;
import appModules.pageObjects.mainPage;
import globalMethods.Methods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.table.TableColumn;
import java.util.List;

public class Bookings_Action {

    private static Logger Log = Logger.getLogger(Bookings_Action.class.getName());
    static ExcelUtils getValue = new ExcelUtils();

    public static void Execute(String[] headers, String[] data, String columnName ) throws Exception {
        String sTestCaseName = getValue.getCellValue(headers, data, "TestCaseName");
        String sRowNumber = getValue.getCellValue(headers, data, "RowNumber");
        String strScenario = getValue.getCellValue(headers, data, "Scenario");
        String strDate = getValue.getCellValue(headers, data, "TargetDate");
        String strTime = getValue.getCellValue(headers, data, "TargetTime");
        String strBeautician = getValue.getCellValue(headers, data, "Beautician");
        String strClient = getValue.getCellValue(headers, data, "ClientName");
        String strTherapist = getValue.getCellValue(headers, data, "Therapistname");
        String strTherapistOption = getValue.getCellValue(headers, data, "TherapistOption");

        try{
            Log.info("'''''''''''''''''''''''''''''''''''''BOOKINGS''''''''''''''''''''''''''''''''''''''''''''''");
            //Set Bookings Date
            Methods.waitForElement(mainPage.tableMain());
            Methods.sleep(4000);
            Methods.setDateValue(mainPage.setDate(), strDate);
            Methods.waitForElement(mainPage.tableMain());
            Methods.sleep(4000);
            //Find the therapist
            List<WebElement> headerTable = mainPage.tableHeads();
            List<WebElement> headerColumns = headerTable.get(0).findElements(By.tagName("th"));
            int TargetColumn = 0;
            for (int i = 0; i < headerColumns.size(); i++) {
                String header = headerColumns.get(i).getAttribute("innerHTML");
                if(header.contains(strBeautician)){
                    TargetColumn = i;
                    break;
                }
            }
            //find time/row
            WebElement MainTable = mainPage.tableMain();
            Methods.sleep(2000);
            List<WebElement> Mainrows = MainTable.findElements(By.tagName("tr"));

            int TargetRow = 0;
            for (int x = 0; x < Mainrows.size(); x++) {
                String timeslot = Mainrows.get(x).getAttribute("innerHTML");
                if(timeslot.contains(strTime)){
                    TargetRow = x;
                    break;
                }
            }

            //click the cell identified
            mainPage.getTableLink(MainTable, TargetColumn, TargetRow).click();
            Methods.sleep(2000);
            //Add Bookings
            if(strScenario.equalsIgnoreCase("Booking")){
                Methods.waitForElement(mainPage.clientName());
                Methods.setTextBoxValue(mainPage.clientName(),strClient);
                Methods.sleep(500);
                Methods.ClickElement(mainPage.clientResult());
                Methods.selectFromOptions(mainPage.selectTherapist(),strTherapist);
                Methods.ClickElement(mainPage.bookSession());
                Methods.sleep(7000);
                Methods.waitForElement(mainPage.tableMain());
                //Edit Bookings
            }else if(strScenario.equalsIgnoreCase("Edit_Booking")){
                Methods.waitForElement(mainPage.editSession());
                Methods.selectFromOptions(mainPage.selectEditTherapist(),strTherapistOption);
                Methods.sleep(555);
                Methods.ClickElement(mainPage.editSession());
                Methods.sleep(7000);
                Methods.waitForElement(mainPage.tableMain());
                //Cancel Bookings
            }else if(strScenario.equalsIgnoreCase("Cancel_Booking")){
                Methods.waitForElement(mainPage.cancelOption());
                Methods.sleep(555);
                Methods.ClickElement(mainPage.cancelOption());
                Methods.sleep(2000);
                Methods.ClickElement(mainPage.cancelSession());
                Methods.sleep(7000);
                Methods.setDateValue(mainPage.setDate(),strDate);
                Methods.sleep(7000);
                Methods.waitForElement(mainPage.tableMain());
            }
        }catch (Exception e){
            Log.error("Unable to make a Booking:");
            throw(e);
        }

    }







}
