package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Класс описания главной страницы авторизации.
 */
public class MainPage {

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public MainPage(final WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Поиск и объявление локатора поля Username.
     */
    @FindBy(xpath = "//input[@id=\"username\"]")
    private WebElement userName;

    /**
     * Поиск и объявление локатора поля Password.
     */
    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement password;

    /**
     * Поиск и объявление локатора поля Username*.
     */
    @FindBy(xpath = "//input[@ng-model=\"model[options.key]\"]")
    private WebElement userName2;

    /**
     * Поиск и объявление локатора кнопки Login.
     */
    @FindBy(xpath = "//button[@ng-click=\"Auth.login()\"]")
    private WebElement loginButton;

    /**
     * Поиск и объявление локатора текста ошибки.
     */
    @FindBy(xpath = "//div[@ng-if=\"Auth.error\"]")
    private WebElement errorMessage;

    /**
     * Поиск и объявление локатора текста ошибки под полями.
     */
    @FindBy(xpath = "//div[@ng-message=\"required\"]")
    private WebElement errorMessageUnderFields;

    /**
     * Поиск и объявление локатора текста ошибки при вводе одного символа.
     */
    @FindBy(xpath = "//div[@ng-message=\"minlength, maxlength\"]")
    private WebElement oneSymbolMessage;

    /**
     * Метод получения элемента поля Username
     * и проверка его наличия на странице.
     *
     * @return элемент поля Username
     */
    public WebElement getUserName() {
        openElement(userName);
        return userName;
    }

    /**
     * Метод получения элемента поля Password
     * и проверка его наличия на странице.
     *
     * @return элемента поля Password
     */
    public WebElement getPassword() {
        openElement(password);
        return password;
    }

    /**
     * Метод получения элемента поля Username*
     * и проверка его наличия на странице.
     *
     * @return элемент поля Username*
     */
    public WebElement getUserName2() {
        openElement(userName2);
        return userName2;
    }

    /**
     * Метод получения элемента кнопки Login и проверка его наличия на странице.
     *
     * @return элемент кнопки Login
     */
    public WebElement getLoginButton() {
        openElement(loginButton);
        return loginButton;
    }

    /**
     * Метод получения элемента поля с текстом ошибки
     * и проверка его наличия на странице.
     *
     * @return элемент поля с текстом ошибки
     */
    public WebElement getErrorMessage() {
        openElement(errorMessage);
        return errorMessage;
    }

    /**
     * Метод получения элемента поля с текстом ошибки под полями
     * и проверка его наличия на странице.
     *
     * @return элемент поля с текстом ошибки под полями
     */
    public WebElement getErrorMessageUnderFields() {
        openElement(errorMessageUnderFields);
        return errorMessageUnderFields;
    }

    /**
     * Метод получения элемента поля с текстом ошибки ввода одного символа
     * и проверка его наличия на странице.
     *
     * @return элемент поля с текстом ошибки ввода одного символа
     */
    public WebElement getOneSymbolMessage() {
        openElement(oneSymbolMessage);
        return oneSymbolMessage;
    }

    /**
     * Метод проверки на наличия элементов на странице.
     *
     * @param element элемент на странице
     */
    public void openElement(final WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    /**
     * Метод получения сообщения ошибки.
     *
     * @return Сообщение ошибки
     */
    @Step("Проверка сообщения ошибки")
    public String textCheckErrorMessage() {
        return getErrorMessage().getText();
    }

    /**
     * Метод получения сообщения ошибки под полями.
     *
     * @return Сообщение ошибки под полями
     */
    @Step("Проверка сообщения ошибки под полями")
    public String textCheckErrorMessageUnderFields() {
        return getErrorMessageUnderFields().getText();
    }

    /**
     * Метод получения сообщения ошибки ввода одного символа.
     *
     * @return Сообщение ошибки ввода одного символа
     */
    @Step("Проверка сообщения ошибки ввода Одного символа под полями")
    public String textCheckOneSymbolMessage() {
        return getOneSymbolMessage().getText();
    }

    /**
     * Метод ввода данных в поле Username.
     *
     * @param username Имя пользователя
     * @return Текущая страница
     */
    @Step("Ввод значения в поле Username")
    public MainPage userNameInput(final String username) {
        getUserName().sendKeys(username);
        return this;
    }

    /**
     * Метод ввода данных в поле Password.
     *
     * @param passwordUser Пароль пользователя
     * @return Текущая страница
     */
    @Step("Ввод значения в поле Password")
    public MainPage passwordInput(final String passwordUser) {
        getPassword().sendKeys(passwordUser);
        return this;
    }

    /**
     * Метод ввода данных в поле Username*.
     *
     * @param username2 Имя пользователя*
     * @return Текущая страница
     */
    @Step("Ввод значения в поле Username*")
    public MainPage userName2Input(final String username2) {
        getUserName2().sendKeys(username2);
        return this;
    }

    /**
     * Метод клика по кнопке Login.
     *
     * @return Текущая страница
     */
    @Step("Клик по кнопке Login")
    public MainPage loginButtonClick() {
        getLoginButton().click();
        return this;
    }
}
