package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.BaseTest;
import utils.Browser;

import static pages.AuthorizationPage.*;

@Epic("Авторизация")
@Feature("Вход в аккаунт")
public class AuthorizationTest extends BaseTest {

    private WebDriver driver;
    MainPage mainPage;
    AuthorizationPage authorizationPage;
    RegistrationPage registrationPage;
    private static String email = "hilokea@yandex.ru";
    private static String password = "kassian";
    private static String name = "Kolo";

    @BeforeAll
    public static void setUpAll() {
        RestAssured.baseURI = MAIN_URL;
        registerUser(email, password, name)
                .then()
                .statusCode(200);
    }

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = Browser.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(MAIN_URL);
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
        driver.get(REGISTER_PAGE_URL);
        registrationPage.clickLoginButtonOnRegistryPage();
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        mainPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginWithButtonOnRestorePageSuccess() {
        driver.get(FORGOT_PASSWORD_PAGE_URL);
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

    @AfterAll
    public static void tearDownAll() {
        String requestBody = "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response response = loginUser(requestBody);
        String token = response.jsonPath().getString("accessToken");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            deleteUser(token);
        }
    }
}
