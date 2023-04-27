package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id=\"username\"]")
    private WebElement userName;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement password;

    @FindBy(xpath = "//input[@ng-model=\"model[options.key]\"]")
    private WebElement userName2;

    @FindBy(xpath = "//button[@ng-click=\"Auth.login()\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@ng-if=\"Auth.error\"]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@ng-message=\"required\"]")
    private WebElement errorMessageUnderFields;

    @FindBy(xpath = "//div[@ng-message=\"minlength, maxlength\"]")
    private WebElement oneSymbolMessage;

    public WebElement getUserName() {
        openElement(userName);
        return userName;
    }

    public WebElement getPassword() {
        openElement(password);
        return password;
    }

    public WebElement getUserName2() {
        openElement(userName2);
        return userName2;
    }

    public WebElement getLoginButton() {
        openElement(loginButton);
        return loginButton;
    }

    public WebElement getErrorMessage() {
        openElement(errorMessage);
        return errorMessage;
    }

    public WebElement getErrorMessageUnderFields() {
        openElement(errorMessageUnderFields);
        return errorMessageUnderFields;
    }

    public WebElement getOneSymbolMessage() {
        openElement(oneSymbolMessage);
        return oneSymbolMessage;
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @Step("Проверка сообщения ошибки")
    public String textCheckErrorMessage() {
        return getErrorMessage().getText();
    }

    @Step("Проверка сообщения ошибки под полями")
    public String textCheckErrorMessageUnderFields() {
        return getErrorMessageUnderFields().getText();
    }

    @Step("Проверка сообщения ошибки ввода Одного символа под полями")
    public String textCheckOneSymbolMessage() {
        return getOneSymbolMessage().getText();
    }

    @Step("Ввод значения в поле Username")
    public MainPage userNameInput(String username) {
        getUserName().sendKeys(username);
        return this;
    }

    @Step("Ввод значения в поле Password")
    public MainPage passwordInput(String password) {
        getPassword().sendKeys(password);
        return this;
    }

    @Step("Ввод значения в поле Username*")
    public MainPage userName2Input(String username2) {
        getUserName2().sendKeys(username2);
        return this;
    }

    @Step("Клик по кнопке Login")
    public MainPage loginButtonClick() {
        getLoginButton().click();
        return this;
    }
}

