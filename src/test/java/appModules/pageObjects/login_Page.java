package appModules.pageObjects;

import Utilities.WebDriverAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class login_Page {
    private static Logger Log = Logger.getLogger(login_Page.class.getName());
    private static WebElement element = null;
    private static WebDriver driver = WebDriverAdapter.objBrowser;

    //Login Username Textfield
    public static WebElement username() throws Exception{
        try{
            element = driver.findElement(By.id("username"));
            Log.info("username textfield is found");
        }catch (Exception e){
            Log.warning("username textfield is not found");
            throw(e);
        }
        return element;
    }

    //Login Password Textfield
    public static WebElement password() throws Exception{
        try{
            element = driver.findElement(By.id("password"));
            Log.info("password textfield is found");
        }catch (Exception e){
            Log.warning("password textfield is not found");
            throw(e);
        }
        return element;
    }

    //Login login Button
    public static WebElement loginButton() throws Exception{
        try{
            element = driver.findElement(By.id("login"));
            Log.info("login button is found");
        }catch (Exception e){
            Log.warning("login button is not found");
            throw(e);
        }
        return element;
    }






}
