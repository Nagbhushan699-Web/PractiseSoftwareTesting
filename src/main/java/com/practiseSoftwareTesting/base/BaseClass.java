package com.practiseSoftwareTesting.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.practiseSoftwareTesting.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public static WebDriver driver;
    public static Properties prop;

    public void setup() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Fixed wait time
            
            // Get environment dynamically (default: "branch")
            String env = System.getProperty("env", "branch");
            prop = ConfigReader.getPropertiesFile(env);

            driver.get(prop.getProperty("url"));
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset driver for the next test execution
        }
    }
}
