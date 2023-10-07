import constants.URL;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class YandexScooterTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void onStartUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(URL.YANDEX_SCOOTER_MAIN_PAGE);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
