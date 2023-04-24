package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SqlPage;
import utils.CookiesFiles;

import static utils.ValueProperties.valueProperties;

@Epic(value = "UI Tests")
public class CookiesAutorizationTest extends BaseTest {

    public static final String SQLPAGE_URL = "https://sql-ex.ru/";
    private SqlPage sqlPage;
    private CookiesFiles cookiesFiles;

    @BeforeClass
    public void setSqlPage() {
        sqlPage = new SqlPage(driver);
        cookiesFiles = new CookiesFiles();
    }

    @BeforeMethod
    public void getDriver() {
        driver.get(SQLPAGE_URL);
    }

    @Test(description = "Авторизация при первом запуске", priority = 1)
    @Feature(value = "Проверка авторизации")
    @Story(value = "Валидные значения")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация при первом запуске")
    public void loginTest() {
        sqlPage.loginInput(valueProperties("usernameSql"))
            .passwordInput(valueProperties("passwordSql"))
            .emptyButtonClick();
        cookiesFiles.fileWrite();
        Assert.assertEquals(sqlPage.textName(), valueProperties("name"));
    }

    @Test(description = "Авторизация с помощью cookies из файла", priority = 2)
    @Feature(value = "Проверка авторизации")
    @Story(value = "Значения cookies")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с помощью cookies из файла")
    public void loginCookiesTest() {
        cookiesFiles.fileRead(SQLPAGE_URL);
        Assert.assertEquals(sqlPage.textName(), valueProperties("name"));
    }
}
