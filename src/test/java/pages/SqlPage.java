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
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name=\"login\"]")
    private WebElement login;

    @FindBy(xpath = "//input[@name=\"psw\"]")
    private WebElement password;

    @FindBy(xpath = "//input[@name=\"subm1\"]")
    private WebElement emptyButton;

    @FindBy(xpath = "//a[@href=\"/personal.php\"]")
    private WebElement name;

    @FindBy(xpath = "//img[@title=\"Выход...\"]")
    private WebElement exit;

    public WebElement getExit() {
        return exit;
    }

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

    @Step("Ввод логина, пароля и нажатие кнопки Вход")
    public SqlPage loginSql(String name, String password) {
        getLogin().sendKeys(name);
        getPassword().sendKeys(password);
        getEmptyButton().click();
        return this;
    }

    @Step("Деавторизация")
    public SqlPage logOut() {
        getExit().click();
        return this;
    }
}
