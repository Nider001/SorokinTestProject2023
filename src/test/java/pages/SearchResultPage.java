package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchResultPage extends BasePage
{
    //@FindBy(css = "[id = 'sa_container'] > [class = 'post'] > a")
    //WebElement firstResultItemLink;

    @FindBy(css = "[id = 'sa_container'] > [class = 'post'] > a > [class='ssize']")
    WebElement firstResultItemName;

    @FindBy(xpath = "//a[*[text()[contains(.,'Трамвайный путь')]]]")
    //@FindBy(css = "a[href = '//artnow.ru/ru/gallery/210/14314/picture/0/870972.html?sen=3c&wid=210']")
    WebElement TramTrackItemLink;

    //@FindBy(css = "button[class = 'control scLarge']")
    //WebElement searchSubmitButton;

    public SearchResultPage(WebDriver driver) { super(driver); }

    @Step("Assert first result name.")
    public SearchResultPage assertFirstResultName(String text)
    {
        waitForElementVisible(firstResultItemName);
        Assert.assertTrue(firstResultItemName.getText().contains(text));
        return this;
    }

    @Step("Go to Tram Track picture details page.")
    public PictureDetailsPage goToTramTrackDetails()
    {
        waitForElementEnable(TramTrackItemLink);
        driver.get("https://artnow.ru/ru/gallery/210/14314/picture/0/870972.html?sen=3c&wid=210");
        return new PictureDetailsPage(driver);
    }

    @Step("Locate Tram Track picture.")
    public SearchResultPage locateTramTrackItem()
    {
        waitForElementVisible(TramTrackItemLink);
        return this;
    }

    @Step("Restore default state.")
    public HomePage doReset()
    {
        simpleReset();
        return new HomePage(driver);
    }
}
