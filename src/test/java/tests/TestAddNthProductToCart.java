package tests;

import core.ActionHandler;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestAddNthProductToCart extends ActionHandler
{
    @Test(description = "Add a product to cart and confirm the info.")
    @Parameters({"n"})
    public void search(int n)
    {
        new HomePage(driver).open()
                .goToJewelry()
                .getNthElement(n)
                .addProductAndGoToCart()
                .confirmPurchase()
                .doReset();
    }
}
