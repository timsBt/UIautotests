package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FramesAndWindowsPage;
import pages.NewBroserTabsPage;

import java.util.ArrayList;

import static utils.PropertiesUtils.valueProperties;

/**
 * Тест работы со вкладками и ссылками на странице Frames And Windows.
 */
@Epic(value = "UI Tests")
public class TabsTest extends BaseTest {

    /**
     * Метод открытия страницы.
     */
    @BeforeMethod
    public void openPage() {
        getDriver().get(valueProperties("tabsPageUrl"));
    }

    /**
     * Тест открытия новых вкладок.
     */
    @Test(description = "Открытие новых вкладок")
    @Feature(value = "Проверка работы Tabs")
    @Description("Открытие новых вкладок")
    public void tabsFrameTest() {
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(
                getDriver());
        NewBroserTabsPage newBroserTabsPage = new NewBroserTabsPage(
                getDriver());
        framesAndWindowsPage.switchToFrame()
            .clickButton();
        for (String tab : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(tab);
        }
        newBroserTabsPage.clickButtonNewBrowserPage();
        ArrayList<String> tabs2 = new ArrayList(getDriver().getWindowHandles());
        final int count = 3;
        Assert.assertEquals(tabs2.size(), count,
                "Количество вкладок должно быть 3!");
    }
}
