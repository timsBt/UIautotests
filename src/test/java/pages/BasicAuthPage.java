package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasicAuthPage {

    private WebDriver driver;

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @FindBy(xpath = "//*[@id=\"displayImage\"]")
    private WebElement displayImage;

    @FindBy(xpath = "//*[@id=\"downloadImg\"]")
    private WebElement downloadImage;

    public WebElement getDisplayImage() {
        openElement(displayImage);
        return displayImage;
    }

    @Step("Нажатие кнопки Display Image")
    public BasicAuthPage clickDisplayImage() {
        getDisplayImage().click();
        return this;
    }

    @Step("Аутентификация")
    public BasicAuthPage authentication(String name, String password) {
        HasAuthentication authentication = (HasAuthentication) driver;
        authentication.register(() -> new UsernameAndPassword(name, password));
        return this;
    }

    @Step("Проверка на присутствие изображения аутентификации")
    public boolean authenticationImage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(downloadImage));
        return downloadImage.isDisplayed();
    }
}
