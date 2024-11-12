package reportUtils;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestManager {
  static ExtentReports extentReports= ExtentManager.getReport();  
  static ExtentTest extentTest;

public static synchronized ExtentTest startTest(String testName){
     extentTest=extentReports.startTest(testName);
    return extentTest;

}

public static synchronized void testLogger(LogStatus status, String description){
     extentTest.log(status, description);
}

public static synchronized void endTest(){
    extentReports.endTest(extentTest);
}
}