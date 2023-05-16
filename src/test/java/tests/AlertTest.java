package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;

@Epic(value = "UI Tests")
public class AlertTest extends BaseTest {

    private final String alertUrl = "http://way2automation.com/way2auto_jquery/alert.php";

    @Test(description = "Ввод текста во всплывающее оповещение, проверка результата")
    @Feature(value = "Проверка работы Alert")
    @Description("Нажатие кнопок, ввод текста, проверка текста")
    public void alertTest() {
        getDriver().get(alertUrl);
        AlertPage alertPage = new AlertPage(getDriver());
        alertPage.clickInputAlert()
            .switchToFrame()
            .clickButtonDemonstrate()
            .getAlert("Tims");
        Assert.assertEquals(alertPage.getTextAfterAlert(), "Hello Tims! How are you today?");
    }
}
