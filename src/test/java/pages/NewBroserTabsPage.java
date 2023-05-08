package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class NewBroserTabsPage {

    private WebDriver driver;

    public NewBroserTabsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @FindBy(xpath = "//a[@target=\"_blank\"]")
    private WebElement newBrowserTab;

    public WebElement getNewBrowserTab() {
        openElement(newBrowserTab);
        return newBrowserTab;
    }

    @Step("Нажимаем на ссылку во второй вкладке")
    public NewBroserTabsPage clickButtonNewBrowserPage() {
        getNewBrowserTab().click();
        return new NewBroserTabsPage(driver);
    }
}
