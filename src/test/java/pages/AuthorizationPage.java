package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {

    private WebDriver driver;

    // Заголовок страницы Вход
    private By loginHeader = By.xpath("//h2[text()='Вход']");
    // Поле ввода почты
    private By emailInput = By.xpath("//form/fieldset[1]//input");
    // Поле ввода пароля
    private By passwordInput = By.xpath("//form/fieldset[2]//input");
    // Кнопка Войти
    private By loginButton = By.tagName("button");

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка отображения заголовка 'Вход'")
    public void checkLoginHeaderIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
    }

    @Step("Установка почты")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Установка пароля")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку Входа")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
