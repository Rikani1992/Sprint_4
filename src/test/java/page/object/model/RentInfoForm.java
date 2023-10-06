package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class RentInfoForm {

    private final WebDriver driver;

    // Форма ввода допольнительной информации об аренде
    private final By rentInfoForm = By.className("Order_Content__bmtHS");

    //Дата, когда нужно привезти самокат
    private final By rentDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    private final By selectedDataPickerCell = By.className("react-datepicker__day--selected");

    // Поле выбора срока аренды
    private final By rentTermField = By.className("Dropdown-placeholder");

    // Выпадающее меню выбора срока аренды
    private final By termDropDownMenu = By.className("Dropdown-menu");

    // Варианты выбора срока аренды
    private final By termDropDownOptions = By.className("Dropdown-option");

    //Чекбокс выбора черного цвета самоката
    private final By blackColorCheckBox = By.id("black");

    //Чекбокс выбора серого цвета самоката
    private final By greyColorCheckBox = By.id("grey");

    // Поле комментрария для курьера
    private final By commentField =
            By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка заказать после заполнения формы
    private final By makeOrderButton =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    public RentInfoForm(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectedDataPickerCell() {
        driver.findElement(selectedDataPickerCell).click();
    }

    public void clickTermPlaceHolder() {
        driver.findElement(rentTermField).click();
    }

    public void waitForRentInfoFormDisplayed() {
        new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOfElementLocated(rentInfoForm));
    }

    public void waitForTermMenuDropsDown() {
        new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOfElementLocated(termDropDownMenu));
    }

    public void setTermOption() {
        waitForTermMenuDropsDown();
        List<WebElement> options = driver.findElements(termDropDownOptions);
        int i = new Random().nextInt(options.size());
        options.get(i).click();
    }

    public void setBlackColorCheckBox() {
        driver.findElement(blackColorCheckBox).click();
    }

    public void setGreyColorCheckBox() {
        driver.findElement(greyColorCheckBox).click();
    }

    public void clickMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
    }

    public void fillRentInfoForm(String rentDate, String comment) {
        waitForRentInfoFormDisplayed();
        driver.findElement(rentDateField).sendKeys(rentDate);
        clickSelectedDataPickerCell();
        clickTermPlaceHolder();
        setTermOption();
        setBlackColorCheckBox();
        driver.findElement(commentField).sendKeys(comment);
        clickMakeOrderButton();
    }

}
