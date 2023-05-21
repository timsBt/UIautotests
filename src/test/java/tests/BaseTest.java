package tests;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeSuite;
import utils.RetryClassUtils;

import java.net.MalformedURLException;
import java.time.Duration;

/**
 * Базовый класс с настройками для всех тестов.
 */
public class BaseTest {

    /**
     * Объявление экземпляра драйвера.
     */
    private static final ThreadLocal<WebDriver> DRIVER
            = new InheritableThreadLocal<>();

    /**
     * Метод логики перезапуска теста.
     *
     * @param context Информация о запускаемом тесте
     */
    @BeforeSuite
    public void setRetry(final ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryClassUtils.class);
        }
    }

    /**
     * Метод настройки и выбора браузера перед тестом.
     *
     * @param browserType Название браузера
     * @param remote      удаленно (true) или локально (false)
     */
    @BeforeMethod
    @Parameters({"BrowserType", "Remote"})
    public void setUp(@Optional("IE") final String browserType,
                      @Optional("false") final String remote)
            throws MalformedURLException {
        DRIVER.set(DriverFactory.getBrowsers(browserType, remote));
        getDriver().manage().window().maximize();
        final int seconds = 10;
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Метод получения экземпляра драйвера.
     *
     * @return Экземпляр драйвера
     */
    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    /**
     * Закрытие драйвера после теста.
     */
    @AfterMethod
    public void tearDown() {
        DRIVER.get().quit();
        DRIVER.remove();
    }
}
