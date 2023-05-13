package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static utils.ChromeDriverUtils.getChromeDriver;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();

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
        driver.get().close();
        driver.remove();
    }
}
