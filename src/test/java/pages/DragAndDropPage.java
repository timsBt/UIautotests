package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DragAndDropPage {

    private WebDriver driver;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id=\"draggable\"]")
    private WebElement drag;

    @FindBy(xpath = "//div[@id=\"droppable\"]")
    private WebElement drop;

    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame;

    public WebElement getDrag() {
        openElement(drag);
        return drag;
    }

    public WebElement getDrop() {
        openElement(drop);
        return drop;
    }

    public WebElement getFrame() {
        openElement(frame);
        return frame;
    }

    @Step("Переносим элемент")
    public DragAndDropPage dragNDrop() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getDrag(), getDrop()).build().perform();
        return this;
    }

    @Step("Переключаемся на фрейм")
    public DragAndDropPage switchToFrame() {
        driver.switchTo().frame(getFrame());
        return this;
    }

    @Step("Получаем текст")
    public String getTextDropp() {
        return getDrop().getText();
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }
}
