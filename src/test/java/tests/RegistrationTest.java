package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;
import utils.Browser;
import utils.UserApiHelper;

public class RegistrationTest {

    private WebDriver driver;
    RegistrationPage registrationPage;
    private String name = "Noel";
    private String email = "monfils88@mail.com";
    private String password = "tennis";
    private boolean userShouldBeDeleted = false;


    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = Browser.createDriver(browser);
        driver.manage().window().maximize();

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/register");

        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void registrationSuccess() {
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.clickRegistryButton();
//        registrationPage.checkLoginHeaderIsDisplayed();
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
