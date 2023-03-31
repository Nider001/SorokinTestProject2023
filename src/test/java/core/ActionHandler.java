package core;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;


public class ActionHandler
{
    protected WebDriver driver;

    @BeforeMethod(description = "Start browser")
    @Parameters({"browser"})
    public void initDriver(int browser)
    {
        if (browser == 1) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\java\\core\\drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        else if (browser == 2)
        {
            System.setProperty("webdriver.gecko.driver", "src\\test\\java\\core\\drivers\\geckodriver.exe");
            FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(binary);
            //options.addArguments("--profile=c:\\Users\\Andrey\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\o94ooh61.default-release\\");
            driver = new FirefoxDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("Browser was successfully opened.");
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowserOrScreenshotOnFailure(ITestContext result)
    {
        takeScreenshot(result.getName());
        driver.quit();

        if (result.getFailedTests().size() == 0)
        {
            System.out.println("Browser was successfully closed.");
        }
        else
        {
            System.out.println("Test failed. Check the screenshot for details.");
        }
    }

    @Attachment(value = "Screenshot")
    public byte[] takeScreenshot(String name)
    {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(screenshotFile, new File(".\\target\\screenshots\\" + name + ".png"));
        }
        catch (Exception ignored) {}
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}