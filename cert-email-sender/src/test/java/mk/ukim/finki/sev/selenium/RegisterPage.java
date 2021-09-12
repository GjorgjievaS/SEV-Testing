package mk.ukim.finki.sev.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost:8443/register");
    }

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(10000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form/div/label[1]/b"))).isDisplayed();

    }

    public void register(String code, String name, String lastName, String email) throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div/form/div/input[1]")).clear();
        driver.findElement(By.xpath("/html/body/div/form/div/input[2]")).clear();
        driver.findElement(By.xpath("/html/body/div/form/div/input[3]")).clear();
        driver.findElement(By.xpath("/html/body/div/form/div/input[4]")).clear();

        driver.findElement(By.xpath("/html/body/div/form/div/input[1]")).sendKeys(code);
        driver.findElement(By.xpath("/html/body/div/form/div/input[2]")).sendKeys(name);
        driver.findElement(By.xpath("/html/body/div/form/div/input[3]")).sendKeys(lastName);
        driver.findElement(By.xpath("/html/body/div/form/div/input[4]")).sendKeys(email);

//       // driver.findElement(By.id("user-name")).sendKeys(user);
//        Thread.sleep(5000);
//        driver.findElement(By.id("password")).sendKeys("test");
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("[value=\"LOGIN\"]")).click();
//        Thread.sleep(5000);
    }

    public String getErrorMessage() {
        WebElement errorPage = driver.findElement(By.xpath("//h3[@data-test=\"error\"]"));
        return errorPage.getText();
    }
}
