package cucumber;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.testng.Assert;
import pages.HomeLogin;
import pages.MainPage;

import static tests.RunnerTest.getDriver;
import static utils.PropertiesUtils.valueProperties;

public class AuthorizationDefs {

    /**
     * Объявление экземпляра класса.
     */
    private MainPage mainPage;

    /**
     * Объявление  экземпляра класса.
     */
    private HomeLogin homeLogin;

    /**
     * Открытие главной страницы.
     */
    @Дано("пользователь открывает страницу авторизации")
    public void openPage() {
        mainPage = new MainPage(getDriver());
        homeLogin = new HomeLogin(getDriver());
        getDriver().get(valueProperties("mainPageUrl"));
    }

    /**
     * Шаг авторизации с корректными данными.
     *
     * @param username Имя пользователя
     * @param password Пароль пользователя
     * @param username2 Имя пользователя*
     */
    @Если("Пользователь ввел данные Username {string},"
            + " Password {string}, Username2 {string}")
    public void validAuthorization(
            final String username, final String password,
            final String username2) {
        mainPage.userNameInput(username)
            .passwordInput(password)
            .userName2Input(username2);
    }

    /**
     * Шаг нажатия кнопки Login.
     */
    @И("нажал кнопку Login")
    public void clickButtonLogin() {
        mainPage.loginButtonClick();
    }

    /**
     * Проверка корректной авторизации.
     */
    @Тогда("Авторизация пользователя прошла успешно")
    public void authorizationPassed() {
        Assert.assertEquals(homeLogin.textCheck2(),
                valueProperties("loginInMessage"));
    }

    /**
     * Шаг авторизации с некорректными данными.
     *
     * @param username Имя пользователя
     * @param password Пароль пользователя
     * @param username2 Имя пользователя*
     */
    @Если("Пользователь ввел некорректные данные {string}, {string}, {string}")
    public void invalidAuthorization(
            final String username, final String password,
            final String username2) {
        mainPage.userNameInput(username)
            .passwordInput(password)
            .userName2Input(username2);
    }

    /**
     * Проверка сообщения ошибки авторизации.
     *
     * @param errorMessage Сообщение об ошибке
     */
    @Тогда("Появилось сообщение об ошибке {string}")
    public void errorMessageOnInvalidAuthorization(final String errorMessage) {
        Assert.assertEquals(mainPage.textCheckErrorMessage(), errorMessage);
    }

    /**
     * Шаг авторизации с пустым полем Username.
     *
     * @param password Пароль пользователя
     * @param username2 Имя пользователя*
     */
    @Если("Пользователь ввел данные Password {string}, Username2 {string}")
    public void userInputPasswordUsername2(
            final String password, final String username2) {
        mainPage.getUserName().click();
        mainPage.passwordInput(password)
            .userName2Input(username2);
    }

    /**
     * Проверка сообщения ошибки ввода в поле Username.
     *
     * @param errorMessage Сообщение об ошибке
     */
    @Тогда("Появилось сообщение об ошибке в поле Username {string}")
    public void errorMessageOnInvalidUsername(final String errorMessage) {
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderFields(),
                errorMessage);
    }

    /**
     * Шаг авторизации с пустым полем Username.
     *
     * @param username Имя пользователя
     * @param username2 Имя пользователя*
     */
    @Если("Пользователь ввел данные Username {string}, Username2 {string}")
    public void userInputUsernameUsername2(
            final String username, final String username2) {
        mainPage.userNameInput(username)
            .getPassword().click();
        mainPage.userName2Input(username2);
    }

    /**
     * Проверка сообщения ошибки ввода в поле Password.
     *
     * @param errorMessage Сообщение об ошибке
     */
    @Тогда("Появилось сообщение об ошибке в поле Password {string}")
    public void errorMessageOnInvalidPassword(final String errorMessage) {
        Assert.assertEquals(mainPage.textCheckErrorMessageUnderFields(),
                errorMessage);
    }

    /**
     * Проверка сообщения ошибки под полем Username.
     *
     * @param errorMessage Сообщение об ошибке
     */
    @Тогда("Появилось сообщение об ошибке под полем Username {string}")
    public void errorMessageUnderFieldUsername(final String errorMessage) {
        Assert.assertEquals(mainPage.textCheckOneSymbolMessage(), errorMessage);
    }

    /**
     * Проверка сообщения ошибки под полем Password.
     *
     * @param errorMessage Сообщение об ошибке
     */
    @Тогда("Появилось сообщение об ошибке под полем Password {string}")
    public void errorMessageUnderFieldPassword(final String errorMessage) {
        Assert.assertEquals(mainPage.textCheckOneSymbolMessage(), errorMessage);
    }
}




