package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage
{
    String productInfo;
    String productCost;

    @FindBy(css = "[class = 'c_ph'] > a > img")
    WebElement cartInfo;

    @FindBy(css = "[class = 'shop'] > [class = 'price']")
    WebElement cartCost;

    @FindBy(css = "[class = 'c_del control']")
    WebElement removeButton;

    public CartPage(WebDriver driver, String info, String cost)
    {
        super(driver);

        productInfo = info;
        productCost = cost;
    }

    @Step("Confirm purchase in the cart.")
    public CartPage confirmPurchase()
    {
        waitForElementVisible(cartInfo);
        Assert.assertEquals(productInfo, cartInfo.getAttribute("src"));

        waitForElementVisible(cartCost);
        Assert.assertEquals(productCost, cartCost.getText());

        return this; //c_del control
    }

    @Step("Remove from cart and restore default state.")
    public HomePage doReset()
    {
        waitForElementEnable(removeButton);
        new Actions(driver).click(removeButton).build().perform();

        simpleReset();
        return new HomePage(driver);
    }
}
