package com.confluxsys.dsp.automation.testcases.uaradministration;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.confluxsys.dsp.automation.implementation.UarAdministrationCertificationDashboardPage;
import com.confluxsys.dsp.automation.objectRepository.UarAdministration;
import com.confluxsys.dsp.automation.utills.Initialization;
import com.confluxsys.dsp.automation.utills.UtilityMethods;
import org.testng.annotations.Test;

public class CampaignCreationTestCases_1 extends Initialization
{
    UtilityMethods obj=new UtilityMethods();
    UarAdministrationCertificationDashboardPage objuarPage=new UarAdministrationCertificationDashboardPage();


    @Test(priority = 1,testName = "Create_Campaign-with empty parameters", description = "Campaign creation with empty parameters")
    public void campaignWithEmptyParameters_Function() throws InterruptedException {
        reporter=getReporter();
        obj.clickUarAdministrationLink();
        reporter.log(Status.INFO,"Verify the certification Dashboard title");
        objuarPage.verifyCertificationDashboardTitle();
        reporter.info("Step- Create Campaign");
        objuarPage.emptyParametersCampaignCreation();
    }

    @Test(priority = 2,testName = "Create_Campaign-with empty parameters- Assertion Fail", description = "Campaign creation with empty parameters failed assertions")
    public void campaignWithEmptyParameters_Function_fail() throws InterruptedException {
        reporter=getReporter();
        obj.clickUarAdministrationLink();
        reporter.log(Status.INFO,"Verify the certification Dashboard title");
        objuarPage.verifyCertificationDashboardTitle();
        reporter.info("Step- Create Campaign");
        objuarPage.emptyParametersCampaignCreation_failedSoftAssertions();
    }
    @Test(priority = 3,testName = "Test the Assertstatements class", description = "only check the assert class functionality")
    public void testAssertclassMethods() throws InterruptedException {
        reporter=getReporter();
        obj.clickUarAdministrationLink();
        reporter.log(Status.INFO,"Verify the certification Dashboard title");
        objuarPage.verifyCertificationDashboardTitle();
        objuarPage.testAssertClass();
    }

}
