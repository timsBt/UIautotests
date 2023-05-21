package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Класс описания успешной авторизации.
 */
public class HomeLogin {

    /**
     * Конструктор класса для инициализации вэб элементов.
     *
     * @param webDriver драйвер для управления браузером
     */
    public HomeLogin(final WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Поиск и объявление локатора сообщения "Home".
     */
    @FindBy(xpath = "//h1[@class =  'ng-scope']")
    private WebElement homeMessage;

    /**
     * Поиск и объявление локатора сообщения "You're logged in!!".
     */
    @FindBy(xpath = "//p[@class =  'ng-scope']")
    private WebElement loginInMessage;

    /**
     * Метод получения элемента поля сообщения "Home"
     * и проверка его наличия на странице.
     *
     * @return элемент поля "Home"
     */
    public WebElement getHomeMessage() {
        openElement(homeMessage);
        return homeMessage;
    }

    /**
     * Метод получения элемента поля сообщения "You're logged in!!"
     * и проверка его наличия на странице.
     *
     * @return элемент поля "You're logged in!!"
     */
    public WebElement getLoginInMessage() {
        openElement(loginInMessage);
        return loginInMessage;
    }

    /**
     * Метод получения сообщения успешной авторизации - "Home".
     *
     * @return Сообщение успешной авторизации - "Home"
     */
    @Step("Получение Сообщения HOME")
    public String textCheck() {
        return getHomeMessage().getText();
    }

    /**
     * Метод получения сообщения успешной авторизации - "You're logged in!!".
     *
     * @return Сообщение успешной авторизации - "You're logged in!!"
     */
    @Step("Получение Сообщения You're logged in!!")
    public String textCheck2() {
        return getLoginInMessage().getText();
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
