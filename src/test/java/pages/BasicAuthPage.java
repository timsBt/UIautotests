package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/**
 * Класс описания страницы Basic Authentication.
 */
public class BasicAuthPage {

    /**
     * Объявление экземпляра драйвера.
     */
    private final WebDriver driver;

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public BasicAuthPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
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
     * Поиск и объявление локатора кнопки Display Image.
     */
    @FindBy(xpath = "//*[@id=\"displayImage\"]")
    private WebElement displayImage;

    /**
     * Поиск и объявление локатора кнопки Download Image.
     */
    @FindBy(xpath = "//*[@id=\"downloadImg\"]")
    private WebElement downloadImage;

    /**
     * Метод получения элемента кнопки Display Image
     * и проверка его наличия на странице.
     *
     * @return элемент кнопки Display Image
     */
    public WebElement getDisplayImage() {
        openElement(displayImage);
        return displayImage;
    }

    /**
     * Метод нажатия кнопки Display Image.
     *
     * @return Текущая страница
     */
    @Step("Нажатие кнопки Display Image")
    public BasicAuthPage clickDisplayImage() {
        getDisplayImage().click();
        return this;
    }

    /**
     * Метод получения логического значения на присутсвие элемента изображения.
     *
     * @return true если элемент изображения присутсвует, иначе false
     */
    @Step("Проверка на присутствие изображения аутентификации")
    public boolean authenticationImage() {
        final int seconds = 10;
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(downloadImage));
        return downloadImage.isDisplayed();
    }
}
