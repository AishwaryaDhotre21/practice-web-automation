package com.confluxsys.dsp.automation.utills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilityMethods extends Initialization{
    WebDriver driver;
    AssertStatements objAssert=new AssertStatements();
    public void login()
    {
        driver=getDriver();
        PropertiesReader objPropertyReader=new PropertiesReader();
        String username=objPropertyReader.getProperty("unameval");
        String password=objPropertyReader.getProperty("passval");
        objAssert.waitForElementPresentBy_Xpath("XPATH","//*[@placeholder=\"User Name\"]");
        objAssert.waitForElementPresentBy_Xpath("XPATH","//*[@placeholder=\"Password\"]");
        objAssert.waitForElementPresentBy_Xpath("XPATH","//*[@value=\"Log in\"]");
        driver.findElement(By.xpath("//*[@placeholder=\"User Name\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@placeholder=\"Password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@value=\"Log in\"]")).click();
    }
    public void logout()
    {
        driver=getDriver();
        driver.findElement(By.xpath("//span[contains(text(),'pbule')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();
    }
    public void clickUarAdministrationLink()
    {
        driver=getDriver();
        driver.navigate().refresh();
        objAssert.waitForElementPresentBy_Xpath("XPATH","//span[contains(text(),'UAR Administration')]");
        driver.findElement(By.xpath("//span[contains(text(),'UAR Administration')]")).click();
    }
    public void clickAccessReviewLink()
    {
        driver=getDriver();
        driver.navigate().refresh();
        objAssert.waitForElementPresentBy_Xpath("XPATH","//span[contains(text(),'Access Reviews')]");
        driver.findElement(By.xpath("//span[contains(text(),'Access Reviews')]")).click();
    }
}
