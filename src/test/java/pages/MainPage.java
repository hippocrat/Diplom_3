package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    // Кнопка Конструктора в хэдере
    private By constructorButton = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    // Кнопка лого сайта
    private By mainLogoButton = By.className("AppHeader_header__logo__2D0X2");
    // Кнопка Личный кабинет
    private By personalPageButton = By.xpath("//*[@id=\"root\"]/div/header/nav/a");
    // Кнопка Войти в аккаунт
    private By loginButton = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");
    // Кнопка Оформить заказ
    private By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    // Заголовок "Соберите бургер"
    private By collectBurgerHeader = By.xpath("//h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickMainLogoButton() {
        driver.findElement(mainLogoButton).click();
    }

    public void clickPersonalPageButton() {
        driver.findElement(personalPageButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void checkMakeOrderButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
    }

    public void checkCollectBurgerHeaderIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(collectBurgerHeader));
    }
}
