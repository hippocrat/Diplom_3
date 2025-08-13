package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Browser;

@Epic("Авторизация")
@Feature("Вход в аккаунт")
public class AuthorizationTest {

    private WebDriver driver;
    MainPage mainPage;
    AuthorizationPage authorizationPage;
    RegistrationPage registrationPage;
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
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Story("Вход по кнопке на главной")
    @Description("Проверка входа через кнопку «Войти в аккаунт» на главной странице")
    public void loginWithLoginButtonOnMainPageSuccess() {
        mainPage.clickLoginButton();
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        mainPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginWithPersonalPageButtonOnMainPageSuccess() {
        mainPage.clickPersonalPageButton();
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        mainPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginWithButtonOnRegistrationPageSuccess() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registrationPage.clickLoginButtonOnRegistryPage();
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        mainPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginWithButtonOnRestorePageSuccess() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        registrationPage.clickLoginButtonOnRegistryPage();
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        mainPage.checkMakeOrderButtonIsDisplayed();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
