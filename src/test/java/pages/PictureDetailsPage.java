package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PictureDetailsPage extends BasePage
{
    @FindBy(css = "div[class='txtline lft'] > a[href='//artnow.ru/kartiny-realism.html']")
    WebElement realismLabel;

    public PictureDetailsPage(WebDriver driver) { super(driver); }

    @Step("Locate 'Realism' label.")
    public PictureDetailsPage locateRealismLabel()
    {
        waitForElementVisible(realismLabel);
        return this;
    }

    @Step("Restore default state.")
    public HomePage doReset()
    {
        simpleReset();
        return new HomePage(driver);
    }
}
