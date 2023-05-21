package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Класс создания WebDrivers для запуска браузеров удаленно и локально.
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    /**
     * Метод выбора запуска тестов локально или удаленно.
     *
     * @param browserType Название браузера
     * @param remote      удаленно (true) или локально (false)
     * @return если true, то запуск Удаленно, иначе false, то запуск локально
     */
    public static WebDriver getBrowsers(final String browserType,
                                        final String remote)
            throws MalformedURLException {
        if (remote.equals("true")) {
            return remoteBrowsersDrivers(browserType);
        } else {
            return crossBrowsersDrivers(browserType);
        }
    }

    /**
     * Метод выбора запуска браузера, локально.
     *
     * @param browserType Название браузера
     * @return экземпляр драйвера для браузера
     */
    public static WebDriver crossBrowsersDrivers(final String browserType) {
        if (browserType.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver(new InternetExplorerOptions()
                .attachToEdgeChrome()
                .ignoreZoomSettings()
                .introduceFlakinessByIgnoringSecurityDomains());
        } else {
            throw new IllegalArgumentException("Браузер не определен!");
        }
    }

    /**
     * Метод выбора,и настройка запуска браузера, удаленно.
     *
     * @param browserType Название браузера
     * @return экземпляр драйвера для запуска браузера удаленно
     */
    public static WebDriver remoteBrowsersDrivers(final String browserType)
            throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (browserType.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            capabilities.setBrowserName(Browser.CHROME.browserName());
            capabilities.setPlatform(Platform.WIN11);
            chromeOptions.merge(capabilities);
            return new RemoteWebDriver(new URL("http://localhost:4444/"),
                    chromeOptions);
        } else if (browserType.equalsIgnoreCase("Edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            capabilities.setBrowserName(Browser.EDGE.browserName());
            capabilities.setPlatform(Platform.WIN11);
            edgeOptions.merge(capabilities);
            return new RemoteWebDriver(new URL("http://localhost:4444/"),
                    edgeOptions);
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            capabilities.setBrowserName(Browser.FIREFOX.browserName());
            capabilities.setPlatform(Platform.WIN11);
            firefoxOptions.merge(capabilities);
            return new RemoteWebDriver(new URL("http://localhost:4444/"),
                    firefoxOptions);
        } else if (browserType.equalsIgnoreCase("IE")) {
            InternetExplorerOptions internetExplorerOptions
                    = new InternetExplorerOptions();
            capabilities.setBrowserName(Browser.IE.browserName());
            capabilities.setPlatform(Platform.WIN11);
            internetExplorerOptions.attachToEdgeChrome().ignoreZoomSettings()
                .introduceFlakinessByIgnoringSecurityDomains()
                .merge(capabilities);
            return new RemoteWebDriver(new URL("http://localhost:4444/"),
                    internetExplorerOptions);
        } else {
            throw new IllegalArgumentException("Браузер не определен!");
        }
    }
}
