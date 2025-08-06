package pages;

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

    public void clickBunsTab() {
        driver.findElements(tabs).get(0).click();
    }

    public void clickSaucesTab() {
        driver.findElements(tabs).get(1).click();
    }

    public void clickFillingsTab() {
        driver.findElements(tabs).get(2).click();
    }

    public void checkCurrentTabIsActive(String tabName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'tab_tab_type_current')]"))).getText().contains(tabName);
    }
}
