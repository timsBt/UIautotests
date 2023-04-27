package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomeLogin;
import pages.MainPage;


import static utils.ValueProperties.valueProperties;

public class AuthorizationTest extends BaseTest{

    MainPage mainPage = new MainPage(driver);
    HomeLogin homeLogin = new HomeLogin(driver);

    @Test
    public void validLoginTest() throws Exception {
        mainPage.userNameInput(valueProperties("username"))
                .passwordInput(valueProperties("password"))
                .userName2Input(valueProperties("username2"))
                .loginButtonClick();
        Assert.assertEquals(homeLogin.textCheck(),valueProperties("homeMessage"));
        Assert.assertEquals(homeLogin.textCheck2(),valueProperties("loginInMessage"));
    }

    @Test
    public void inValidLoginTest() throws Exception {
        mainPage.userNameInput(valueProperties("invalidUsername"))
                .passwordInput(valueProperties("invalidPassword"))
                .userName2Input(valueProperties("username2"))
                .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }

   @Test
    public void inValidPasswordTest() throws Exception {
        mainPage.userNameInput(valueProperties("username"))
                .passwordInput(valueProperties("invalidPassword"))
                .userName2Input(valueProperties("username2"))
                .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }

    @Test
    public void inValidUserNameTest() throws Exception {
        mainPage.userNameInput(valueProperties("invalidUsername"))
                .passwordInput(valueProperties("password"))
                .userName2Input(valueProperties("username2"))
                .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }

    @Test
    public void messageUnderUsernameTest() throws Exception {
        mainPage.getUserName().click();
        mainPage.passwordInput(valueProperties("password"))
                .userName2Input(valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderUsername(),valueProperties("messageUnderTheFields"));
    }

    @Test
    public void messageUnderPasswordTest() throws Exception {
        mainPage.userNameInput(valueProperties("username"))
                .getPassword().click();
        mainPage.userName2Input(valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderPassword(),valueProperties("messageUnderTheFields"));
    }

   @Test
    public void oneSimbolUserNameTest() throws Exception {
       mainPage.userNameInput(valueProperties("oneSymbol"))
               .passwordInput(valueProperties("password"))
               .userName2Input(valueProperties("username2"))
               .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckOneSymbolMessageUsername(),valueProperties("oneSymbolMessageUsername"));
    }

    @Test
    public void oneSimbolPasswordTest() throws Exception {
        mainPage.userNameInput(valueProperties("username"))
                .passwordInput(valueProperties("oneSymbol"))
                .userName2Input(valueProperties("username2"))
                .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckOneSymbolMessagePassword(),valueProperties("oneSymbolMessagePassword"));
    }
}
