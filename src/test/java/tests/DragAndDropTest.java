package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

import static utils.PropertiesUtils.valueProperties;

/**
 * Тест перетаскивания элемента на странице Droppable.
 */
@Epic(value = "UI Tests")
public class DragAndDropTest extends BaseTest {

    /**
     * Метод открытия страницы.
     */
    @BeforeMethod
    public void openPage() {
        getDriver().get(valueProperties("droppblePageUrl"));
    }

    /**
     * Тест перетаскивания элемента.
     */
    @Test(description = "Перетаскивание элемента")
    @Feature(value = "Проверка работы DragAndDrop")
    @Description("Перетаскивание элемента")
    public void actionTest() {
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver());
        dragAndDropPage.switchToFrame()
            .dragNDrop();
        Assert.assertEquals(dragAndDropPage.getTextDropp(), "Dropped!");
    }
}
