package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmOrderBanner {

    private final WebDriver driver;

    // Баннер с запросом подтверждения заказа
    private final By confirmBanner = By.className("Order_Modal__YZ-d3");

    // Кнопка "да" на баннере подтверждения заказа
    private final By confirmOrderButton =
            By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

    // Сообщение о том, что закза оформлен
    private final By orderIsProcessedMessage =
            By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");


    public ConfirmOrderBanner(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForConfirmOrderBannerDisplayed() {
        new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOfElementLocated(confirmBanner));
    }

    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isOrderProcessedMessageDisplayed() {
        return driver.findElement(orderIsProcessedMessage).isDisplayed();
    }

    public void confirmOrder() {
        waitForConfirmOrderBannerDisplayed();
        clickConfirmOrderButton();
    }

}
