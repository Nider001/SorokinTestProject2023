package Tests;

import Pages.HomePage;
import Core.ActionHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAbilityToChangeCity extends ActionHandler {

    @Test(dataProvider = "Authentication")
    public void changeCity(String city)
    {
        HomePage checkChangeCity = new HomePage(webDriver);

        checkChangeCity.changeCity(webDriver, city);
    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][]{
                {"Хвалынск"},
                {"Хабаровск"},
                {"Москва"},
                {"Волгоград"}
        };
    }
}
