package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

/**
 * Класс утилит для создания отчетов.
 */
public class ListenersUtils implements ITestListener {

    /**
     * Метод для создания скриншота после падения теста.
     */
    @Override
    public void onTestFailure(final ITestResult result) {
        makeScreenshot();
    }

    /**
     * Метод получения скриншота и прикрепление его к отчету.
     *
     * @return созданный скриншот
     */
    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) BaseTest.getDriver())
            .getScreenshotAs(OutputType.BYTES);
    }
}
