package tests;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.WebDriverChrome;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverChrome.getChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void testFailure(ITestResult testResult) {
        if (testResult.FAILURE == testResult.getStatus()) {
            makeScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        }
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot(byte[] screenShot) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
