package pages;

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

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegistryButton() {
        driver.findElement(registryButton).click();
    }

    public void checkPasswordErrorIsDisplayed() {
//        String expectedText = "Некорректный пароль";
//        String actualText = driver.findElement(wrongPasswordMessage).getText();
//        assertTrue(actualText.contains(expectedText), "Текст не соответствует ожидаемому");
        assertTrue(driver.findElement(wrongPasswordMessage).isDisplayed(), "Текст ошибки не отображается на странице");
    }

}
