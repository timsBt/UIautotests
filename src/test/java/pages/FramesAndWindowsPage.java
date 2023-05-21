package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Класс описания страницы Frames And Windows.
 */
public class FramesAndWindowsPage {

    /**
     * Объявление экземпляра драйвера.
     */
    private final WebDriver driver;

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public FramesAndWindowsPage(final WebDriver webDriver) {
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
     * Поиск и объявление локатора фрейма.
     */
    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame;

    /**
     * Поиск и объявление локатора поля с ссылкой.
     */
    @FindBy(xpath = "//a[@target=\"_blank\"]")
    private WebElement newBrowserTab;

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
     * Метод получения элемента Ссылки и проверка его наличия на странице.
     *
     * @return элемент Ссылки
     */
    public WebElement getNewBrowserTab() {
        openElement(newBrowserTab);
        return newBrowserTab;
    }

    /**
     * Метод нажатия на ссылку.
     *
     * @return Текущая страница
     */
    @Step("Нажимаем на ссылку в первой вкладке")
    public NewBroserTabsPage clickButton() {
        getNewBrowserTab().click();
        return new NewBroserTabsPage(driver);
    }

    /**
     * Метод переключения на фрейм.
     *
     * @return Текущая страница
     */
    @Step("Переключаемся на фрейм")
    public FramesAndWindowsPage switchToFrame() {
        driver.switchTo().frame(getFrame());
        return this;
    }
}
