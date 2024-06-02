package com.confluxsys.dsp.automation.utills;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssertStatements extends  Initialization  {

    public void  waitForElementPresentBy_Xpath(String type, String value)
    {   wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by xpath is not present");
    }
    public void  waitForElementPresentBy_CSS(String type,String value)
    {
        wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by css is not present");
    }
    public void  waitForElementPresentBy_ID(String type,String value)
    {
        wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by ID is not present");
    }
    public void  waitForElementPresentBy_TagName(String type,String value)
    {

        wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by TagName is not present");
    }
    public void  waitForElementPresentBy_Name(String type,String value)
    {
        wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by Name is not present");
    }
    public void  waitForElementPresentBy_ClassName(String type,String value)
    {
        wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by className is not present");
    }
    public void  waitForElementPresentBy_LinkText(String type,String value)
    {

        wait=getWait();
        softAssert=getSoftAssert();
        wait.until(ExpectedConditions.visibilityOf(getWebElement(type,value)));
        softAssert.assertTrue(getWebElement(type,value).isDisplayed(), "Element located by linkText is not present");
    }

}
