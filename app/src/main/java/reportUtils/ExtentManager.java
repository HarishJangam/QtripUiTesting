package reportUtils;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    static ExtentReports extentReoprts;
    final static String reportFilePath= System.getProperty("User.dir")+File.separator+"reports"+File.separator+"QTripReports.html";


    public static synchronized ExtentReports getReport(){
        if(extentReoprts==null){
            extentReoprts=new ExtentReports(reportFilePath,true);
        }
        return extentReoprts;
    } 
}
