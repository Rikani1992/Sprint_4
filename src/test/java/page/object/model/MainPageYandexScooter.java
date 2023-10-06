package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPageYandexScooter {

    private final WebDriver driver;

    // Кнопка принятия соглашения использования куки файлов
    private final By cookieAcceptButton = By.className("App_CookieButton__3cvqF");

    //Кнопка заказа в шапке странице
    private final By headerOrderButton = By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']");

    //Кнопка заказа в середине страницы
    private final By middleSectionOrderButton = By.className("Button_Middle__1CSJM");

    public MainPageYandexScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    public void scrollToQuestions() {
        WebElement element = driver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOnQuestion(String questionText) {
        By question = By.xpath(".//div[@class='accordion__button' and text()='" + questionText + "']");
        driver.findElement(question).click();
    }

    public boolean isCorrespondingAnswerDisplayed(String answerText) {
        By answer = By.xpath(".//div[@class='accordion__panel']/p[text()='" + answerText + "']");
        new WebDriverWait(driver, 3).until(
                ExpectedConditions.visibilityOf(driver.findElement(answer)));
        return driver.findElement(answer).isDisplayed();
    }

    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    public void scrollToMiddleSectionButton() {
        WebElement element = driver.findElement(middleSectionOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickMiddleSectionOrderButton() {
        driver.findElement(middleSectionOrderButton).click();
    }

}
