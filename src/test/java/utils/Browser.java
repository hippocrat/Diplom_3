package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        String yandexPath;
        String driverPath;

        if (osName.contains("win")) {
            // Windows
            yandexPath = "C:/Users/" + System.getProperty("user.name")
                    + "/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";
            driverPath = "C:/drivers/yandexdriver.exe";

        } else if (osName.contains("mac")) {
            // macOS
            yandexPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
            driverPath = "/usr/local/bin/yandexdriver";

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

