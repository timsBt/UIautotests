package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;



import java.time.Duration;

public class BaseTest {

    public static WebDriver driver = utils.WebDriver.getChromeDriver();
    public static String pageUrl = "https://www.way2automation.com/angularjs-protractor/registeration/#/login";

    @BeforeMethod
    public static void setUp() {
        driver.get(pageUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public static void tearDown() {
            driver.quit();
    }
}