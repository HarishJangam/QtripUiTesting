package qtriptest.pages;

//package com.crio.QKART_TestNG.pages;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumWrapper {

    // RemoteWebDriver driver;
    public static void wrap_sendKeys(WebElement e, String inputText)
    {
        //TODO: WRAPPER METHODS: MILESTONE 2 ACTIVITY
        System.out.println("Entering text after clearing the textbox");
        e.clear();
        e.sendKeys(inputText);
    }

    public static WebElement wrap_findElement(RemoteWebDriver driver, By b)
    {
        //TODO: WRAPPER METHODS: MILESTONE 2 ACTIVITY
        try{
           String text =  driver.findElement(b).getText();
        }
        catch (Exception e){
          takeScreenshot("Fail", "Faile to send Keys", driver);//
         
         //
         e.printStackTrace();
        }
        return driver.findElement(b);
        
    }

    public static void takeScreenshot(String screenshotType, String Description, TakesScreenshot driver) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, Description);
           // TakesScreenshot driver;
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void advancedClick(WebElement e)
    {
        //TODO: Wrapper METHODS: Milestone 4 Activity
        int x = e.getLocation().getX();
        int y = e.getLocation().getY();
        System.out.println("x coordinat"+x );
        System.out.println("y coordinat"+y );
       
        if(e.isDisplayed()){
            System.out.println("it is present ");
        }
        else{
            System.out.println("its not present");
        }
        e.click();
    }

    public static void advancedSendkeys(WebElement e , String inputText)
    {
         
         e.clear();
         e.sendKeys(inputText);
        }


}