package tests;

import core.ActionHandler;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestAddToFavourites extends ActionHandler
{
    @Test(description = "Go to Batik catalog, add the first picture to Favourites and confirm the addition.")
    public void search()
    {
        new HomePage(driver).open()
                .goToBatik()
                .addToFavourites()
                .goToFavourites()
                .confirmAddition()
                .doReset();
    }
}
