package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class RegisterPage {

    RemoteWebDriver driver;
    public static String lastgeneratedUsername;
    public static String lastUserPassword;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement toRegisterBtn;
    @FindBy(id="floatingInput")
    WebElement emailTextBox;
    @FindBy(name ="password")
    WebElement passwordTextBox;
    @FindBy(name="confirmpassword")
    WebElement confirmPasswordTextBox;
    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registerBtn;



    public RegisterPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    

    public void registerNewUser(String userName,String password,String confirmPassword,boolean makeUserDynamic) throws InterruptedException{

        toRegisterBtn.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/register"), "Unable to navigate Register page");
    
            
        if (makeUserDynamic == true)
            userName = userName+UUID.randomUUID().toString();
        emailTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(password);
        confirmPasswordTextBox.sendKeys(confirmPassword);
        registerBtn.click();
        
        Thread.sleep(4000);

        RegisterPage.lastgeneratedUsername=userName;
        RegisterPage.lastUserPassword=password;
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Unable to navigate Login page");
     
    }

    public void navigateToRegister(){
        toRegisterBtn.click();
    }
}
