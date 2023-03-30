package Core;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;


public class ActionHandler
{
    public static WebDriver webDriver;

    @BeforeMethod
    public void initDriver()
    {
        System.setProperty("webdriver.chrome.driver", "src//main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.isSuccess())
        {
            webDriver.quit();
        }
        else
        {
            takeScreenshot(result.getName());
            webDriver.quit();
        }
    }

    @Attachment(value = "Screenshot")
    public byte[] takeScreenshot(String name)
    {
        File screenShotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(screenShotFile, new File(".\\target\\screenshots\\" + name + ".png"));
        }
        catch (Exception ignored) {}
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}