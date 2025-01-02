package com.example.automation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

public class LoginAutomationTest {

   @Test
   public void testLogin() {
      // Invoke the main method of App class to ensure it's covered
      App.main(new String[]{});  // This will execute App.java's main method

      // Set the path for ChromeDriver
      System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe");

      // Setup Chrome Options for Headless mode (for CI environments)
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--headless");  // Uncomment for headless mode (runs without UI)
      options.addArguments("--disable-gpu");  // Disable GPU usage in headless mode
      options.addArguments("--window-size=1920x1080");  // Set a window size for headless mode

      WebDriver driver = new ChromeDriver(options);

      try {
         // Navigate to the login page
         driver.get("https://the-internet.herokuapp.com/login");

         // Create WebDriverWait instance to wait for elements
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time

         // Take a screenshot for debugging before login
         takeScreenshot(driver, "before_login_page");

         // Wait until the username field is visible
         WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
         WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
         
         // Wait for the login button to be clickable
         WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.radius[type='submit']")));

         // Enter login credentials
         usernameField.sendKeys("tomsmith");
         passwordField.sendKeys("SuperSecretPassword!");

         // Click the login button
         loginButton.click();

         // Wait for the page to load and verify the logout button is visible
         WebElement logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.button.secondary.radius")));

         // Verify that the logout button is displayed after login
         Assertions.assertTrue(logoutButton.isDisplayed(), "Logout button should be visible after login");

         // Take a screenshot after login attempt
         takeScreenshot(driver, "after_login_page");

      } catch (Exception e) {
         // Capture screenshot in case of any error
         takeScreenshot(driver, "error_screenshot");
         System.out.println("Error occurred: " + e.getMessage());
         e.printStackTrace();  // Print stack trace for debugging
         throw e; // Rethrow the exception to fail the test
      } finally {
         // Always close the browser after the test
         driver.quit();
      }
   }

   // Helper method to take a screenshot
   private void takeScreenshot(WebDriver driver, String filename) {
      try {
         File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         Files.copy(screenshot.toPath(), Paths.get(filename + ".png"));
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Failed to capture screenshot: " + e.getMessage());
      }
   }
}
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
