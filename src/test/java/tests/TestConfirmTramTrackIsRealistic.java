package tests;

import core.ActionHandler;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestConfirmTramTrackIsRealistic extends ActionHandler
{
    @Test(description = "Go to embroidery catalog, find Tram Track picture and confirm that it belongs to Realism.")
    public void search()
    {
        new HomePage(driver).open()
                .goToEmbroidery()
                .localSearchCityScapes()
                .locateTramTrackItem()
                .goToTramTrackDetails()
                .locateRealismLabel()
                .doReset();
    }
}
