package com.epam.padawans;

import com.epam.padawans.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected final static String LOGIN = "linked.test11@gmail.com";
    protected final static String PASSWORD = "epaM@2018";
    private final static String BASE_URL = "http://10.6.221.51/";
    private final static Logger log = LogManager.getLogger();

    @BeforeMethod
    public void setUp() {
        log.debug("Create driver");
        DriverManager.getDriver().get(BASE_URL);
        log.info("Open URL: " + BASE_URL);
    }

    @AfterMethod
    public void closeDriver() {
        DriverManager.closeDriver();
        log.debug("Close driver");
    }
}