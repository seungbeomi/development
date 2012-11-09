package org.onehippo.gogreen;

import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class GoGreenTest {

    private static Selenium SELENIUM;
    private static FirefoxDriver DRIVER;

    protected Selenium selenium;

    @BeforeSuite
    @Parameters("driver.url")
    public void startSelenium(@Optional("http://localhost:8080") String url) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("extensions.checkCompatibility.5.0", false);
        DRIVER = new FirefoxDriver(profile);

        SELENIUM = new WebDriverBackedSelenium(DRIVER, url);
    }

    @BeforeClass
    public void retrieveSelenium() {
        selenium = SELENIUM;
    }

    @AfterSuite
    public void stopSelenium() {
        DRIVER.quit();
    }

}
