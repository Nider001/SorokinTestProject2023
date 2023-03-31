package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FavouritesPage extends BasePage
{
    @FindBy(css = "[id = 'sa_container'] > [class = 'post'] > a > img")
    WebElement firstImage;

    @FindBy(css = "[class='heart']")
    WebElement firstHeart;

    public FavouritesPage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Confirm addition to favourites.")
    public FavouritesPage confirmAddition()
    {
        waitForElementVisible(firstImage);
        return this;
    }


    @Step("Clear the new entry and restore default state.")
    public HomePage doReset()
    {
        waitForElementEnable(firstHeart);
        new Actions(driver).click(firstHeart).build().perform();

        simpleReset();
        return new HomePage(driver);
    }
}
