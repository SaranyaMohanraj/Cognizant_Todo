package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.*;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class BaseTestClass {
    protected WebDriver driver;
    protected ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
    protected ExtentReports extent = new ExtentReports();
    protected String browser = ConfigReader.getProperty("browser").toLowerCase();
    String baseUrl = ConfigReader.getProperty("base.url");
  

    @BeforeClass
    public void setUp() {
    	ExtentTest test = extent.createTest("Launch Todo");
        extent.attachReporter(sparkReporter);

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("chrome.driver.path"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", ConfigReader.getProperty("edge.driver.path"));
            driver = new EdgeDriver();
        } 

        driver.get(baseUrl);
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "Page_launch");
        test.pass("Page Launch Successful").addScreenCaptureFromPath(screenshotPath);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extent.flush();
        }
    }
}
