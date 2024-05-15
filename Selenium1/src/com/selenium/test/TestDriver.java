package com.selenium.test;

import org.openqa.selenium.chrome.ChromeDriver;

public class TestDriver {
    public TestDriver(){}
    public ChromeDriver getDriver()
    {
        String driveUrl = "C:\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driveUrl);
        return new ChromeDriver();
    }
}
