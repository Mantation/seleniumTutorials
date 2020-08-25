import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        WebDriver driver = null;
        WebDriverManager.chromedriver().version("79.0.3945.36").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://www.twitter.com");
        try {
            Thread.sleep(5000); //primary Log In button
            driver.findElement(By.xpath("//div[@id='react-root']/div/div/div[2]/header/div[2]/div/div/div[2]/div/div/a/div/span/span")).click();
            Thread.sleep(5000); //Username
            driver.findElement(By.name("session[username_or_email]")).sendKeys("Rebonepholose@gmail.com");
            Thread.sleep(5000); //Password
            driver.findElement(By.name("session[password]")).sendKeys("Khwezla@2014");
            Thread.sleep(5000);  //Second Login
            driver.findElement(By.xpath("//div[@id='react-root']/div/div/div[2]/main/div/div/form/div/div[3]/div/div")).click();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
