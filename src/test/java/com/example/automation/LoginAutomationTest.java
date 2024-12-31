// Source code is decompiled from a .class file using FernFlower decompiler.
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
      System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe");
      WebDriver driver = new ChromeDriver();

      try {
         driver.get("https://example.com/login");
         WebElement usernameField = driver.findElement(By.id("username"));
         WebElement passwordField = driver.findElement(By.id("password"));
         WebElement loginButton = driver.findElement(By.cssSelector("button.radius[type='submit']"));
         usernameField.sendKeys("tomsmith");
         passwordField.sendKeys("SuperSecretPassword!");
         loginButton.click();
         String expectedTitle = "Dashboard";
         String actualTitle = driver.getTitle();
         Assertions.assertEquals(expectedTitle, actualTitle);
      } finally {
         driver.quit();
      }

   }
}
