package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class ListenersUtils implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) BaseTest.driver.get()).getScreenshotAs(OutputType.BYTES);
    }
}
