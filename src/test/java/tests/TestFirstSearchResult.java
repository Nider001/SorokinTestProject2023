package tests;

import pages.HomePage;
import core.ActionHandler;
import org.testng.annotations.Test;

public class TestFirstSearchResult extends ActionHandler
{
    @Test(description = "Check if the first search result contains the word that was used for the search.")
    public void search()
    {
        new HomePage(driver).open()
                .searchByText("Жираф")
                .assertFirstResultName("Жираф")
                .doReset();
    }
}
