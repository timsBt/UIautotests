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

@Epic(value = "UI Tests")
public class TabsTest extends BaseTest {

    private final String tabsUrl = "http://way2automation.com/way2auto_jquery/frames-and-windows.php";

    @BeforeMethod
    public void openPage() {
        getDriver().get(tabsUrl);
    }

    @Test(description = "Открытие новых вкладок")
    @Feature(value = "Проверка работы Tabs")
    @Description("Открытие новых вкладок")
    public void tabsFrameTest() {
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(getDriver());
        NewBroserTabsPage newBroserTabsPage = new NewBroserTabsPage(getDriver());
        framesAndWindowsPage.switchToFrame()
            .clickButton();
        for (String tab : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(tab);
        }
        newBroserTabsPage.clickButtonNewBrowserPage();
        ArrayList<String> tabs2 = new ArrayList(getDriver().getWindowHandles());
        Assert.assertEquals(tabs2.size(), 3, "Количество вкладок должно быть 3!");
    }
}
