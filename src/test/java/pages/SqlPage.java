package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SqlPage {

    private WebDriver driver;

    public SqlPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@name=\"login\"]")
    private WebElement login;

    @FindBy(xpath = "//input[@name=\"psw\"]")
    private WebElement password;

    @FindBy(xpath = "//input[@name=\"subm1\"]")
    private WebElement emptyButton;

    @FindBy(xpath = "//a[@href=\"/personal.php\"]")
    private WebElement name;

    public WebElement getName() {
        openElement(name);
        return name;
    }

    public WebElement getLogin() {
        openElement(login);
        return login;
    }

    public WebElement getPassword() {
        openElement(password);
        return password;
    }

    public WebElement getEmptyButton() {
        openElement(emptyButton);
        return emptyButton;
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @Step("Проверка Имени")
    public String textName() {
        return getName().getText();
    }

    @Step("Ввод значения в поле Логин")
    public SqlPage loginInput(String username) {
        getLogin().sendKeys(username);
        return this;
    }

    @Step("Ввод значения в поле Пароль")
    public SqlPage passwordInput(String password) {
        getPassword().sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке Вход")
    public SqlPage emptyButtonClick() {
        getEmptyButton().click();
        return this;
    }
}
