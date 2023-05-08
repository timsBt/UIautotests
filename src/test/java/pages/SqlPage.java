package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Класс описания страницы Упражнения по SQL.
 */
public class SqlPage {

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public SqlPage(final WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Поиск и объявление локатора поля Логин.
     */
    @FindBy(xpath = "//input[@name=\"login\"]")
    private WebElement login;

    /**
     * Поиск и объявление локатора поля Пароль.
     */
    @FindBy(xpath = "//input[@name=\"psw\"]")
    private WebElement password;

    /**
     * Поиск и объявление локатора кнопки Вход.
     */
    @FindBy(xpath = "//input[@name=\"subm1\"]")
    private WebElement emptyButton;

    /**
     * Поиск и объявление локатора поля Имени пользователя.
     */
    @FindBy(xpath = "//a[@href=\"/personal.php\"]")
    private WebElement name;

    /**
     * Поиск и объявление локатора кнопки Выход.
     */
    @FindBy(xpath = "//a[@href=\"/logout.php\"]")
    private WebElement exit;

    /**
     * Метод получения элемента кнопки Выход и проверка его наличия на странице.
     *
     * @return элемент кнопки Выход
     */
    public WebElement getExit() {
        openElement(name);
        return exit;
    }

    /**
     * Метод получения элемента поля Имени пользователя
     * и проверка его наличия на странице.
     *
     * @return элемент поля Имени пользователя
     */
    public WebElement getName() {
        openElement(name);
        return name;
    }

    /**
     * Метод получения элемента поля Логин и проверка его наличия на странице.
     *
     * @return элемент поля Логин
     */
    public WebElement getLogin() {
        openElement(login);
        return login;
    }

    /**
     * Метод получения элемента поля Пароль и проверка его наличия на странице.
     *
     * @return элемент поля Пароль
     */
    public WebElement getPassword() {
        openElement(password);
        return password;
    }

    /**
     * Метод получения элемента поля Вход и проверка его наличия на странице.
     *
     * @return элемент поля Вход
     */
    public WebElement getEmptyButton() {
        openElement(emptyButton);
        return emptyButton;
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
     * Метод получения Имени пользователя.
     *
     * @return текст Имя пользователя
     */
    @Step("Проверка Имени")
    public String textName() {
        return getName().getText();
    }

    /**
     * Метод авторизации на странице.
     *
     * @param nameUser     Логин
     * @param passwordUser Пароль
     * @return Текущая страница
     */
    @Step("Ввод логина, пароля и нажатие кнопки Вход")
    public SqlPage loginSql(final String nameUser, final String passwordUser) {
        getLogin().sendKeys(nameUser);
        getPassword().sendKeys(passwordUser);
        getEmptyButton().click();
        return this;
    }

    /**
     * Метод Деавторизации на странице.
     *
     * @return Текущая страница
     */
    @Step("Деавторизация")
    public SqlPage logOut() {
        getExit().click();
        return this;
    }
}
