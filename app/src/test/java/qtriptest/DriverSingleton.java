package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
    
private static RemoteWebDriver driver;
private static DriverSingleton instanceOfDriverSingletonClass;
private DriverSingleton() throws MalformedURLException{
    final DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(BrowserType.CHROME);
    driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
   // System.out.println("createDriver()");
     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

}

public static  DriverSingleton  getDriverInstance() throws MalformedURLException{
    if(instanceOfDriverSingletonClass==null){
        instanceOfDriverSingletonClass= new DriverSingleton();
       

    }
    return instanceOfDriverSingletonClass;

}

public RemoteWebDriver getDriver(){
    return driver;
}
}
