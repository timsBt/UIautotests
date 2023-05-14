package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

@Epic(value = "UI Tests")
public class DragAndDropTest extends BaseTest {

    private final String dropUrl = "http://way2automation.com/way2auto_jquery/droppable.php";

    @BeforeMethod
    public void openPage() {
        getDriver().get(dropUrl);
    }

    @Test(description = "Перетаскивание элемента")
    @Feature(value = "Проверка работы DragAndDrop")
    @Description("Перетаскивание элемента")
    public void actionTest() {
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver());
        dragAndDropPage.switchToFrame();
        dragAndDropPage.dragNDrop();
        Assert.assertEquals(dragAndDropPage.getTextDropp(), "Dropped!");
    }
}
