package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomeLogin {

    private WebDriver driver;

    public HomeLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath =  "//h1[@class =  'ng-scope']")
    private WebElement homeMessage;

    @FindBy(xpath =  "//p[@class =  'ng-scope']")
    private WebElement loginInMessage;

    public WebElement getHomeMessage() {
        checkOpen(homeMessage);
        return homeMessage;
    }

    public WebElement getLoginInMessage() {
        checkOpen(loginInMessage);
        return loginInMessage;
    }

    public String textCheck (){
        return getHomeMessage().getText();
    }

    public String textCheck2 (){
        return getLoginInMessage().getText();
    }

    public void checkOpen(WebElement element){
        Assert.assertTrue(element.isDisplayed());
    }
}
