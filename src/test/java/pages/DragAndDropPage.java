package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Класс описания страницы Droppable.
 */
public class DragAndDropPage {

    /**
     * Объявление экземпляра драйвера.
     */
    private final WebDriver driver;

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public DragAndDropPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Поиск и объявление локатора элемента Drag me to my target.
     */
    @FindBy(xpath = "//div[@id=\"draggable\"]")
    private WebElement drag;

    /**
     * Поиск и объявление локатора элемента Drop here.
     */
    @FindBy(xpath = "//div[@id=\"droppable\"]")
    private WebElement drop;

    /**
     * Поиск и объявление локатора фрейма.
     */
    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame;

    /**
     * Метод получения элемента Drag me to my target
     * и проверка его наличия на странице.
     *
     * @return элемент Drag me to my target
     */
    public WebElement getDrag() {
        openElement(drag);
        return drag;
    }

    /**
     * Метод получения элемента Drop here и проверка его наличия на странице.
     *
     * @return элемент Drop here
     */
    public WebElement getDrop() {
        openElement(drop);
        return drop;
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
     * Метод переноса элемента.
     *
     * @return Текущая страница
     */
    @Step("Переносим элемент")
    public DragAndDropPage dragNDrop() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getDrag(), getDrop()).build().perform();
        return this;
    }

    /**
     * Метод переключения на фрейм.
     *
     * @return Текущая страница
     */
    @Step("Переключаемся на фрейм")
    public DragAndDropPage switchToFrame() {
        driver.switchTo().frame(getFrame());
        return this;
    }

    /**
     * Метод получения текста.
     *
     * @return Сообщение текста успешного перетаскивания элемента
     */
    @Step("Получаем текст")
    public String getTextDropp() {
        return getDrop().getText();
    }

    /**
     * Метод проверки на наличия элементов на странице.
     *
     * @param element элемент на странице
     */
    public void openElement(final WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }
}
