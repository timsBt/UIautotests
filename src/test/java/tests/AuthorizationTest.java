package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomeLogin;
import pages.MainPage;
import utils.ExecutorUtils;

import static utils.ValueProperties.valueProperties;

@Epic(value = "UI Tests")
@Listeners(utils.ListenersUtils.class)
public class AuthorizationTest extends BaseTest {

    private MainPage mainPage;
    private HomeLogin homeLogin;
    private ExecutorUtils executor;
    public static final String MAIN_URL = "https://www.way2automation.com/angularjs-protractor/registeration/#/login";

    @BeforeClass
    public void setPages() {
        mainPage = new MainPage(driver);
        homeLogin = new HomeLogin(driver);
        executor = new ExecutorUtils();
    }

    @BeforeMethod
    public void setgetDriver() {
        driver.get(MAIN_URL);
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

    @Test(description = "TC-2 Авторизация с НЕвалидными данными")
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

    @Test(description = "TC-3 Ввод корректного логина и некорректного пароля")
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

    @Test(description = "TC-4 Ввод некорректного логина и корректного пароля")
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

    @Test(description = "TC-5 Проверка поля Username пустым значением")
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

    @Test(description = "TC-6 Проверка поля Password пустым значением")
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

    @Test(description = "TC-7 Ввод одного символа в поле Username")
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

    @Test(description = "TC-8 Ввод одного символа в поле Password")
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

    @Test(description = "TC-9 Авторизация с валидными и НЕвалидными данными",
            dataProvider = "LoginDataProvider", dataProviderClass = AuthorizationTest.class)
    @Feature(value = "Проверка авторизации")
    @Story(value = "Валидные и НЕвалидные значения")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с валидными и НЕ валидными данными")
    public void validInvalidLoginTest(String userName, String password, String username2, Boolean expectResult) {
        mainPage.userNameInput(userName)
            .passwordInput(password)
            .userName2Input(username2)
            .loginButtonClick();
        if (expectResult) {
            Assert.assertEquals(homeLogin.textCheck(), valueProperties("homeMessage"));
            Assert.assertEquals(homeLogin.textCheck2(), valueProperties("loginInMessage"));
        } else {
            Assert.assertEquals(mainPage.textCheckErrorMessage(), valueProperties("errorMessage"));
        }
    }

    @DataProvider(name = "LoginDataProvider")
    private Object[][] getData() {
        Object[][] data = {{valueProperties("username"), valueProperties("password"), valueProperties("username2"), true},
                {valueProperties("username"), valueProperties("password"), valueProperties("username3"), true},
                {valueProperties("invalidUsername"), valueProperties("invalidPassword"), valueProperties("username2"), false},
                {valueProperties("invalidUsername2"), valueProperties("invalidPassword2"), valueProperties("username3"), false}};
        return data;
    }

    @Test(description = "TC-10 Падающий тест - Проверка создания скриншота")
    @Feature(value = "Падающие тесты")
    @Story(value = "Проверка создания скриншота")
    @Severity(value = SeverityLevel.TRIVIAL)
    @Description("Падающий тест - Проверка создания скриншота")
    public void validLoginErrorTest() {
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(homeLogin.textCheck(), valueProperties("homeMessageError"));
    }

    @Test(description = "TC-11 Падающий тест - Проверка создания скриншота")
    @Feature(value = "Падающие тесты")
    @Story(value = "Проверка создания скриншота")
    @Severity(value = SeverityLevel.TRIVIAL)
    @Description("Падающий тест - Проверка создания скриншота")
    public void inValidLoginErrorTest() {
        mainPage.userNameInput(valueProperties("invalidUsername"))
            .passwordInput(valueProperties("invalidPassword"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(), valueProperties("errorMessageError"));
    }

    @Test(description = "Отвод фокуса с поля ввода")
    @Feature(value = "Проверка работы JavaScriptExecutor")
    @Description("Отвод фокуса с поля ввода Username")
    public void executorFocus() {
        mainPage.getUserName().click();
        executor.shiftFocusFromUsernameField();
        Assert.assertFalse(mainPage.getUserName().equals(driver.switchTo().activeElement()));
    }

    @Test(description = "Проверка на отсутствие скролла на странице")
    @Feature(value = "Проверка работы JavaScriptExecutor")
    @Description("Проверка скролла")
    public void executorScroll() {
        boolean scrollVertical = executor.executeScriptHeight();
        boolean scrollHorizontal = executor.executeScriptWidth();
        boolean result = scrollVertical && scrollHorizontal;
        Assert.assertFalse(result);
    }
}
