package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicAuthPage;

@Epic(value = "UI Tests")
public class BasicAuthTest extends BaseTest {

    private final String basicUrl = "https://www.httpwatch.com/httpgallery/authentication/#showExample10";

    @Test(description = "Авторизация с Basic Authentication")
    @Feature(value = "Проверка работы Basic Authentication")
    @Description("Нажатие кнопки, ввод значений, проверка авторизации")
    public void basicAuthenticationTest() {
        BasicAuthPage basicAuthPage = new BasicAuthPage(getDriver())
            .authentication("httpwatch", "httpwatch");
        getDriver().get(basicUrl);
        basicAuthPage.clickDisplayImage();
        Assert.assertTrue(basicAuthPage.authenticationImage());
    }
}
