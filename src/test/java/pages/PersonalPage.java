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

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkProfileSectionIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileSection));
    }
}
