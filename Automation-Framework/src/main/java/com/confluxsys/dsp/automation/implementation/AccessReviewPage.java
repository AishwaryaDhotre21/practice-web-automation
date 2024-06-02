package com.confluxsys.dsp.automation.implementation;

import com.aventstack.extentreports.ExtentTest;
import com.confluxsys.dsp.automation.objectRepository.AccessReview;
import com.confluxsys.dsp.automation.utills.Initialization;
import org.testng.Assert;

public class AccessReviewPage extends Initialization implements AccessReview
{

    public void checkSoftAssert_1()
    {
        reporter=getReporter();
        softAssert=getSoftAssert();
        reporter.info("checking the soft assert");
        softAssert.assertTrue(accessReviewDashbaordName.equalsIgnoreCase("Access Reviews False"),"Wrong dashbaord name - Soft Assert");
    }

    public void checkHardAssert_1()
    {
        reporter=getReporter();
        reporter.info("checking the hard assertions");
        Assert.assertTrue(accessReviewDashbaordName.equalsIgnoreCase("Access Reviews False"),"Wrong dashbaord name - Hard Assertion");;
    }

}
