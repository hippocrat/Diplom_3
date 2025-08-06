package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.PersonalPage;
import utils.Browser;

public class PersonalPageTest {

    private WebDriver driver;
    MainPage mainPage;
    AuthorizationPage authorizationPage;
    PersonalPage personalPage;
    private String email = "hilokea@yandex.ru";
    private String password = "kassian";

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = Browser.createDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage = new MainPage(driver);
        authorizationPage = new AuthorizationPage(driver);
        personalPage = new PersonalPage(driver);
        mainPage.clickPersonalPageButton();
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        mainPage.clickPersonalPageButton();
    }

    @Test
    @DisplayName("Проверка перехода в «Личный кабинет»")
    public void transitionToPersonalPageCheck() {
        personalPage.checkProfileSectionIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода в Конструктор из «Личного кабинета»")
    public void transitionToConstructorFromPersonalPageCheck() {
        mainPage.clickConstructorButton();
        mainPage.checkCollectBurgerHeaderIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода в Конструктор из «Личного кабинета» нажатием логотипа сайта")
    public void clickLogoAndTransitionToConstructorFromPersonalPageCheck() {
        mainPage.clickMainLogoButton();
        mainPage.checkCollectBurgerHeaderIsDisplayed();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
