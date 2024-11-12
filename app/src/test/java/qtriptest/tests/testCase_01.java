package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_01 extends BaseTest {

        static RemoteWebDriver driver;
        RegisterPage registerPage;
        static ExtentReports extentReports;
        static ExtentTest extentTests;
        LoginPage loginPage;
        HomePage homePage;
        String lastGenerateduser="";
        String lastUserPassword="";
        

        public static void logStatus(String type, String message, String status){

                System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()),type,message,status));
        }

        
        @BeforeTest(alwaysRun = true)
        public static void createDriver() throws MalformedURLException {
            
           DriverSingleton singleton = DriverSingleton.getDriverInstance();
           driver= singleton.getDriver();
           //driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
    
        //    extentReports = new ExtentReports(System.getProperty("user.dir")+"/OurExtentReport.html");
           extentReports = new ExtentReports(System.getProperty("user.dir") + "/OurExtentReport.html", true);

           extentReports.startTest("TestCase01");
    
        }

        @Test(priority=1,dataProvider = "data-provider",dataProviderClass = DP.class,description = "this registration login verifiying here",groups = {"Login Flow"},enabled=true)
	public void TestCase01(String userName,String password) throws MalformedURLException, InterruptedException {
                // System.out.println("driver "+ driver);
                // driver=singletonDriver.getIntsance();
                driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
                registerPage= new RegisterPage(driver);
                registerPage.navigateToRegister();
                logStatus("registration",userName,password);
                registerPage.registerNewUser(userName, password, password, true);
                lastGenerateduser= registerPage.lastgeneratedUsername;
                lastUserPassword=registerPage.lastUserPassword;
                System.out.println("Registraion done successfully..");
                logStatus("Login",userName,password);
                loginPage= new LoginPage(driver);
                loginPage.PerformLogin(lastGenerateduser,password);
                System.out.println("Login done successfully..");
                loginPage.performLogout();
       }


       
       public static void capture(RemoteWebDriver driver){
        File scr= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      }
       


      @AfterSuite
      public static void quitDriver() {
          System.out.println("quit()");
          driver.quit();
  
          extentReports.endTest(extentTests);
          extentReports.flush();
  
      }

       
}
