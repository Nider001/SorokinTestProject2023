package Tests;

import Pages.CartPage;
import Pages.CatalogPage;
import Pages.HomePage;
import Core.ActionHandler;
import org.testng.annotations.Test;

public class TestAbilityToBuyABrush extends ActionHandler {

    @Test
    public void buyABrush()
    {
        HomePage homePage = new HomePage(webDriver);

        homePage.goToBrushes(webDriver);
        CatalogPage catalogPage = new CatalogPage(webDriver);
        catalogPage.setCostRange(999, 1999);
        catalogPage.scrollAndClick(webDriver);
        catalogPage.goToCart(webDriver);

        CartPage cartPage = new CartPage();
        cartPage.CheckDeliveryNotFree(webDriver);
        cartPage.CheckFinalCost(webDriver);
        cartPage.goBackToCart(webDriver);
        cartPage.IncreaseCount(2999, webDriver);
        cartPage.CheckFinalCost(webDriver);
    }
}
