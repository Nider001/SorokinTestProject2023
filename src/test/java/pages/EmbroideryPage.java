package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Sleeper;

public class EmbroideryPage extends BasePage
{
    @FindBy(css = "input[id='genre257']")
    WebElement localCityScapeInput;

    @FindBy(css = "span[class='form'] > div > div > button[type='submit']")
    WebElement localSearchSubmitButton;

    public EmbroideryPage(WebDriver driver) { super(driver); }

    @Step("Filter by city scapes and go to search results.")
    public SearchResultPage localSearchCityScapes()
    {
        waitForElementEnable(localCityScapeInput);
        new Actions(driver).click(localCityScapeInput).build().perform();
        timeSleep();
        waitForElementEnable(localSearchSubmitButton);
        new Actions(driver).click(localSearchSubmitButton).build().perform();
        return new SearchResultPage(driver);
    }

    @Step("Restore default state.")
    public HomePage doReset()
    {
        simpleReset();
        return new HomePage(driver);
    }
}
