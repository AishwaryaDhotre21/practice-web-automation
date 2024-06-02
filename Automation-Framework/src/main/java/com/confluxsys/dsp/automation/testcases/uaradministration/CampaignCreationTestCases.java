package com.confluxsys.dsp.automation.testcases.uaradministration;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.confluxsys.dsp.automation.implementation.UarAdministrationCertificationDashboardPage;
import com.confluxsys.dsp.automation.utills.Initialization;
import com.confluxsys.dsp.automation.utills.UtilityMethods;
import org.testng.annotations.Test;

public class CampaignCreationTestCases extends Initialization
{
    UarAdministrationCertificationDashboardPage objuarPage=new UarAdministrationCertificationDashboardPage();
    UtilityMethods obj=new UtilityMethods();


    @Test(priority = 1,testName = "Create campaign E2E Flow", description = "Create campaign End to end flow")
    public void Create_Campaign_E2E_Function() throws InterruptedException {
        reporter=getReporter();
      /*  objConfluxsysHomePage.verifyHomePage();
        objConfluxsysHomePage.clickUarAdministrationLink();*/
        obj.clickUarAdministrationLink();
        reporter.log(Status.INFO,"Verify the certification Dashboard title");
        objuarPage.verifyCertificationDashboardTitle();
        reporter.info("Step- Create Campaign");
        objuarPage.createCampaignMethod();
    }
    @Test(priority = 2,testName = " Create campaign 2", description = "test campaign create 2")
    public void Create_Campaign_E2E_failed_Function()
    {
        reporter=getReporter();
        obj.clickUarAdministrationLink();
        reporter.log(Status.INFO,"fun 2");
       // objConfluxsysHomePage.clickUarAdministrationLink();
        objuarPage.demo_test_fail_fun();
    }
    @Test(priority = 3,testName = " Create campaign 3", description = "test campaign create 3")
    public void fun3()
    {
        reporter=getReporter();
        reporter.log(Status.INFO,"fun 3");
        obj.clickUarAdministrationLink();
    }
}

