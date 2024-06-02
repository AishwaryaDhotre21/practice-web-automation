package com.confluxsys.dsp.automation.utills;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static com.confluxsys.dsp.automation.utills.Initialization.reporter_1;
import com.confluxsys.dsp.automation.utills.Initialization;
public class SuiteListener  implements ITestListener, IAnnotationTransformer {

   Initialization objInitialization=new Initialization();
   WebDriver driver;
   /* public void onTestStart(ITestResult result)
    {
        if(!result.isSuccess())
        {
            ExtentReports extent =objInitialization.getExtentInstance();
            reporter_1=extent.createTest(result.getMethod().getMethodName()+"Method name");
        }

    }*/

    public void onTestFailure(ITestResult result)
    {
        driver= objInitialization.getDriver();
     // ExtentReports extent =objInitialization.getExtentInstance();
      //  reporter_1=extent.createTest(result.getMethod().getMethodName()+"Method name"); //It is working correctly & require static extent object only then it will work
        String filename=System.getProperty("user.dir")+ File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
      //  File f1=((TakesScreenshot)Initialization.driver).getScreenshotAs(OutputType.FILE);
        File f1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f1,new File(filename+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
