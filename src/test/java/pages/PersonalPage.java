package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalPage {

    private WebDriver driver;

    // Секция профиля в личном кабинете
    private By profileSection = By.className("Profile_profile__3dzvr");
    // Кнопка Выход
    private By exitButton = By.className("Account_button__14Yp3");

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickExitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton)).click();
    }

    public void checkProfileSectionIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileSection));
    }
}
