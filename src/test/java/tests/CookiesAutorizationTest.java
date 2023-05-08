package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.SqlPage;
import utils.CookiesUtils;

import java.io.File;

import static utils.PropertiesUtils.valueProperties;

/**
 * Тест авторизации с помощью Cookies на странице Упражнения по SQL.
 */
@Epic(value = "UI Tests")
public class CookiesAutorizationTest extends BaseTest {

    /**
     * Объявление экземпляра файла.
     */
    private final File file = new File("Cookies.data");

    /**
     * Объявление переменной (количество запусков теста).
     */
    private final int count = 3;

    /**
     * Тест авторизации с помощью Cookies.
     *
     * @param testContext Данные теста
     */
    @Test(description = "Авторизация с помощью cookies из файла",
            invocationCount = count)
    @Feature(value = "Проверка авторизации")
    @Story(value = "Cookies")
    @Severity(value = SeverityLevel.BLOCKER)
    @Description("Авторизация с помощью cookies из файла")
    public void loginCookiesTest(final ITestContext testContext) {
        SqlPage sqlPage = new SqlPage(getDriver());
        CookiesUtils cookiesUtils = new CookiesUtils(getDriver());
        getDriver().get(valueProperties("sqlPageUrl"));
        int currentCount = testContext.getAllTestMethods()[0]
            .getCurrentInvocationCount();
        if (currentCount == 0 && !file.isFile()) {
            sqlPage.loginSql(valueProperties("usernameSql"),
                    valueProperties("passwordSql"));
            Assert.assertEquals(sqlPage.textName(), valueProperties("name"));
            cookiesUtils.writeCookieToFile();
            getDriver().manage().deleteAllCookies();
            sqlPage.logOut();
        } else if (currentCount > 0 && file.isFile()) {
            cookiesUtils.readCookieFromFile();
            getDriver().navigate().refresh();
            Assert.assertEquals(sqlPage.textName(), valueProperties("name"));
            getDriver().manage().deleteAllCookies();
            sqlPage.logOut();
        }
    }

    /**
     * Метод удаления файла с Cookies после всех тестов.
     */
    @AfterClass
    public void deleteCookiesFile() {
        CookiesUtils cookiesUtils = new CookiesUtils(getDriver());
        cookiesUtils.deleteCookiesFile();
    }
}
