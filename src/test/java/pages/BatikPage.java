package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class BatikPage extends BasePage
{
    @FindBy(css = "[class='heart']")
    WebElement firstHeart;

    @FindBy(css = "[id = 'sa_container'] > [class = 'post'] > a > img")
    WebElement firstResultItemImage;

    @FindBy(css = "img[src='//artnow.ru/ico/fvt.png']")
    WebElement favouritesButton;

    public BatikPage(WebDriver driver) { super(driver); }

    @Step("Add picture to favourites.")
    public BatikPage addToFavourites()
    {
        waitForElementEnable(firstHeart);
        new Actions(driver).click(firstHeart).build().perform();
        waitForElementVisible(firstResultItemImage);
        return this;
    }

    @Step("Go to favourites.")
    public FavouritesPage goToFavourites()
    {
        new Actions(driver).click(favouritesButton).build().perform();
        return new FavouritesPage(driver);
    }

    /*@Step("Restore default state.")
    public HomePage doReset()
    {
        simpleReset();
        return new HomePage(driver);
    }*/
}
