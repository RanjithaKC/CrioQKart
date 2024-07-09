package QKART_SANITY_LOGIN.Module1;

import java.beans.Visibility;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/login";

    public Login(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogin(String Username, String Password) throws InterruptedException {
        // Find the Username Text Box
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement username_txt_box = this.driver.findElement(By.id("username"));
        wait.until(ExpectedConditions.visibilityOf(username_txt_box));
        //username_txt_box.click();
        // Enter the username
        username_txt_box.sendKeys(Username);
        // Wait for user name to be entered
        wait.until(ExpectedConditions.textToBePresentInElementValue(username_txt_box, Username));
        // Find the password Text Box
        WebElement password_txt_box = this.driver.findElement(By.id("password"));
        wait.until(ExpectedConditions.visibilityOf(password_txt_box));

        // Enter the password
        password_txt_box.sendKeys(Password);

        // Find the Login Button
        WebElement login_button = driver.findElement(By.className("button"));
        wait.until(ExpectedConditions.visibilityOf(login_button));

        // Click the login Button
        login_button.click();

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(30));
        fluentWait.pollingEvery(Duration.ofMillis(250));
        fluentWait.until(ExpectedConditions.invisibilityOf(login_button));
        // Wait for Login action to complete
        //Thread.sleep(5000);
        wait.until(ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/"));
        return this.VerifyUserLoggedIn(Username);
    }

    public Boolean VerifyUserLoggedIn(String Username) {
        try {
            // Find the username label (present on the top right of the page)
            WebElement username_label;
             username_label = this.driver.findElement(By.className("username-text"));
            System.out.println("username text on home page is" +username_label.getText().equals(Username));
            return username_label.getText().equals(Username);
        } catch (Exception e) {
            System.out.println("exception is : "+ e);
           // e.printStackTrace();
            return false;
        }

    }

}
