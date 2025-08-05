package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class Browser {

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "yandex":
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createYandexDriver() {
        // Указать путь к исполняемому файлу Яндекс.Браузера
        String yandexPath = System.getProperty("yandex.binary");

        if (yandexPath == null || !new File(yandexPath).exists()) {
            throw new RuntimeException("Yandex browser binary not found: " + yandexPath);
        }

        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File(yandexPath));
        return new ChromeDriver(options);
    }
}
