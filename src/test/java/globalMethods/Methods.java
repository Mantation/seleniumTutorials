package globalMethods;

import appModules.functionalActions.Login_Action;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static Utilities.WebDriverAdapter.objBrowser;

public class Methods {
    private static Logger Log = Logger.getLogger(Methods.class.getName());

    //set value to an element
    public static void setTextBoxValue(WebElement element, String strText){
        element.clear();
        element.sendKeys(strText);
    }

    //sleep functionality
    public static void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //click element
    public static void ClickElement(WebElement element){
        element.click();
    }
    //Methods to wait for a particular element to be visible
    public static void waitForElement(WebElement element) throws Exception {
        try{
            WebDriverWait wait = new WebDriverWait(objBrowser, 10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch(Exception e){
            Log.warning(e.getMessage());
            throw (e);

        }

    }
    //set value to an element
    public static void setDateValue(WebElement element, String strDate) {
        JavascriptExecutor js = (JavascriptExecutor)((RemoteWebElement)element).getWrappedDriver();
        WebElement link = (WebElement)js.executeScript(
                "document.getElementById('date').value = arguments[0]",
                strDate
        );

    }

    public static void selectFromOptions(Select select, String strText) {
        select.selectByVisibleText(strText);
    }
}
