package com.confluxsys.dsp.automation.implementation;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.confluxsys.dsp.automation.utills.AssertStatements;
import com.confluxsys.dsp.automation.utills.Initialization;
import com.confluxsys.dsp.automation.objectRepository.UarAdministration;
import com.confluxsys.dsp.automation.utills.PropertiesReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class UarAdministrationCertificationDashboardPage extends Initialization implements UarAdministration
{
    String date;
    AssertStatements objAssert=new AssertStatements();
    PropertiesReader objPropReader=new PropertiesReader();


    public void verifyCertificationDashboardTitle() {
        reporter=getReporter();
        softAssert=getSoftAssert();
        String certificationDashboard=objPropReader.getProperty("certificationDashboard");
        // wait.until(ExpectedConditions.visibilityOf(getWebElement("XPATH",uarDashboardTitle)));
        objAssert.waitForElementPresentBy_Xpath("XPATH",uarDashboardTitle);
        String dashboardName=getWebElement("XPATH",uarDashboardTitle).getText();
        softAssert.assertEquals(dashboardName,certificationDashboard,"Dashboard title not matching");

    }

    public void demo_test_fail_fun()
    {
        reporter=getReporter();
        Assert.assertTrue(getWebElements("XPATH",actionButton).size()==0,"Element not found");
    }
    public void createCampaignMethod() throws InterruptedException {
        reporter=getReporter();
        String campaigName=objPropReader.getProperty("campaigName");
        reporter.log(Status.INFO,"Step- Clicking the create button");
//wait.until(ExpectedConditions.visibilityOf(getWebElement("XPATH",createButton)));
        objAssert.waitForElementPresentBy_Xpath("XPATH",createButton);
        Thread.sleep(3000);
        getWebElement("XPATH",createButton).click();
        reporter.log(Status.INFO,"Step- Passing the Campaign Name in CampaignName Text Field");
        getWebElement("XPATH",campaignNameField).sendKeys(campaigName);
        Thread.sleep(3000);
        reporter.log(Status.INFO,"Step- Entered Cmapign Name:DSP Automation Camp_1");
        reporter.log(Status.INFO,"Step- Passing the Campaign description in CampaignDescription Text Field");
        getWebElement("XPATH",campaignDescriptionField).sendKeys(new CharSequence[]{"Test demo Purpose"});
        reporter.log(Status.INFO,"Step- Clicking the search button");
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.visibilityOf(getWebElement("XPATH",searchButton)));
        objAssert.waitForElementPresentBy_Xpath("XPATH",searchButton);
        getWebElement("XPATH",searchButton).click();
        List<WebElement> List_ProgramName=getWebElements("XPATH",li_programName);
        Iterator var2 = List_ProgramName.iterator();

        while(var2.hasNext()) {
            WebElement el = (WebElement)var2.next();
            if (el.getText().equalsIgnoreCase("Annual Access Revalidation")) {
                String ProgramName = el.getText();
                reporter.log(Status.INFO,"Step- Choosen Program Name:" + ProgramName);
                el.click();
                break;
            }
        }

        reporter.log(Status.INFO,"Step- Clicking the submit button");
        // wait.until(ExpectedConditions.visibilityOf(getWebElement("XPATH",submitButton)));
        objAssert.waitForElementPresentBy_Xpath("XPATH",submitButton);
        getWebElement("XPATH",submitButton).click();
        reporter.log(Status.INFO,"Step- Capturing the alert message");
        objAssert.waitForElementPresentBy_Xpath("XPATH",confirmationBox);
        String msg = getWebElement("XPATH",confirmDataAlert).getText();
        reporter.log(Status.INFO,"Step- Captured Alert Message:- " + msg);
        reporter.log(Status.INFO,"Step- Clicking the confirm button of Alert Box");
        getWebElement("XPATH",alertConfirmButton).click();
        Thread.sleep(3000);
        reporter.log(Status.INFO,"Capture the created campaign timestamp details");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        reporter.log(Status.INFO,"Campaign Creation time:" + dtf.format(now));
        Thread.sleep(5000);
        getWebElement("XPATH",okButton).click();
    }

    public void emptyParametersCampaignCreation()
    {
        reporter=getReporter();
        objAssert.waitForElementPresentBy_Xpath("XPATH",createButton);
        getWebElement("XPATH",createButton).click();
        reporter.log(Status.INFO,"Step- Passing the TAB key in CampaignName Text Field");
        getWebElement("XPATH",campaignNameField).sendKeys(Keys.TAB);
        getWebElement("XPATH",campaignNameField).sendKeys(Keys.ENTER);
        reporter.log(Status.INFO,"Step- Entered Cmapign Name:DSP Automation Camp_1");
        reporter.log(Status.INFO,"Step- Passing theTAB key in CampaignDescription Text Field");
        getWebElement("XPATH",campaignDescriptionField).sendKeys(Keys.TAB);
        getWebElement("XPATH",campaignDescriptionField).sendKeys(Keys.ENTER);
        reporter.log(Status.INFO,"Step- Clicking the submit button");
        // wait.until(ExpectedConditions.visibilityOf(getWebElement("XPATH",submitButton)));
        objAssert.waitForElementPresentBy_Xpath("XPATH",submitButton);
        getWebElement("XPATH",submitButton).click();

    }
    public void emptyParametersCampaignCreation_failedSoftAssertions()
    {
        reporter=getReporter();
        softAssert=getSoftAssert();
        objAssert.waitForElementPresentBy_Xpath("XPATH",createButton);
        getWebElement("XPATH",createButton).click();
        reporter.log(Status.INFO,"Step- Passing the TAB key in CampaignName Text Field");
        getWebElement("XPATH",campaignNameField).sendKeys(Keys.TAB);
        getWebElement("XPATH",campaignNameField).sendKeys(Keys.ENTER);
        reporter.log(Status.INFO,"Step- Entered Cmapign Name:DSP Automation Camp_1");
        reporter.log(Status.INFO,"Step- Passing theTAB key in CampaignDescription Text Field");
        getWebElement("XPATH",campaignDescriptionField).sendKeys(Keys.TAB);
        getWebElement("XPATH",campaignDescriptionField).sendKeys(Keys.ENTER);
        reporter.log(Status.INFO,"Step- Clicking the submit button");
        // wait.until(ExpectedConditions.visibilityOf(getWebElement("XPATH",submitButton)));
        objAssert.waitForElementPresentBy_Xpath("XPATH",submitButton);
        getWebElement("XPATH",submitButton).click();


        softAssert.assertEquals(getWebElement("XPATH",campaignNameError).getText(),"Campaign name is not required");
        softAssert.assertEquals(getWebElement("XPATH",descriptionError).getText(),"Campaign Description is not required");
    }

    public void testAssertClass()
    {
        reporter=getReporter();
        objAssert.waitForElementPresentBy_Xpath("XPATH","//td[contains(text(),'dsp workbook 2')]");
    }
}
