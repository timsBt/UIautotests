package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverChrome;
import java.time.Duration;

public class BaseTest {

   public static WebDriver driver;
   public static final String MAIN_URL = "https://www.way2automation.com/angularjs-protractor/registeration/#/login";

    @BeforeClass
    public void setUp() {
        driver = WebDriverChrome.getChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void setgetDriver() {
        driver.get(MAIN_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
