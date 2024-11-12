package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 extends BaseTest{
    static RemoteWebDriver driver;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    AdventurePage adventurePage;
    HistoryPage historyPage;
    String lastGenerateduser="";
    String lastUserPassword="";

    static ExtentReports extentReports;
    static ExtentTest extentTests;

   

    @BeforeTest(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        
       DriverSingleton singleton = DriverSingleton.getDriverInstance();
       driver= singleton.getDriver();
       driver.manage().window().maximize();
       //driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
       
       extentReports = new ExtentReports(System.getProperty("user.dir")+"/OurExtentReport.html");
       extentReports.startTest("TestCase03");
    }

    public static void logStatus(String type, String message, String status){

        System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()),type,message,status));
    }

    @Test(priority=3,dataProvider = "data-provider",dataProviderClass = DP.class,description = "Booking and Cancellation Flow",groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String userName,String password,String cityName,String adventureName,String guestName,String date,String count) throws MalformedURLException, InterruptedException{

        logStatus("TestCase03 ", "Started", "done");
        // driver=singletonDriver.getIntsance();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        registerPage=new RegisterPage(driver);
        registerPage.navigateToRegister();
        registerPage.registerNewUser(userName, password, password, true);
        lastGenerateduser=registerPage.lastgeneratedUsername;
        loginPage=new LoginPage(driver);
        loginPage.PerformLogin(lastGenerateduser, password);
        homePage=new HomePage(driver);
        homePage.searchCity(cityName);
        homePage.selectCity(cityName);
        // homePage.addFeatures(category, duration, filter, withoutFilter);
        adventurePage=new AdventurePage(driver);
        System.out.println("into selectAdventures");
        adventurePage.selectAdventure(adventureName, guestName, date, count,true);
        System.out.println("into historypage");
        historyPage=new HistoryPage(driver);
        String str=historyPage.getReservation();
        System.out.println(str);
        historyPage.cancelReservation();
        loginPage.performLogout();
        Thread.sleep(3000);
    }


    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();

        extentReports.endTest(extentTests);
        extentReports.flush();

    }

}
