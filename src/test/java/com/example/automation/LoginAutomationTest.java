
package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login"); // Replace with actual URL

            // Locate the username and password fields and login button
         WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
         WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
         
         // Wait for the login button to be clickable
         WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.radius[type='submit']")));


            // Perform login
            usernameField.sendKeys("tomsmith");
            passwordField.sendKeys("SuperSecretPassword!");
            loginButton.click();

            // Validate successful login
            WebElement successMessage = driver.findElement(By.className("post-title"));
            String expectedMessage = "Logged In Successfully";
            assertTrue(successMessage.getText().contains(expectedMessage), "Login was not successful.");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
