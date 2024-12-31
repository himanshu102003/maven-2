package com.example.automation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginAutomationTest {
   public LoginAutomationTest() {
   }

   @Test
   public void testLogin() {
      // Set the path for the ChromeDriver
      System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe");
      WebDriver driver = new ChromeDriver();

      try {
         // Navigate to the Herokuapp login page
         driver.get("https://the-internet.herokuapp.com/login");

         // Locate the username, password fields, and the login button by their attributes
         WebElement usernameField = driver.findElement(By.id("username"));
         WebElement passwordField = driver.findElement(By.id("password"));

         // Update to locate the login button using its class name and type
         WebElement loginButton = driver.findElement(By.cssSelector("button.radius[type='submit']"));

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
