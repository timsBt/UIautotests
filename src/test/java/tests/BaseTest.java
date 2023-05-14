package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.RetryClassUtils;

import java.time.Duration;

import static utils.ChromeDriverUtils.getChromeDriver;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();

    @BeforeSuite
    public void setRetry(ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryClassUtils.class);
        }
    }

    @BeforeMethod
    public void setUp() {
        driver.set(getChromeDriver());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        driver.get().quit();
        driver.remove();
    }
}
