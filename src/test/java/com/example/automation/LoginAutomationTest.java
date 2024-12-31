package com.example.automation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

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

         // Verify that the title of the page is as expected after login
         String expectedTitle = "Secure Area";
         String actualTitle = driver.getTitle();
         Assertions.assertEquals(expectedTitle, actualTitle);

      } finally {
         // Close the browser after the test
         driver.quit();
      }
   }
}
