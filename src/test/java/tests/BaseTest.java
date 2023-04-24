package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ChromeDriverUtils;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = ChromeDriverUtils.getChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
