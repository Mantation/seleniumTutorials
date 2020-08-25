package appModules.pageObjects;

import Utilities.WebDriverAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.logging.Logger;

public class mainPage {
    private static Logger Log = Logger.getLogger(login_Page.class.getName());
    private static Select select = null;
    private static WebElement element = null;
    private static List elements = null;
    public static WebDriver driver = WebDriverAdapter.objBrowser;

    public static WebElement dateField() throws Exception{
        try{
            element = driver.findElement(By.id("date"));
            Log.info("datefield is found");
        }catch (Exception e){
            Log.warning("datefield is not found");
            throw (e);
        }

        return element;
    }

    public static WebElement setDate() throws Exception {

        element = null;
        try {
            element = driver.findElement(By.id("date"));
            Log.info("Date field is found" );
        } catch (Exception e) {
            Log.warning("ERROR:Date field not found on  wizard" );
            throw(e);
        }

        return element;
    }

    public static List tableHeads() throws Exception{
        try{
            elements = driver.findElements(By.id("head"));
            Log.info("Table headers is found");
        }catch (Exception e){
            Log.warning("Table headers  is not found");
            throw (e);
        }

        return elements;
    }

    public static WebElement tableMain() throws Exception{
        try{
            element = driver.findElement(By.id("bookingsTable"));
            Log.info("Bookings Table is found");
        }catch (Exception e){
            Log.warning("Bookings Table is not found");
            throw (e);
        }

        return element;
    }

    public static WebElement getTableLink(WebElement table, int column, int row) throws Exception{
        JavascriptExecutor js = (JavascriptExecutor)((RemoteWebElement)table).getWrappedDriver();
        try{
            element = (WebElement)js.executeScript(
                    "var table = arguments[0], header = arguments[1], iRow = arguments[2];      " +
                            "return table.rows[iRow].cells[header];" ,
                    table, column, row);
            //document.getElementById("dtBasicExample").rows[0].cells[index].innerHTML;
            Log.info("Bookings Table Column is found");
        }catch (Exception e){
            Log.warning("Bookings Table Column is not found");
            throw (e);
        }
        return element;
    }

    public static WebElement clientName() throws Exception{
        try{
            element = driver.findElement(By.id("clientel"));
            Log.info("client textfield is found");
        }catch (Exception e){
            Log.warning("client textfield is not found");
            throw (e);
        }

        return element;
    }

    public static WebElement clientsList() throws Exception{
        try{
            element = driver.findElement(By.className("listDialog"));
            Log.info("client list is found");
        }catch (Exception e){
            Log.warning("client list is not found");
            throw (e);
        }

        return element;
    }

    public static WebElement clientResult() throws Exception{
        try{
            element = driver.findElement(By.xpath("//fieldset/div/div/div/div/div"));
            Log.info("client list result is found");
        }catch (Exception e){
            Log.warning("client list result is not found");
            throw (e);
        }

        return element;
    }

    public static Select selectTherapist() throws Exception{
        try{
            select = new Select(driver.findElement(By.id("beautician")));
            Log.info("client list result is found");
        }catch (Exception e){
            Log.warning("client list result is not found");
            throw (e);
        }

        return select;
    }

    public static WebElement bookSession() throws Exception{
        try{
            element = driver.findElement(By.id("register"));
            Log.info("Book button is found");
        }catch (Exception e){
            Log.warning("Book button is not found");
            throw (e);
        }

        return element;
    }

    public static WebElement cancelOption() throws Exception{
        try{
            element = driver.findElement(By.id("cancel"));
            Log.info("Cancel option is found");
        }catch (Exception e){
            Log.warning("Cancel option is not found");
            throw (e);
        }

        return element;
    }

    public static WebElement cancelSession() throws Exception{
        try{
            element = driver.findElement(By.id("e_cancel"));
            Log.info("Cancel Button is found");
        }catch (Exception e){
            Log.warning("Cancel Button is not found");
            throw (e);
        }

        return element;
    }

    public static Select selectEditTherapist() throws Exception{
        try{
            select = new Select(driver.findElement(By.id("e_beautician")));
            Log.info("client list result is found");
        }catch (Exception e){
            Log.warning("client list result is not found");
            throw (e);
        }

        return select;
    }

    public static WebElement editSession() throws Exception{
        try{
            element = driver.findElement(By.id("e_edit"));
            Log.info("Edit booking button is found");
        }catch (Exception e){
            Log.warning("Edit booking button is not found");
            throw (e);
        }

        return element;
    }
}
