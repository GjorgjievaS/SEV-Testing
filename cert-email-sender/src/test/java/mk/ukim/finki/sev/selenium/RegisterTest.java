package mk.ukim.finki.sev.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = getDriver();
    }

    @Test
    public void shouldOpen() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        assertTrue(registerPage.isLoaded());
    }

    @Test
    public void shouldRegister() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register("test", "test", "test", "test@test.com");
    }


    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/user1/Desktop/SEV-Testing/cert-email-sender/src/main/resources/driver/chromedriver");
        return new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
