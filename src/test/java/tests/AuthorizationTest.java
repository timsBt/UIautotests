package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomeLogin;
import pages.MainPage;
import utils.ExecutorUtils;

import static utils.PropertiesUtils.valueProperties;

/**
 * Тесты авторизации на главной странице сайта.
 */
@Epic(value = "UI Tests")
@Listeners(utils.ListenersUtils.class)
public class AuthorizationTest extends BaseTest {

    /**
     * Открытие главной страницы.
     */
    @BeforeMethod
    public void openPage() {
        getDriver().get(valueProperties("mainPageUrl"));
    }

    /**
     * Тест авторизации с корректными данными.
     */
    @Test(description = "TC-1 Авторизация с валидными данными")
    @Feature(value = "Проверка авторизации")
    @Story(value = "Валидные значения")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с валидными данными")
    public void validLoginTest() {
        MainPage mainPage = new MainPage(getDriver());
        HomeLogin homeLogin = new HomeLogin(getDriver());
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(homeLogin.textCheck(),
                valueProperties("homeMessage"));
        Assert.assertEquals(homeLogin.textCheck2(),
                valueProperties("loginInMessage"));
    }

    /**
     * Тест авторизации с некорректными данными.
     */
    @Test(description = "TC-2 Авторизация с НЕвалидными данными")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидными данными")
    public void inValidLoginTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("invalidUsername"))
            .passwordInput(valueProperties("invalidPassword"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),
                valueProperties("errorMessage"));
    }

    /**
     * Тест авторизации с некорректным паролем.
     */
    @Test(description = "TC-3 Ввод корректного логина и некорректного пароля")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидным паролем")
    public void inValidPasswordTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("invalidPassword"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),
                valueProperties("errorMessage"));
    }

    /**
     * Тест авторизации с некорректным логином.
     */
    @Test(description = "TC-4 Ввод некорректного логина и корректного пароля")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидным логином")
    public void inValidUserNameTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("invalidUsername"))
            .passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),
                valueProperties("errorMessage"));
    }

    /**
     * Тест авторизации с пустым полем Username.
     */
    @Test(description = "TC-5 Проверка поля Username пустым значением")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с пустым полем Username")
    public void messageUnderUsernameTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.getUserName().click();
        mainPage.passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderFields(),
                valueProperties("messageUnderTheFields"));
    }

    /**
     * Тест авторизации с пустым полем Password.
     */
    @Test(description = "TC-6 Проверка поля Password пустым значением")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с пустым полем Password")
    public void messageUnderPasswordTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("username"))
            .getPassword().click();
        mainPage.userName2Input(valueProperties("username2"));
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderFields(),
                valueProperties("messageUnderTheFields"));
    }

    /**
     * Тест авторизации с одним символом в поле Username.
     */
    @Test(description = "TC-7 Ввод одного символа в поле Username")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Авторизация с одним символом Username")
    public void oneSimbolUserNameTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("oneSymbol"))
           .passwordInput(valueProperties("password"))
           .userName2Input(valueProperties("username2"))
           .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckOneSymbolMessage(),
                valueProperties("oneSymbolMessageUsername"));
    }

    /**
     * Тест авторизации с одним символом в поле Password.
     */
    @Test(description = "TC-8 Ввод одного символа в поле Password")
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с одним символом Password")
    public void oneSimbolPasswordTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("oneSymbol"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckOneSymbolMessage(),
                valueProperties("oneSymbolMessagePassword"));
    }

    /**
     * Тест авторизации с корректными и некорректными данными.
     *
     * @param userName Имя пользователя
     * @param password Пароль пользователя
     * @param username2 Имя пользователя*
     * @param expectResult Результат данных
     */
    @Test(description = "TC-9 Авторизация с валидными и НЕвалидными данными",
            dataProvider = "LoginDataProvider",
            dataProviderClass = AuthorizationTest.class)
    @Feature(value = "Проверка авторизации")
    @Story(value = "Валидные и НЕвалидные значения")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с валидными и НЕвалидными данными")
    public void validInvalidLoginTest(final String userName,
                                      final String password,
                                      final String username2,
                                      final Boolean expectResult) {
        MainPage mainPage = new MainPage(getDriver());
        HomeLogin homeLogin = new HomeLogin(getDriver());
        mainPage.userNameInput(userName)
            .passwordInput(password)
            .userName2Input(username2)
            .loginButtonClick();
        if (expectResult) {
            Assert.assertEquals(homeLogin.textCheck(),
                    valueProperties("homeMessage"));
            Assert.assertEquals(homeLogin.textCheck2(),
                    valueProperties("loginInMessage"));
        } else {
            Assert.assertEquals(mainPage.textCheckErrorMessage(),
                    valueProperties("errorMessage"));
        }
    }

    /**
     * Метод для передачи в тест множества различных данных.
     *
     * @return набор данных
     */
    @DataProvider(name = "LoginDataProvider")
    private Object[][] getData() {
        return new Object[][]{
                {valueProperties("username"), valueProperties("password"),
                        valueProperties("username2"), true},
                {valueProperties("username"), valueProperties("password"),
                        valueProperties("username3"), true},
                {valueProperties("invalidUsername"),
                        valueProperties("invalidPassword"),
                        valueProperties("username2"), false},
                {valueProperties("invalidUsername2"),
                        valueProperties("invalidPassword2"),
                        valueProperties("username3"), false}};
    }

    /**
     * Тест проверки на создание скриншота в падающем тесте.
     */
    @Test(description = "TC-10 Падающий тест - Проверка создания скриншота")
    @Feature(value = "Падающие тесты")
    @Story(value = "Проверка создания скриншота")
    @Severity(value = SeverityLevel.TRIVIAL)
    @Description("Падающий тест - Проверка создания скриншота")
    public void validLoginErrorTest() {
        MainPage mainPage = new MainPage(getDriver());
        HomeLogin homeLogin = new HomeLogin(getDriver());
        mainPage.userNameInput(valueProperties("username"))
            .passwordInput(valueProperties("password"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(homeLogin.textCheck(),
                valueProperties("homeMessageError"));
    }

    /**
     * Тест проверки на создание скриншота в падающем тесте.
     */
    @Test(description = "TC-11 Падающий тест - Проверка создания скриншота")
    @Feature(value = "Падающие тесты")
    @Story(value = "Проверка создания скриншота")
    @Severity(value = SeverityLevel.TRIVIAL)
    @Description("Падающий тест - Проверка создания скриншота")
    public void inValidLoginErrorTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.userNameInput(valueProperties("invalidUsername"))
            .passwordInput(valueProperties("invalidPassword"))
            .userName2Input(valueProperties("username2"))
            .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),
                valueProperties("errorMessageError"));
    }

    /**
     * Тест на отвод фокуса с поля ввода.
     */
    @Test(description = "Отвод фокуса с поля ввода")
    @Feature(value = "Проверка работы JavaScriptExecutor")
    @Description("Отвод фокуса с поля ввода Username")
    public void executorFocus() {
        MainPage mainPage = new MainPage(getDriver());
        ExecutorUtils executor = new ExecutorUtils();
        mainPage.getUserName().click();
        executor.shiftFocusFromUsernameField();
        Assert.assertNotEquals(getDriver()
            .switchTo().activeElement(), mainPage.getUserName());
    }

    /**
     * Тест проверки скролла на странице.
     */
    @Test(description = "Проверка на отсутствие скролла на странице")
    @Feature(value = "Проверка работы JavaScriptExecutor")
    @Description("Проверка скролла")
    public void executorScroll() {
        ExecutorUtils executor = new ExecutorUtils();
        boolean scrollVertical = executor.executeScriptHeight();
        boolean scrollHorizontal = executor.executeScriptWidth();
        boolean result = scrollVertical && scrollHorizontal;
        Assert.assertFalse(result);
    }
}
