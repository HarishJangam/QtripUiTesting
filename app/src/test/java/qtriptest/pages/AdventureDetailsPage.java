package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventureDetailsPage {

    static RemoteWebDriver driver;
    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservationHistory;
    @FindBy(xpath = "//table//tbody//th")
    WebElement reservationId;
    @FindBy(xpath="//table//tbody//tr[1]//td[8]")
    WebElement cancel;

    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void checkingReservation(){
        
    }
}