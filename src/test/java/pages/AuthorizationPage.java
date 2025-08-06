package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage {

    private WebDriver driver;

    // Поле ввода почты
    private By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    // Поле ввода пароля
    private By passwordInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    // Кнопка Войти
    private By loginButton = By.tagName("button");

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
