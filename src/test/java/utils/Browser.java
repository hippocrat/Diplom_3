package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
        String osName = System.getProperty("os.name").toLowerCase();

        Properties props = new Properties();
        try (InputStream input = Browser.class.getResourceAsStream("/config.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }

        String yandexPath;
        String driverPath;

        if (osName.contains("win")) {
            yandexPath = props.getProperty("yandex.path.windows")
                    .replace("${user.home}", System.getProperty("user.home"));
            driverPath = props.getProperty("yandex.driver.windows")
                    .replace("${user.home}", System.getProperty("user.home"));
        } else if (osName.contains("mac")) {
            yandexPath = props.getProperty("yandex.path.mac");
            driverPath = props.getProperty("yandex.driver.mac");
        } else {
            throw new RuntimeException("Yandex browser path not configured for OS: " + osName);
        }

        if (!Files.exists(Paths.get(yandexPath))) {
            throw new RuntimeException("Yandex Browser not found at: " + yandexPath);
        }

        if (!Files.exists(Paths.get(driverPath))) {
            throw new RuntimeException("YandexDriver not found at: " + driverPath);
        }

        // Устанавливаем системное свойство для драйвера
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File(yandexPath));
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }

}

