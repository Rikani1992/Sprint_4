import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.object.model.ClientInfoForm;
import page.object.model.ConfirmOrderBanner;
import page.object.model.MainPageYandexScooter;
import page.object.model.RentInfoForm;


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;

    private final String rentDate;
    private final String comment;


    public OrderTest(String firstName, String secondName, String address, String metroStation, String phoneNumber, String rentDate, String comment) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.rentDate = rentDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][] {
                {"Евгений", "Васильев", "Лужская, 5", "Черкизовская", "89113451213", "10.10.2023", "Побыстрее!"},
                {"Петр", "Григорьев", "Апрельская, 12", "Сокольники", "89313214563", "07.10.2023", "Заранее спасибо"},
                {"афыаылья", "Андреев", "Дачный переулок, 3", "аыфвафыв", "111", "01.10.2023", "Ха-ха!"},
        };
    }

    @Before
    public void onStartUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderByHeaderButtonTest() {
        MainPageYandexScooter mainPage = new MainPageYandexScooter(driver);
        mainPage.clickCookieAcceptButton();
        mainPage.clickHeaderOrderButton();

        ClientInfoForm clientInfoForm = new ClientInfoForm(driver);
        clientInfoForm.fillCustomerInfoForm(firstName, secondName, address, metroStation, phoneNumber);

        RentInfoForm rentInfoForm = new RentInfoForm(driver);
        rentInfoForm.fillRentInfoForm(rentDate, comment);

        ConfirmOrderBanner confirmOrderBanner = new ConfirmOrderBanner(driver);
        confirmOrderBanner.confirmOrder();

        Assert.assertTrue("Заказ не оформлен!",
                confirmOrderBanner.isOrderProcessedMessageDisplayed());
    }

    @Test
    public void orderByMiddleSectionButtonTest() {
        MainPageYandexScooter mainPage = new MainPageYandexScooter(driver);
        mainPage.clickCookieAcceptButton();
        mainPage.scrollToMiddleSectionButton();
        mainPage.clickMiddleSectionOrderButton();

        ClientInfoForm clientInfoForm = new ClientInfoForm(driver);
        clientInfoForm.fillCustomerInfoForm(firstName, secondName, address, metroStation, phoneNumber);

        RentInfoForm rentInfoForm = new RentInfoForm(driver);
        rentInfoForm.fillRentInfoForm(rentDate, comment);

        ConfirmOrderBanner confirmOrderBanner = new ConfirmOrderBanner(driver);
        confirmOrderBanner.confirmOrder();

        Assert.assertTrue("Заказ не оформлен!",
                confirmOrderBanner.isOrderProcessedMessageDisplayed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
