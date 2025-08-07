package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {

    private WebDriver driver;

    // Поле ввода имени
    private By nameInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    // Поле ввода почты
    private By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    // Поле ввода пароля
    private By passwordInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    // Кнопка Зарегистрироваться
    private By registryButton = By.tagName("button");
    // Абзац с текстом "Некорректный пароль"
    private By wrongPasswordMessage = By.xpath("//p[text()=\"Некорректный пароль\"]");
    //Кнопка Войти на странице регистрации
    private By loginButtonOnRegistryPage = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Установка имени в поле ввода")
    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Установка почты в поле ввода")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Установка пароля в поле ввода")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Выбрать кнопку Регистрация")
    public void clickRegistryButton() {
        driver.findElement(registryButton).click();
    }

    @Step("Выбрать кнопку Вход на странице Регистрации")
    public void clickLoginButtonOnRegistryPage() {
        driver.findElement(loginButtonOnRegistryPage).click();
    }

    @Step("Проверка оторажения ошибки некорректного пароля")
    public void checkPasswordErrorIsDisplayed() {
        assertTrue(driver.findElement(wrongPasswordMessage).isDisplayed(), "Текст ошибки не отображается на странице");
    }

}
