package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 extends BaseTest{
    static RemoteWebDriver driver;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    String lastGenerateduser="";
    String lastUserPassword="";
    static ExtentReports extentReports;
    static ExtentTest extentTests;

    public static void logStatus(String type, String message, String status){

            System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()),type,message,status));
    }


    @BeforeTest(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton singleton = DriverSingleton.getDriverInstance();
        driver= singleton.getDriver();
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      //  driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
                    //  https://qtripdynamic-qa-frontend.vercel.app/
        //driver.manage().window().maximize();
        
       extentReports = new ExtentReports(System.getProperty("user.dir")+"/OurExtentReport.html");
       extentReports.startTest("TestCase02");
    }

    @Test(priority=2,dataProvider = "data-provider",dataProviderClass = DP.class,description = "search and filter verification",groups = {"Search and Filter flow"})
    public void TestCase02(String cityName,String category,String duration,String filter,String withoutFilter) throws InterruptedException, MalformedURLException{
        // driver=singletonDriver.getIntsance();
        System.out.println("driver"+driver);
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        HomePage homePage=new HomePage(driver);
        homePage.navigateToHomePage();
        homePage.searchCity(cityName);
        homePage.selectCity(cityName);
        homePage.addFeatures(category, duration, filter, withoutFilter);
    }

    // @AfterClass
    // public static void closDriver() throws MalformedURLException {
    //     driver.close();
    //     logStatus("driver", "closing Driver...", "Success");
    // }
    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();

        extentReports.endTest(extentTests);
        extentReports.flush();

    }
}
