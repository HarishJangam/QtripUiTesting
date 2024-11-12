package qtriptest.pages;

import java.util.List;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field.Xpath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HomePage {

    RemoteWebDriver driver;

    @FindBy(id="autocomplete")
    private WebElement searchMyCity;
     
    @FindBy(xpath="//ul[@id='results']/a/li")
    private WebElement selectResult;

    @FindBy(xpath="//ul[@id='results']//h5")
    private WebElement noCityFound;

    @FindBy(xpath = "//a[text()='Home']")
    private WebElement homePage;

    @FindBy(xpath = "//select[@name='duration']")
    private WebElement hours;
    @FindBy(xpath = "//*[@id='duration-select']/option[1]")
    private WebElement verifyHours;
    @FindBy(xpath = "//*[@onclick='clearDuration(event)']")
    private WebElement clearHours;
    @FindBy(xpath="//select[@id='duration-select']//option")
    List<WebElement>hoursResults;

    @FindBy(xpath = "//*[@id='category-select']")
    private WebElement addCategory;
    @FindBy(xpath = "//*[@id='category-select']/option[1]")
    private WebElement verifyCategory;
    @FindBy(xpath = "//select[@id='category-select']//option")
    List<WebElement>categoryResults;
    @FindBy(xpath="//*[@onclick='clearCategory(event)']")
    private WebElement clearCategory;

    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement>specifiedLocationResults;

    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement>LocationResults;

    @FindBy(xpath="//div[@class='activity-card']")
    private WebElement resultsVerify;

    public HomePage(RemoteWebDriver driver){
        
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15), this); //new AjaxElementLocatorFactory(driver,15)
    }

    public void clickRegister(){

    }

    public Boolean isUserLoggedIn(){
        return null;        
    }

    public void logOutUser(){

    }
    public void navigateToHomePage(){
        homePage.click();
    }

    public void searchCity(String city) throws InterruptedException{

        Thread.sleep(2000);
        searchMyCity.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        searchMyCity.sendKeys(city);
       
     }

     public boolean selectCity(String city)throws InterruptedException{
        boolean status=false;
        Thread.sleep(2000);

        if(selectResult.getText().equalsIgnoreCase(city)){
            selectResult.click();
            System.out.println("Searched city found and clicked");
            Thread.sleep(3000);
            status=true;
        }
        return status;
    }
    public void addFeatures(String category,String duration,String filter,String withoutFilter)throws InterruptedException{ 
           
            hours.click();
            Thread.sleep(1000);

            for(WebElement we:hoursResults){
                String str=we.getText();
                if(str.equals(duration)){
                    we.click();
                    Thread.sleep(2000);
                }
            }
            Assert.assertTrue(verifyHours.isDisplayed(),"hours are not verified");
            // clearHours.click();

            addCategory.click();
            Thread.sleep(1000);
           
            for(WebElement we:categoryResults){
                String str=we.getText();
                if(str.equals(category)){
                    we.click();
                    Thread.sleep(2000);
                }
            }

            int filterLen=specifiedLocationResults.size();
            System.out.println("withoutFilterLen"+filterLen);
            Assert.assertEquals(filterLen, Integer.valueOf(filter),"after applaying of filter it is failed");

            clearHours.click();
            clearCategory.click();
            Thread.sleep(1000);
            int withoutFilterLen=LocationResults.size();
            System.out.println("withoutFilterLen"+withoutFilterLen);
            Assert.assertEquals(withoutFilterLen,Integer.valueOf(withoutFilter),"failed at verified of without filter");

            // Assert.assertTrue(verifyCategory.isDisplayed(),"category is not verified");
            // // clearCategory.click();
            // // if(resultsVerify.isDisplayed()){
            // //     System.out.println("items are displayed done");
            // // }
            // Assert.assertTrue(resultsVerify.isDisplayed(),"results are not verified");

        }

     
}

