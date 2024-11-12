// package qtriptest;

// public class ReportSingleton {
// }
package qtriptest;

// import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.relevantcodes.extentreports.ExtentReports;


public class ReportSingleton {

private static ReportSingleton instanceOfExtentReportSingleton=null;
private ExtentReports report;

// public static String getTimestamp(){
// Timestamp timestamp= new Timestamp(System.currentTimeMillis());
// return String.valueOf(timestamp.getTimestamp());
// }

    private String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }
private ReportSingleton(){
    // report=new ExtentReports(System.getProperty(("user.dir")+"/report"+getTimestamp()+".html"),true);
    String filePath = System.getProperty("user.dir") + "/report" + getTimestamp() + ".html";
    System.out.println("File path: " + filePath); // Add this line for debugging
    report = new ExtentReports(filePath, true);
}


public static ReportSingleton getInstanceOfReportSingleton(){
    if(instanceOfExtentReportSingleton==null){
        instanceOfExtentReportSingleton=new ReportSingleton();
    }
    return instanceOfExtentReportSingleton;
}

public ExtentReports getReports(){
    return report;
}

}