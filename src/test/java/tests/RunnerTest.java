package tests;

import drivers.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.time.Duration;

@Epic(value = "UI Tests")
@Feature(value = "Проверка авторизации Cucumber")
@CucumberOptions(features = {"src/test/java/cucumber/autorization.feature"},
        glue = {"cucumber"})
public class RunnerTest extends AbstractTestNGCucumberTests {

    /**
     * Объявление экземпляра драйвера.
     */
    private static final ThreadLocal<WebDriver> DRIVER
            = new InheritableThreadLocal<>();

    /**
     * Метод настройки и выбора браузера перед тестом.
     *
     * @param browserType Название браузера
     * @param remote      удаленно (true) или локально (false)
     */
    @BeforeMethod
    @Parameters({"BrowserType", "Remote"})
    public void setUp(@Optional("Chrome") final String browserType,
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
