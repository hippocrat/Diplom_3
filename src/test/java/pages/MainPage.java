package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    // Кнопка Конструктора в хэдере
    private By constructorButton = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    // Кнопка лого сайта
    private By mainLogoButton = By.className("AppHeader_header__logo__2D0X2");
    // Кнопка Личный кабинет
    private By personalPageButton = By.xpath("//*[@id=\"root\"]/div/header/nav/a");

}
