package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;

    // Вкладки
    private By tabs = By.className("tab_tab__1SPyG");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Выбрать вкладку Булки")
    public void clickBunsTab() {
        driver.findElements(tabs).get(0).click();
    }

    @Step("Выбрать вкладку Соусы")
    public void clickSaucesTab() {
        driver.findElements(tabs).get(1).click();
    }

    @Step("Выбрать вкладку Начинки")
    public void clickFillingsTab() {
        driver.findElements(tabs).get(2).click();
    }

    @Step("Проверка, что текущая вкладка активна")
    public void checkCurrentTabIsActive(String tabName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'tab_tab_type_current')]"))).getText().contains(tabName);
    }
}
