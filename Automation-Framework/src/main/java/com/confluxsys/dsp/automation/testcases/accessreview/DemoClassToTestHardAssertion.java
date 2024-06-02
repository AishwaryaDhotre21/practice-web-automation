package com.confluxsys.dsp.automation.testcases.accessreview;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.confluxsys.dsp.automation.implementation.AccessReviewPage;
import com.confluxsys.dsp.automation.utills.Initialization;
import com.confluxsys.dsp.automation.utills.UtilityMethods;
import org.testng.annotations.Test;

public class DemoClassToTestHardAssertion extends Initialization {
    UtilityMethods obj=new UtilityMethods();
    AccessReviewPage objAccessReviewPage=new AccessReviewPage();


    @Test(priority = 1,testName = "Check the Hard assert", description = "desc-test soft assert object")
    public void demo_call_1() throws InterruptedException {
        reporter=getReporter();
        obj.clickAccessReviewLink();
        objAccessReviewPage.checkHardAssert_1();
    }

    @Test(priority = 2,testName = "demo 2", description = "description 2")
    public void demo_call_2()
    {
        reporter=getReporter();
        reporter.log(Status.INFO,"function 2");
        obj.clickAccessReviewLink();
    }
}
