package pages;

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

    @FindBy(xpath = "//input[@id=\"formly_1_input_username_0\"]")
    private WebElement userName2;

    @FindBy(xpath = "//button[@ng-click=\"Auth.login()\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@ng-if=\"Auth.error\"]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@ng-message=\"required\"]")
    private WebElement errorMessageUnderUsername;

    @FindBy(xpath = "//div[@ng-message=\"required\"]")
    private WebElement errorMessageUnderPassword;

    @FindBy(xpath = "//div[@ng-message=\"minlength, maxlength\"]")
    private WebElement oneSymbolMessageUsername;

    @FindBy(xpath = "//div[@ng-message=\"minlength, maxlength\"]")
    private WebElement oneSymbolMessagePassword;


    public WebElement getUserName() {
        checkOpen(userName);
        return userName;
    }

    public WebElement getPassword() {
        checkOpen(password);
        return password;
    }

    public WebElement getUserName2() {
        checkOpen(userName2);
        return userName2;
    }

    public WebElement getLoginButton() {
        checkOpen(loginButton);
        return loginButton;
    }

    public WebElement getErrorMessage() {
        checkOpen(errorMessage);
        return errorMessage;
    }

    public WebElement getErrorMessageUnderUsername() {
        checkOpen(errorMessageUnderUsername);
        return errorMessageUnderUsername;
    }

    public WebElement getErrorMessageUnderPassword() {
        checkOpen(errorMessageUnderPassword);
        return errorMessageUnderPassword;
    }

    public WebElement getOneSymbolMessageUsername() {
        checkOpen(oneSymbolMessageUsername);
        return oneSymbolMessageUsername;
    }

    public WebElement getOneSymbolMessagePassword() {
        checkOpen(oneSymbolMessagePassword);
        return oneSymbolMessagePassword;
    }

    public void checkOpen(WebElement element){
        Assert.assertTrue(element.isDisplayed());
    }

    public String textCheckErrorMessage (){
        return getErrorMessage().getText();
    }
    public String textCheckErrorMessageUnderUsername (){
        return getErrorMessageUnderUsername().getText();
    }
    public String textCheckErrorMessageUnderPassword (){
        return getErrorMessageUnderPassword().getText();
    }
    public String textCheckOneSymbolMessageUsername (){
        return getOneSymbolMessageUsername().getText();
    }
    public String textCheckOneSymbolMessagePassword (){
        return getOneSymbolMessagePassword().getText();
    }

    public MainPage userNameInput (String username){
        getUserName().sendKeys(username);
        return this;
    }

    public MainPage passwordInput (String password){
        getPassword().sendKeys(password);
        return this;
    }

    public MainPage userName2Input (String username2){
        getUserName2().sendKeys(username2);
        return this;
    }

    public MainPage loginButtonClick (){
        getLoginButton().click();
        return this;
    }
}