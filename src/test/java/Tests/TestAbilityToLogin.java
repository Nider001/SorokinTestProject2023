package Tests;

import Pages.HomePage;
import Core.ActionHandler;
import org.testng.annotations.Test;

public class TestAbilityToLogin extends ActionHandler {

    @Test
    public void login()
    {
        HomePage checkLogin = new HomePage(webDriver);

        checkLogin.login(webDriver);
    }
}
