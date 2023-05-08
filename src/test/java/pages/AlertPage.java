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

import java.time.Duration;

/**
 * Класс описания страницы Alert.
 */
public class AlertPage {

    /**
     * Объявление экземпляра драйвера.
     */
    private final WebDriver driver;

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public AlertPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Поиск и объявление локатора кнопки Input Alert.
     */
    @FindBy(xpath = "//a[@href=\"#example-1-tab-2\"]")
    private WebElement inputAlert;

    /**
     * Поиск и объявление локатора кнопки
     * Click the button to display an alert box.
     */
    @FindBy(xpath = "//button[@onclick=\"myFunction()\"]")
    private WebElement buttonOnclick;

    /**
     * Поиск и объявление локатора фрейма.
     */
    @FindBy(xpath = "//*[@id=\"example-1-tab-2\"]/div/iframe")
    private WebElement frame;

    /**
     * Поиск и объявление локатора текста.
     */
    @FindBy(xpath = "//*[@id=\"demo\"]")
    private WebElement textAfter;

    /**
     * Метод получения элемента поля сообщения
     * и проверка его наличия на странице.
     *
     * @return элемент поля сообщения
     */
    public WebElement getTextAfter() {
        openElement(textAfter);
        return textAfter;
    }

    /**
     * Метод получения элемента Фрейма и проверка его наличия на странице.
     *
     * @return элемент Фрейма
     */
    public WebElement getFrame() {
        openElement(frame);
        return frame;
    }

    /**
     * Метод получения элемента кнопки Input Alert
     * и проверка его наличия на странице.
     *
     * @return элемент кнопки Input Alert
     */
    public WebElement getInputAlert() {
        openElement(inputAlert);
        return inputAlert;
    }

    /**
     * Метод получения элемента кнопки
     * Click the button to display an alert box
     * и проверка его наличия на странице.
     *
     * @return элемент кнопки Click the button to display an alert box
     */
    public WebElement getButtonOnclick() {
        openElement(buttonOnclick);
        return buttonOnclick;
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
     * Метод переключения на фрейм.
     *
     * @return Текущая страница
     */
    public AlertPage switchToFrame() {
        driver.switchTo().frame(getFrame());
        return this;
    }

    /**
     * Метод ввода текста во всплывающее оповещение.
     *
     * @param text Текст (Имя)
     * @return Текущая страница
     */
    @Step("Ввод текста во всплывающее оповещение, закрытие окна")
    public AlertPage getAlert(final String text) {
        final int seconds = 10;
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(seconds));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        return this;
    }

    /**
     * Метод получения текста.
     *
     * @return Сообщение текста после ввода во всплывающее оповещение
     */
    @Step("Получение текста")
    public String getTextAfterAlert() {
        return getTextAfter().getText();
    }

    /**
     * Метод нажатия на кнопку Input Alert.
     *
     * @return Текущая страница
     */
    @Step("Нажатие на кнопку Input Alert")
    public AlertPage clickInputAlert() {
        getInputAlert().click();
        return this;
    }

    /**
     * Метод нажатия на кнопку Click the button...
     *
     * @return Текущая страница
     */
    @Step("Нажатие на кнопку Click the button to demonstrate the Input box.")
    public AlertPage clickButtonDemonstrate() {
        getButtonOnclick().click();
        return this;
    }
}
