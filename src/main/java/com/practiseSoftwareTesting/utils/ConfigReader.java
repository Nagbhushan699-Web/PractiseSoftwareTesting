package com.practiseSoftwareTesting.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static Properties prop;

    public static Properties getPropertiesFile(String env) {
        prop = new Properties();
        String filePath;

        switch (env.toLowerCase()) {
            case "branch":
                filePath = PROJECT_PATH + File.separator + "config" + File.separator + "Branch.properties";
                break;
            case "staging":
                filePath = PROJECT_PATH + File.separator + "config" + File.separator + "Staging.properties";
                break;
            default:
                throw new IllegalArgumentException("Invalid environment: " + env);
        }

        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }

        return prop;
    }
}
