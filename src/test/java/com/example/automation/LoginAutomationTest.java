package com.example.automation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
      // Set the path for the ChromeDriver
      System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe");
      WebDriver driver = new ChromeDriver();

      try {
         // Navigate to the Herokuapp login page
         driver.get("https://the-internet.herokuapp.com/login");

         // Create WebDriverWait instance to wait for elements
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

         // Take a screenshot for debugging
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

         // Wait for the page title to change to "Secure Area"
         wait.until(ExpectedConditions.titleIs("Secure Area"));

         // Verify that the title of the page is as expected after login
         String expectedTitle = "Secure Area";
         String actualTitle = driver.getTitle();
         Assertions.assertEquals(expectedTitle, actualTitle);

      } catch (Exception e) {
         // Capture screenshot if there's an error
         takeScreenshot(driver, "error_screenshot");
         throw e;
      } finally {
         // Close the browser after the test
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
      }
   }
}
