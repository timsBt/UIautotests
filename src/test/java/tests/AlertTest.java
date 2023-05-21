package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;

import static utils.PropertiesUtils.valueProperties;

/**
 * Тест работы со всплывающим оповещением на cтранице ALert.
 */
@Epic(value = "UI Tests")
public class AlertTest extends BaseTest {

    /**
     * Тест работы со всплывающим оповещением.
     */
    @Test(description = "Ввод текста во всплывающее оповещение,"
            + " проверка результата")
    @Feature(value = "Проверка работы Alert")
    @Description("Нажатие кнопок, ввод текста, проверка текста")
    public void alertTest() {
        getDriver().get(valueProperties("alertPageUrl"));
        AlertPage alertPage = new AlertPage(getDriver());
        alertPage.clickInputAlert()
            .switchToFrame()
            .clickButtonDemonstrate()
            .getAlert("Tims");
        Assert.assertEquals(alertPage.getTextAfterAlert(),
                "Hello Tims! How are you today?");
    }
}
