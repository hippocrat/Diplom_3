package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.ConstructorPage;
import pages.MainPage;
import utils.Browser;

public class ConstructorTest {

    private final String mainUrl = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    MainPage mainPage;
    ConstructorPage constructorPage;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = Browser.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(mainUrl);
        mainPage = new MainPage(driver);
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void transitionToSaucesTabCheck() {
        constructorPage.clickSaucesTab();
        constructorPage.checkCurrentTabIsActive("Соусы");
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void transitionToFillingsTabCheck() {
        constructorPage.clickFillingsTab();
        constructorPage.checkCurrentTabIsActive("Начинки");
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void transitionToBunsTabCheck() {
        constructorPage.clickFillingsTab();
        constructorPage.clickBunsTab();
        constructorPage.checkCurrentTabIsActive("Булки");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
