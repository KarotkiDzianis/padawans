package com.epam.padawans.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static final String CHROMEDRIVER_EXE_PATH = "src\\main\\resources\\webdrivers\\chromedriver.exe";
    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CHROMEDRIVER_EXE_PATH);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver;
        } else {
            return driver;
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}