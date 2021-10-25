package edge.core;

import java.net.MalformedURLException;
import java.net.URL;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import edge.core.Properties.TypeExecution;

public class DriverFactory {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
        @Override
        protected synchronized WebDriver initialValue() {
            return initDriver();
        }
    };

    private DriverFactory(){

    }

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    public static WebDriver initDriver(){
        WebDriver driver = null;
        if(Properties.type == TypeExecution.LOCAL){
            switch (Properties.browser) {
                case FIREFOX: driver = new FirefoxDriver(); break;
                case CHROME: driver = new ChromeDriver(); break;
            }
        }
        if(Properties.type == TypeExecution.GRID){
            DesiredCapabilities cap = null;
            switch (Properties.browser) {
                case FIREFOX: cap = DesiredCapabilities.firefox(); break;
                case CHROME: cap = DesiredCapabilities.chrome(); break;
            }
            try {
                driver = new RemoteWebDriver(new URL("http://192.168.1.106:4444/wd/hub"), cap);
            } catch (MalformedURLException e) {
                System.err.println("Falha na conexão com o GRID: "+ e);
                e.printStackTrace();
            }
        }
        if(Properties.type == TypeExecution.NUVEM){
            DesiredCapabilities cap = null;
            
            switch (Properties.browser) {
                case FIREFOX: cap = DesiredCapabilities.firefox(); break;
                case CHROME: cap = DesiredCapabilities.chrome(); break;
            }

            // cap.setCapability("username", "oauth-blucas.oficial-a3227");
            // cap.setCapability("accessKey", "cbd32e1d-7c08-4404-af36-d8a82cbe870c");
            // cap.setCapability("name", testName.getMethodName());
            // cap.setCapability("browserVersion", "latest");

            try {
                driver = new RemoteWebDriver(new URL("https://oauth-blucas.oficial-a3227:cbd32e1d-7c08-4404-af36-d8a82cbe870c@ondemand.us-west-1.saucelabs.com:80/wd/hub"), cap);
            } catch (MalformedURLException e) {
                System.err.println("Falha na conexão com o GRID: "+ e);
                e.printStackTrace();
            }
        }
            
    
        return driver;
    } 

    public static void killDriver(){
        WebDriver driver = getDriver();
        if ( driver != null){
            driver.quit();
            driver = null;
        }

        if (threadDriver != null){
            threadDriver.remove();
        }
    }
}
