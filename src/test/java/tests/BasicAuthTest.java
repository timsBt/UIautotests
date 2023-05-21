package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicAuthPage;

import static utils.PropertiesUtils.valueProperties;

/**
 * Тест авторизации с Basic Authentication на странице Httpwatch.
 */
@Epic(value = "UI Tests")
public class BasicAuthTest extends BaseTest {

    /**
     * Тест авторизации с Basic Authentication.
     */
    @Test(description = "Авторизация с Basic Authentication")
    @Feature(value = "Проверка работы Basic Authentication")
    @Description("Нажатие кнопки, ввод значений, проверка авторизации")
    public void basicAuthenticationTest() {
        BasicAuthPage basicAuthPage = new BasicAuthPage(getDriver());
        final String name = "httpwatch";
        final String password = "httpwatch";
        getDriver().get("https://" + name + ":" + password + "@"
                + valueProperties("basicPageUrl"));
        basicAuthPage.clickDisplayImage();
        Assert.assertTrue(basicAuthPage.authenticationImage());
    }
}
