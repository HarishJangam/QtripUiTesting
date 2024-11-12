package qtriptest.pages;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class LoginPage {

    RemoteWebDriver driver;
    @FindBy(className ="nav-link login")    
    WebElement loginHere;
    @FindBy(name="email")
    WebElement emailTextBox;
    @FindBy(name="password")
    WebElement passwordTextBox;
    @FindBy(xpath  ="//button[text()='Login to QTrip']")
    WebElement loginBtn;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logout;

    public LoginPage(RemoteWebDriver driver){

        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    } 

    public void navigateToLogin(){
        loginHere.click();
    }
    public void PerformLogin(String userName,String Password) throws InterruptedException{
      
        System.out.println(userName);
        emailTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(Password);
        loginBtn.click();
        Thread.sleep(4000);
        System.out.println("Logged-in successfully..");
        // logout.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("frontend.vercel.app"), "Unable to navigate Login page");
        // System.out.println("Logout successfully..");
    }

    public void performLogout(){
        logout.click();
    }
    
}
