package qtriptest.pages;


import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HistoryPage {

    static RemoteWebDriver driver;
    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservationHistory;
    @FindBy(xpath = "//table//tbody//th")
    List<WebElement> reservationId;
    @FindBy(xpath="//table//tbody//tr[1]//td[8]")
    WebElement cancel;
    @FindBy(id = "no-reservation-banner")
    WebElement cancelCoformation;

    public HistoryPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String getReservation() throws InterruptedException{
        reservationHistory.click();
        Thread.sleep(2000);
        int n=reservationId.size();
        System.out.println("number of reservations "+n);
        String str=reservationId.get(0).getText();
        return str;
    }

    public void cancelReservation() throws InterruptedException{
        cancel.click();
        Thread.sleep(2000);
        Assert.assertTrue(cancelCoformation.isDisplayed(),"cancel not performed");
    }
}