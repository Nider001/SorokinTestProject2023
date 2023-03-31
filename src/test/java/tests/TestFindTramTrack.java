package tests;

import core.ActionHandler;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestFindTramTrack extends ActionHandler
{
    @Test(description = "Go to embroidery catalog and confirm that Tram Track picture can be found.")
    public void search()
    {
        new HomePage(driver).open()
                .goToEmbroidery()
                .localSearchCityScapes()
                .locateTramTrackItem()
                .doReset();
    }
}
