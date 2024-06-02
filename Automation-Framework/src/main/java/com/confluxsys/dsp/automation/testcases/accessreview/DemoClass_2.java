package com.confluxsys.dsp.automation.testcases.accessreview;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.confluxsys.dsp.automation.utills.Initialization;
import com.confluxsys.dsp.automation.utills.SuiteListener;
import com.confluxsys.dsp.automation.utills.UtilityMethods;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class DemoClass_2 extends Initialization {
    UtilityMethods obj=new UtilityMethods();


    @Test(priority = 1,testName = "Function name 1", description = "description 1")
    public void demo1()
    {
        reporter=getReporter();
        obj.clickAccessReviewLink();
        reporter.log(Status.INFO,"function 1");
    }
    @Test(priority = 2,testName = "Function name 2", description = "description 2")
    public void demo2()
    {
        reporter=getReporter();
        obj.clickUarAdministrationLink();
        reporter.log(Status.INFO,"function 2");
    }
}
