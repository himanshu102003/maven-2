package com.example.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration; // Import Duration class
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Use WebDriverManager to set up ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("http://the-internet.herokuapp.com/login");

            // Wait for the username and password fields to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration.ofSeconds
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))); // Changed to a more general CSS selector

            // Perform login
            usernameField.sendKeys("tomsmith"); // Use the correct test credentials
            passwordField.sendKeys("SuperSecretPassword!"); // Use the correct test credentials
            loginButton.click();

            // Wait for the page to load and validate the login
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']"))); // Check if the logout button is visible after login

            // Validate successful login by checking if the logout button is displayed
            assertEquals("Logout", logoutButton.getText());

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
