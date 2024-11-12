package qtriptest.tests;

import reportUtils.ExtentManager;
import reportUtils.ExtentTestManager;
import java.lang.reflect.Method;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest implements {
    
  public static   ExtentReports extentReports;
  public static  ExtentTest extentTests;

    @BeforeSuite
    public static void config(){
        extentReports= new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentTestReport.html",true);
        extentTests= extentReports.startTest("QTrip Regression Suite");
    }



@BeforeMethod
public  static void beforeTestMethod(Method method){
   // ExtentTestManager.startTest(testName);
   ExtentTestManager.startTest(method.getName());
  
}
  
@AfterMethod
    public  static void afterTestMethod(ITestResult iTestResult){
        if(iTestResult.getStatus() == ITestResult.SUCCESS){
            ExtentTestManager.testLogger(LogStatus.PASS, "Step is passed");
        }
        else if(iTestResult.getStatus() == ITestResult.FAILURE){
            ExtentTestManager.testLogger(LogStatus.FAIL, iTestResult.getThrowable().getMessage());
        }else {
            ExtentTestManager.testLogger(LogStatus.SKIP , "Step is skipped");
        }
        ExtentTestManager.endTest();
    }

@AfterSuite(alwaysRun = true)
public  static void tearDown(){
ExtentManager.getReport().flush();
}
}
