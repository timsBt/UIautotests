package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FramesAndWindowsPage {

    private WebDriver driver;

    public FramesAndWindowsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame;

    @FindBy(xpath = "//a[@target=\"_blank\"]")
    private WebElement newBrowserTab;

    public WebElement getFrame() {
        openElement(frame);
        return frame;
    }

    public WebElement getNewBrowserTab() {
        openElement(newBrowserTab);
        return newBrowserTab;
    }

    @Step("Нажимаем на ссылку в первой вкладке")
    public NewBroserTabsPage clickButton() {
        getNewBrowserTab().click();
        return new NewBroserTabsPage(driver);
    }

    @Step("Переключаемся на фрейм")
    public FramesAndWindowsPage switchToFrame() {
        driver.switchTo().frame(getFrame());
        return this;
    }
}
