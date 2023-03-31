package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
    @FindBy(css = "input[type='text']")
    WebElement searchInput;

    @FindBy(css = "button[class = 'control scLarge']")
    WebElement searchSubmitButton;

    @FindBy(css = "[data-show='gids']")
    WebElement showMoreButton;

    @FindBy(css = "a[href='//artnow.ru/vyshitye-kartiny-kupit.html']")
    WebElement embroideryButton;

    @FindBy(css = "a[href='//artnow.ru/batik-kartiny-kupit.html']")
    WebElement batikButton;

    @FindBy(css = "a[href='//artnow.ru/juvelirnye-izdelija-ruchnoj-raboty.html']")
    WebElement jewelryButton;

    public HomePage(WebDriver driver) { super(driver); }

    @Step("Open home page.")
    public HomePage open()
    {
        driver.get("https://artnow.ru");
        return this;
    }

    @Step("Perform local search with provided text and go to search results.")
    public SearchResultPage searchByText(String text)
    {
        waitForElementEnable(searchInput);
        new Actions(driver).click(searchInput).build().perform();
        searchInput.sendKeys(text);
        new Actions(driver).click(searchSubmitButton).build().perform();
        return new SearchResultPage(driver);
    }

    @Step("Go to Embroidery list.")
    public EmbroideryPage goToEmbroidery()
    {
        waitForElementEnable(showMoreButton);
        new Actions(driver).click(showMoreButton).build().perform();
        waitForElementEnable(embroideryButton);
        new Actions(driver).click(embroideryButton).build().perform();
        return new EmbroideryPage(driver);
    }

    @Step("Go to Batik list.")
    public BatikPage goToBatik()
    {
        waitForElementEnable(showMoreButton);
        new Actions(driver).click(showMoreButton).build().perform();
        waitForElementEnable(batikButton);
        new Actions(driver).click(batikButton).build().perform();
        return new BatikPage(driver);
    }

    @Step("Go to Jewelry list.")
    public JewelryPage goToJewelry()
    {
        waitForElementEnable(showMoreButton);
        new Actions(driver).click(showMoreButton).build().perform();
        waitForElementEnable(jewelryButton);
        new Actions(driver).click(jewelryButton).build().perform();
        return new JewelryPage(driver);
    }

    @Step("Restore default state.")
    public HomePage doReset()
    {
        simpleReset();
        return this;
    }
}
