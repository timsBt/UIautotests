package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomeLogin;
import pages.MainPage;

import java.time.Duration;

import static utils.ValueProperties.valueProperties;

public class TestCases {

    WebDriver driver = utils.WebDriver.getChromeDriver();
    MainPage mainPage = new MainPage(driver);
    HomeLogin homeLogin = new HomeLogin(driver);

    @BeforeTest
    public void SetUp() {
        driver.get(mainPage.pageUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

               /**Тест кейс 1*/
    @Test
    public void validLoginTest() throws Exception {
        mainPage.login(valueProperties("username"),
                valueProperties("password"),
                valueProperties("username2"));
        Assert.assertEquals(homeLogin.textCheck(),valueProperties("homeMessage"));
        Assert.assertEquals(homeLogin.textCheck2(),valueProperties("loginInMessage"));
    }

               /**Тест кейс 2*/
    @Test
    public void inValidLoginTest() throws Exception {
        mainPage.login(valueProperties("invalidUsername"),
                valueProperties("invalidPassword"),
                valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }

                /**Тест кейс 3*/
    @Test
    public void inValidPasswordTest() throws Exception {
        mainPage.login(valueProperties("username"),
                valueProperties("invalidPassword"),
                valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }

                /**Тест кейс 4*/
    @Test
    public void inValidUserNameTest() throws Exception {
        mainPage.login(valueProperties("invalidUsername"),
                valueProperties("password"),
                valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }

                /**Тест кейс 5*/
    @Test
    public void messageUnderUsernameTest() throws Exception {
        mainPage.usernameErrorMessage(valueProperties("password"),valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderUsername(),valueProperties("messageUnderTheFields"));
    }

                /**Тест кейс 6*/
    @Test
    public void messageUnderPasswordTest() throws Exception {
        mainPage.passwordErrorMessage(valueProperties("username"),valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderPassword(),valueProperties("messageUnderTheFields"));
    }

                /**Тест кейс 7*/
    @Test
    public void oneSimbolUserNameTest() throws Exception {
        mainPage.login(valueProperties("oneSymbol"),
                valueProperties("password"),
                valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckOneSymbolMessageUsername(),valueProperties("oneSymbolMessageUsername"));
    }

                /**Тест кейс 8*/
    @Test
    public void oneSimbolPasswordTest() throws Exception {
        mainPage.login(valueProperties("username"),
                valueProperties("oneSymbol"),
                valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckOneSymbolMessagePassword(),valueProperties("oneSymbolMessagePassword"));
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
