package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;

import java.time.Duration;

public class AlertPage {

    private WebDriver driver;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href=\"#example-1-tab-2\"]")
    private WebElement inputAlert;

    @FindBy(xpath = "//button[@onclick=\"myFunction()\"]")
    private WebElement buttonOnclick;

    @FindBy(xpath = "//*[@id=\"example-1-tab-2\"]/div/iframe")
    private WebElement frame;

    @FindBy(xpath = "//*[@id=\"demo\"]")
    private WebElement textAfter;

    public WebElement getTextAfter() {
        openElement(textAfter);
        return textAfter;
    }

    public WebElement getFrame() {
        openElement(frame);
        return frame;
    }

    public WebElement getInputAlert() {
        openElement(inputAlert);
        return inputAlert;
    }

    public WebElement getButtonOnclick() {
        openElement(buttonOnclick);
        return buttonOnclick;
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    public AlertPage switchToFrame() {
        driver.switchTo().frame(getFrame());
        return this;
    }

    @Step("Ввод текста во всплывающее оповещение, закрытие окна")
    public AlertPage getAlert(String text) {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver.get(), Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        return this;
    }

    @Step("Получение текста")
    public String getTextAfterAlert() {
        return getTextAfter().getText();
    }

    @Step("Нажатие на кнопку Input Alert")
    public AlertPage clickInputAlert() {
        getInputAlert().click();
        return this;
    }

    @Step("Нажатие на кнопку Click the button to demonstrate the Input box.")
    public AlertPage clickButtonDemonstrate() {
        getButtonOnclick().click();
        return this;
    }
}
