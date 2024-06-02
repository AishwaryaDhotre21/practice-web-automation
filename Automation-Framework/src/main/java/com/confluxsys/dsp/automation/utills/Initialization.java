package com.confluxsys.dsp.automation.utills;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.markuputils.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.testng.asserts.SoftAssert;

public class Initialization  {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> driverwaitThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> reporterThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<SoftAssert> softAssertThreadLocal = new ThreadLocal<>();
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssert softAssert;
    protected ExtentSparkReporter sparkReporter;
    protected  ExtentReports extent;
    protected ExtentTest reporter;
    protected ExtentSparkReporter sparkReporter_1;
    protected static ExtentReports extent_1; //static is necessary here
    protected static ExtentTest reporter_1;
    private static int i=0;

    String folderPath=System.getProperty("user.dir") + File.separator + "report" + File.separator;
    String filePath="Report_";
    String combineFilePath;
    String combineFileName;
    String completeFilePathWithEXT;
    String newClassName;
    PropertiesReader objPropReader=new PropertiesReader();
    protected Set<String> set = new HashSet<>();
    protected static   Set<String> set1 = new HashSet<>();

    @BeforeSuite
    public void beforeSuite()
    {
        sparkReporter_1 = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "report" + File.separator + "FailledTestCases"+".html");
        extent_1 = new ExtentReports();
        extent_1.attachReporter(sparkReporter_1);
        sparkReporter_1.config().setTheme(Theme.STANDARD);
        sparkReporter_1.config().setDocumentTitle("AutomationReport-Failed Test cases");
        sparkReporter_1.config().setReportName("Automation test Results- Failed Test cases");
    }
    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) {
        String homePageLink=objPropReader.getProperty("homePageLink");
        System.out.println("Name:-"+homePageLink);
        launch_browser(browser);
        driver=getDriver();
        driver.get(homePageLink);
        UtilityMethods obj=new UtilityMethods();
        obj.login();
        i++;
        System.out.println("value of i:="+ i);
        if(i!=0) {

            //   sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "report" + File.separator + "Report_"+i+".html");
            combineFileName=filePath+i;
            combineFilePath=folderPath + combineFileName;
            completeFilePathWithEXT=combineFilePath+".html";
            System.out.println("Full name:-"+completeFilePathWithEXT);
            sparkReporter = new ExtentSparkReporter(completeFilePathWithEXT);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("AutomationReport");
            sparkReporter.config().setReportName("Automation test Results");
        }

    }
    @BeforeMethod
    public void beforeMethod(Method testMethod) {
        Test test= testMethod.getAnnotation(Test.class);
        reporter =extent.createTest("Test case:-"+test.testName()+"<br>Description:-"+test.description());
        reporterThreadLocal.set(reporter);
    }
    @AfterMethod
    public void afterMethod(ITestResult result,Method testMethod)
    {
        reporter=getReporter();
        Test test=testMethod.getAnnotation(Test.class);
        String className=result.getTestClass().getName();
        newClassName=create_canonical_class_name(className);

        if(result.getStatus() == ITestResult.FAILURE)
        {
            reporter.log(Status.FAIL,"<b>Function Name :"+result.getName()+"</b>");// prints the function name
            reporter.fail(result.getThrowable());// prints the exception raised
            reporter_1=extent_1.createTest("Test case Name:-"+test.testName()+"<br>Description:-"+test.description()+"<br>Method name:"+result.getMethod().getMethodName());
            reporter_1.log(Status.FAIL,"<b>Function Name :"+result.getName()+"</b>");
            reporter_1.fail(result.getThrowable());
       /*     reporter_1.log(Status.INFO,MarkupHelper.createLabel(result.getTestClass().getName()+"  Test Class Name", ExtentColor.ORANGE));
            reporter_1.info(MarkupHelper.createLabel(result.getTestClass().getRealClass()+" - Test Class Name",ExtentColor.ORANGE));
            reporter_1.info(MarkupHelper.createLabel(result.getInstance().getClass().getCanonicalName()+" - Test Class Name",ExtentColor.ORANGE));*/
            reporter_1.info("<b>Test class Name:- "+result.getTestClass().getName()+"</b>");
            //  reporter_1.info("Another try:-"+testMethod.getDeclaringClass().getCanonicalName());
            reporter_1.info("<b>Canonical Class name:- " + newClassName+"</b>");
            String filenamewithext=newClassName+".html";
            set.add(newClassName);
            set1.add(filenamewithext);
            System.out.println("*******File name:-"+filenamewithext);
            // testMethod.getDeclaringClass().
        }
        else if(result.getStatus() == ITestResult.SKIP)
        {
            reporter.log(Status.SKIP,"<b>Function Name:- "+result.getName()+"</b>");
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            reporter.log(Status.PASS,"<b>Function Name:- "+result.getName()+"</b>");
        }
        reporter.log(Status.INFO,"<b>Test Class Name:- "+result.getTestClass().getName()+"</b>");

        reporter.info("<b>Canonical Class name:- " + newClassName+"</b>");
        // result.getTestClass().getRealClass();-
    }

    @AfterClass
    public void afterClass()    //@AfterTest --can be used need to configure properly in TestNG.xml
    {
        extent.flush();
        System.out.println("After Class retrieved name:-"+completeFilePathWithEXT);
        System.out.println("Combine File name:"+combineFileName);
        System.out.println("Combine File Path:-"+combineFilePath);
        System.out.println("New Class name:-"+newClassName);
        File oldFile=new File(completeFilePathWithEXT);
        File newFile=new File(folderPath+newClassName+".html");
        if(oldFile.renameTo(newFile))
        {
            System.out.println("File renamed successfully");
        }
        else {
            System.out.println("Error in file renaming");
        }
        //obj.logout();
        quite_browser();
        softAssert=softAssertThreadLocal.get();
        softAssert.assertAll();
    }

    @AfterSuite
    public void afterSuite()
    {
        extent_1.flush();
        System.out.println("Failed class names in set:-\n\n");
        for(String s:set1)
        {
            System.out.println(s);
        }
    }

    public void launch_browser(String Browser)  {
       // String Browser=objPropReader.getProperty("Browser");
        System.out.println("Name:-"+Browser);

        if(Browser.equalsIgnoreCase("Chrome")) {
        //    WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + File.separator + "drivers" + File.separator+"chromedriver.exe");
         //   System.setProperty("webdriver.chrome.driver", "C:\\Users\\Confluxsys\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions o= new ChromeOptions();
            o.addArguments("−−incognito");
            driver = new ChromeDriver(o);
            driverThreadLocal.set(driver);
        }
        else if (Browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
            driverThreadLocal.set(driver);
        }
        else if(Browser.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
            driverThreadLocal.set(driver);
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        softAssert=new SoftAssert();
        softAssertThreadLocal.set(softAssert);
        wait=new WebDriverWait(driver,20);
        driverwaitThreadLocal.set(wait);
    }

    public WebElement getWebElement(String identifierType, String identifierValue)
    { driver=getDriver();
        switch (identifierType)
        {
            case "XPATH":
                return driver.findElement(By.xpath(identifierValue));
            case "CSS":
                return driver.findElement(By.cssSelector(identifierValue));
            case "ID":
                return driver.findElement(By.id(identifierValue));
            case "NAME":
                return driver.findElement(By.name(identifierValue));
            case "TAGNAME":
                return driver.findElement(By.tagName(identifierValue));
            case "CLASSNAME":
                return driver.findElement(By.className(identifierValue));
            case "LINKTEXT":
                return driver.findElement(By.linkText(identifierValue));
            default:
                return null;
        }
    }
    public List<WebElement> getWebElements(String identifierType, String identifierValue)
    {driver=getDriver();
        switch (identifierType)
        {
            case "XPATH":
                return driver.findElements(By.xpath(identifierValue));
            case "CSS":
                return driver.findElements(By.cssSelector(identifierValue));
            case "ID":
                return driver.findElements(By.id(identifierValue));
            case "NAME":
                return driver.findElements(By.name(identifierValue));
            case "TAGNAME":
                return driver.findElements(By.tagName(identifierValue));
            case "CLASSNAME":
                return driver.findElements(By.className(identifierValue));
            case "LINKTEXT":
                return driver.findElements(By.linkText(identifierValue));
            default:
                return null;
        }
    }
    public void quite_browser()
    {
        reporter.log(Status.INFO,"Closing the browser window");
         driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
    public String create_canonical_class_name(String className)
    {
        String[] array=className.split("\\.");
        System.out.println(array);
        String newClassName=array[array.length-1];
        System.out.println("Class Name:-"+newClassName);
        return newClassName;
    }


    public static ExtentReports getExtentInstance()
    {
        return extent_1;
    }

    public WebDriver getDriver()
    {
        return driverThreadLocal.get();
    }

    public Set<String> getSet()
    {
        return set1;
    }

    public WebDriverWait getWait()
    {
        return driverwaitThreadLocal.get();
    }

    public ExtentTest getReporter(){return reporterThreadLocal.get();}
    public SoftAssert getSoftAssert()
    {
        return softAssertThreadLocal.get();
    }
}
