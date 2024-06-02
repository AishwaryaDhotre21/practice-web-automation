package com.confluxsys.dsp.automation.testcases.accessreview;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.confluxsys.dsp.automation.utills.Initialization;
import com.confluxsys.dsp.automation.utills.SuiteListener;
import com.confluxsys.dsp.automation.utills.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class DemoClass_1 extends Initialization
{
    UtilityMethods obj=new UtilityMethods();

    @Test(priority = 1,testName = "Function - 1", description = "description 1")
    public void fun_1()
    {
        reporter=getReporter();
        reporter.log(Status.INFO,"function 1");
        obj.clickAccessReviewLink();
    }
    @Test(priority = 2,testName = "Function - 2", description = "description 2")
    public void fun_2()
    {
        reporter=getReporter();
        reporter.log(Status.INFO,"function 2");
        obj.clickAccessReviewLink();
    }
}
