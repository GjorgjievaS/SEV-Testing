package mk.ukim.finki.sev.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost:8443/");
    }

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(10000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div[1]/p/strong"))).isDisplayed();

    }

    public void login(String user, String password) throws InterruptedException {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(user);
        Thread.sleep(5000);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[value=\"LOGIN\"]")).click();
        Thread.sleep(5000);
    }

    public String getErrorMessage() {
        WebElement errorPage = driver.findElement(By.xpath("//h3[@data-test=\"error\"]"));
        return errorPage.getText();
    }

}
