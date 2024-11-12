package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventurePage {

    RemoteWebDriver driver;
    @FindBy(name="search-adventures")
    private WebElement adventureTextBox;

    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement>listOfAdventures;
    
    @FindBy(xpath="//input[@name='name']")
    WebElement nameTextBox;

    @FindBy(xpath="//input[@name='date']")
    WebElement dateTextBox;
    //unable to find xpath for date
    @FindBy(xpath="//input[@name='person']")
    WebElement personCount;

    @FindBy(xpath = "//button[@class='reserve-button']")
    WebElement reserve;

    public AdventurePage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdventure(String adventureName,String guestName,String date,String count,boolean flag) throws InterruptedException{
        if(flag)
            adventureTextBox.sendKeys("adventure");
        Thread.sleep(4000);
        for(WebElement parent:listOfAdventures){
            String str=parent.findElement(By.xpath(".//h5")).getText();
            System.out.println(str);
            if(str.equals(adventureName)){
                parent.click();
                Thread.sleep(3000);
                nameTextBox.sendKeys(guestName);
                dateTextBox.sendKeys(date);
                personCount.clear();
                personCount.sendKeys(count);
                reserve.click();
                Thread.sleep(2000);
                break;
            }
        }
    }
}