package Utilities;

import Constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverAdapter {
    public static WebDriver objBrowser;
    public static String browserTypte;
    private static  Logger Log = Logger.getLogger(WebDriverAdapter.class.getName());

    public WebDriverAdapter(){
        objBrowser = this.getObjBroswer();
    }

    public WebDriverAdapter(String strBrowser, String Path){
        if(strBrowser.equalsIgnoreCase(Constants.C_BROWSER)){
            WebDriverManager.chromedriver().version("79.0.3945.36").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            objBrowser = new ChromeDriver(options);
        }else if(strBrowser.equalsIgnoreCase(Constants.FF_BROWSER)){
            WebDriverManager.firefoxdriver().version("1.1.4").setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            objBrowser = new FirefoxDriver(options);
        }else if(strBrowser.equalsIgnoreCase(Constants.IE_BROWSER)){
            WebDriverManager.iedriver().version("3.141").setup();
            objBrowser = new InternetExplorerDriver();
        }
        objBrowser.get(Path);
        objBrowser.manage().window().maximize();
        objBrowser.manage().timeouts().pageLoadTimeout(Constants.BROWSER_PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        objBrowser.manage().timeouts().setScriptTimeout(Constants.BROWSER_SCRIPT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        objBrowser.manage().timeouts().implicitlyWait(Constants.BROWSER_IMPLICIT_WAIT_SECONDS,TimeUnit.SECONDS);
    }


    private WebDriver getObjBroswer() {
        return objBrowser;
    }
}
