package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CustomDataProvider;
import pages.HomeLogin;
import pages.MainPage;

import static utils.ValueProperties.valueProperties;

@Epic(value = "UI Tests DataProvider")
public class DataProviderAutorizationTests extends BaseTest {

    private MainPage mainPage;
    private HomeLogin homeLogin;

    @BeforeClass
    public void setPages() {
        mainPage = new MainPage(driver);
        homeLogin = new HomeLogin(driver);
    }

    @Test(description = "TC-1 Авторизация с валидными данными",
            dataProvider = "LoginDataProvider",dataProviderClass = CustomDataProvider.class)
    @Feature(value = "Проверка авторизации")
    @Story(value = "Валидные значения")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с валидными данными")
    public void validLoginTest (String userName,String password,String username2) {
        mainPage.userNameInput(userName)
                .passwordInput(password)
                .userName2Input(username2)
                .loginButtonClick();
        Assert.assertEquals(homeLogin.textCheck(),valueProperties("homeMessage"));
        Assert.assertEquals(homeLogin.textCheck2(),valueProperties("loginInMessage"));
    }

    @Test(description = "TC-2 Авторизация с НЕвалидными данными",
            dataProvider = "inValidLoginDataProvider",dataProviderClass = CustomDataProvider.class)
    @Feature(value = "Проверка авторизации")
    @Story(value = "НЕвалидные значения")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Авторизация с НЕвалидными данными")
    public void inValidloginTest (String userName,String password,String username2) {
        mainPage.userNameInput(userName)
                .passwordInput(password)
                .userName2Input(username2)
                .loginButtonClick();
        Assert.assertEquals(mainPage.textCheckErrorMessage(),valueProperties("errorMessage"));
    }
}
