package Pages;

import Core.ActionHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage extends ActionHandler
{
    //WebElement deliveryInfo;

    public CartPage(/*WebDriver driver*/)
    {
        //deliveryInfo = driver.findElement(By.className("_2YHTmhZmt4"));
    }

    @Step
    public void CheckDeliveryNotFree(WebDriver driver)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[contains(text(),'бесплатной доставки осталось')]")).isDisplayed();
            }
        });
    }

    @Step
    public void CheckDeliveryFree(WebDriver driver)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[text() = 'бесплатную доставку']")).isDisplayed();
            }
        });
    }

    @Step
    public void CheckFinalCost(WebDriver driver)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("_2W4X8tX6r0")).isDisplayed();
            }
        });

        WebElement checkoutButton = driver.findElement(By.className("_2W4X8tX6r0"));
        checkoutButton.click();

        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[contains(@class, '_3yvXLQs2ww')]")).isDisplayed();
            }
        });

        WebElement deliveryButton = driver.findElement(By.xpath("//div[contains(@class, '_3yvXLQs2ww')]"));

        deliveryButton.click();

        WebElement checkoutWindow = driver.findElement(By.className("_2g3X9mAQJd"));
        String initialCost = checkoutWindow.findElement(By.xpath("(//div[contains(@class, '_1Q9ASvPbPN')])[1]")).getText();
        String deliveryCost = checkoutWindow.findElement(By.xpath("(//div[contains(@class, '_1Q9ASvPbPN')])[2]")).getText();
        String finalCost = checkoutWindow.findElement(By.xpath("(//div[contains(@class, '_1Q9ASvPbPN')])[3]")).getText();

        int c1 = Integer.parseInt(initialCost.substring(initialCost.indexOf(')')).replaceAll("\\D", ""));
        int c2 = 0;
        if (deliveryCost != "бесплатно") {
            c2 = Integer.parseInt(deliveryCost.substring(deliveryCost.indexOf(')')).replaceAll("\\D", ""));
        }
        int cf = Integer.parseInt(finalCost.replaceAll("\\D", ""));

        Assert.assertEquals(cf, c1 + c2);
    }

    @Step
    public void goBackToCart(WebDriver driver)
    {
        driver.get("https://beru.ru/my/cart");
    }

    @Step
    public void IncreaseCount(int finalCost, WebDriver driver)
    {
        int cost = 0;

        do
        {
            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.cssSelector("[class='_2ovZ10xVbg'] input")).isDisplayed();
                }
            });

            int currentCount = Integer.parseInt(driver.findElement(By.cssSelector("[class='_2ovZ10xVbg'] input")).getAttribute("value"));

            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.xpath("//*[contains(@class,'_4qhIn2-ESi _2sJs248D-A _18c2gUxCdP _3hWhO4rvmA')]")).isDisplayed();
                }
            });

            WebElement incrementButton = driver.findElement(By.xpath("//*[contains(@class,'_4qhIn2-ESi _2sJs248D-A _18c2gUxCdP _3hWhO4rvmA')]"));
            incrementButton.click();

            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.cssSelector("[class='_2ovZ10xVbg'] input")).isDisplayed();
                }
            });

            int newCount = Integer.parseInt(driver.findElement(By.cssSelector("[class='_2ovZ10xVbg'] input")).getAttribute("value"));
            Assert.assertEquals(currentCount + 1, newCount);

            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.cssSelector("[class='_1pTV0mQZJz _37FeBjfnZk _1JLs4_hnVR']")).isDisplayed();
                }
            });

            cost = Integer.parseInt(driver.findElement(By.cssSelector("[class='_1pTV0mQZJz _37FeBjfnZk _1JLs4_hnVR']")).getText().replaceAll("\\D", ""));
        }
        while (cost <= finalCost);
    }
}
