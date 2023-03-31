package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class JewelryPage extends BasePage
{
    WebElement nthEntry;
    String info = "";
    String cost = "";

    @FindBy(css = "[id='main_container'] > :last-child")
    WebElement anyEntry;

    @FindBy(css = "button[onclick='sendCartForm();']")
    WebElement cartGoToButton;

    public JewelryPage(WebDriver driver) { super(driver); }

    @Step("Find the needed product.")
    public JewelryPage getNthElement(int n)
    {
        waitForElementVisible(anyEntry);

        List<WebElement> products = driver.findElements(By.className("post"));
        nthEntry = products.get(n);

        info = nthEntry.findElement(By.className("bubu")).getAttribute("src");
        cost = nthEntry.findElement(By.className("price")).getText();

        return this;
    }

    @Step("Add product to cart and go there.")
    public CartPage addProductAndGoToCart()
    {
        waitForElementEnable(nthEntry.findElement(By.className("oclick")));
        new Actions(driver).click(nthEntry.findElement(By.className("oclick"))).build().perform();

        waitForElementEnable(cartGoToButton);
        new Actions(driver).click(cartGoToButton).build().perform();

        CartPage res = new CartPage(driver, info, cost);
        return res;
    }
}
