package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.RegistrationPage;
import utils.Browser;
import utils.UserApiHelper;

public class RegistrationTest {

    private final String registerPageUrl = "https://stellarburgers.nomoreparties.site/register";
    private WebDriver driver;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    private String name = "Noel";
    private String email = "monfils88@mail.com";
    private String password = "tennis";
    private boolean userShouldBeDeleted = false;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = Browser.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(registerPageUrl);
        registrationPage = new RegistrationPage(driver);
        authorizationPage = new AuthorizationPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void registrationSuccess() {
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.clickRegistryButton();
        authorizationPage.checkLoginHeaderIsDisplayed();
        userShouldBeDeleted = true;
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля")
    public void registrationPasswordWithFiveLettersErrorCheck() {
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword("tenni");
        registrationPage.clickRegistryButton();
        registrationPage.checkPasswordErrorIsDisplayed();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();

        if (userShouldBeDeleted) {
            UserApiHelper.deleteUser(email, password);
        }
    }
}
