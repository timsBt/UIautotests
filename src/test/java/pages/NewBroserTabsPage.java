package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Класс описания страницы со ссылкой.
 */
public class NewBroserTabsPage {

    /**
     * Объявление экземпляра драйвера.
     */
    private final WebDriver driver;

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public NewBroserTabsPage(final WebDriver webDriver) {
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
     * Поиск и объявление локатора поля с ссылкой.
     */
    @FindBy(xpath = "//a[@target=\"_blank\"]")
    private WebElement newBrowserTab;

    /**
     * Метод получения элемента поля с ссылкой и проверка его наличия.
     *
     * @return элемент ссылки
     */
    public WebElement getNewBrowserTab() {
        openElement(newBrowserTab);
        return newBrowserTab;
    }

    /**
     * Метод нажатия на ссылку.
     *
     * @return новая страница
     * @see NewBroserTabsPage
     */
    @Step("Нажимаем на ссылку во второй вкладке")
    public NewBroserTabsPage clickButtonNewBrowserPage() {
        getNewBrowserTab().click();
        return new NewBroserTabsPage(driver);
    }
}
