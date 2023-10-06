package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClientInfoForm {

    private final WebDriver driver;

    // Форма оформления заказа
    private final By customerInfoForm = By.className("Order_Content__bmtHS");

    // Имя
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");

    // Фамилия
    private final By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Адрес куда привезти заказ
    private final By addressField =
            By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле станции метро
    private final By metroStationField = By.className("select-search__value");

    private final By metroStationDropDownMenu =
            By.xpath(".//div[@class='select-search__select']");


    // Номер телефона
    private final By phoneNumberField =
            By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка далее
    private final By nextStepButton = By.className("Button_Middle__1CSJM");

    public ClientInfoForm(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNextStepButton() {
        driver.findElement(nextStepButton).click();
    }

    public void waitForMetroStationDropDownMenuDisplayed() {
        new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOfElementLocated(metroStationDropDownMenu));
    }

    public void setMetroStation(String metroStation) {
        driver.findElement(metroStationField).click();
        waitForMetroStationDropDownMenuDisplayed();
        String path = ".//div[@class='Order_Text__2broi' and contains(text(), '"+metroStation+"')]/parent::button";
        driver.findElement(By.xpath(path)).click();
    }

    public void waitForNextStepButtonIsClickable() {
        new WebDriverWait(driver, 2).until(
                ExpectedConditions.elementToBeClickable(nextStepButton));
    }

    public void waitForClientInfoFormDisplayed() {
        new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOfElementLocated(customerInfoForm));
    }

    public void fillCustomerInfoForm(String firstName, String secondName,
                                     String address, String metroStation, String phoneNumber) {
        waitForClientInfoFormDisplayed();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(secondNameField).sendKeys(secondName);
        driver.findElement(addressField).sendKeys(address);
        setMetroStation(metroStation);

        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        waitForNextStepButtonIsClickable();
        clickNextStepButton();
    }

}
