package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomeLogin;
import pages.MainPage;

import static utils.ValueProperties.valueProperties;

public class AuthorizationTest extends BaseTest{

    private MainPage mainPage;
    private HomeLogin homeLogin;

    @BeforeClass
    public void setPages() {
        mainPage = new MainPage(driver);
        homeLogin = new HomeLogin(driver);
    }

    @Test(description = "TC-1 Авторизация с валидными данными")
    @Feature(value = "Проверка авторизации")
    @Story(value = "Валидные значения")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с валидными данными")
    public void validLoginTest() {
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(homeLogin.textCheck(), valueProperties("homeMessage"));
        Assert.assertEquals(homeLogin.textCheck2(), valueProperties("loginInMessage"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидными данными")
    public void inValidLoginTest() {
        mainPage.userNameInput(valueProperties("invalidUsername"))
            .passwordInput(valueProperties("invalidPassword"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(), valueProperties("errorMessage"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидным паролем")
    public void inValidPasswordTest() {
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("invalidPassword"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(), valueProperties("errorMessage"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидным логином")
    public void inValidUserNameTest() {
        mainPage.userNameInput(valueProperties("invalidUsername"))
            .passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(), valueProperties("errorMessage"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с пустым полем Username")
    public void messageUnderUsernameTest() {
        mainPage.getUserName().click();
        mainPage.passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderFields(), valueProperties("messageUnderTheFields"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с пустым полем Password")
    public void messageUnderPasswordTest() {
        mainPage.userNameInput(valueProperties("username"))
            .getPassword().click();
        mainPage.userName2Input(valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderFields(), valueProperties("messageUnderTheFields"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Авторизация с одним символом Username")
    public void oneSimbolUserNameTest() {
       mainPage.userNameInput(valueProperties("oneSymbol"))
           .passwordInput(valueProperties("password"))
           .userName2Input(valueProperties("username2"))
           .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckOneSymbolMessage(), valueProperties("oneSymbolMessageUsername"));
    }

    @Test
    @Epic(value = "Негативные тесты")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с одним символом Password")
    public void oneSimbolPasswordTest() {
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("oneSymbol"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckOneSymbolMessage(), valueProperties("oneSymbolMessagePassword"));
    }
}

