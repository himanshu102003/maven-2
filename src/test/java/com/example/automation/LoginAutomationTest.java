<<<<<<< HEAD
package com.example.automation;
 

=======
package com.example.automation; 
>>>>>>> 1375472b003a14ff065aff9fdb2e66a8ef2989ef
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver_win32/chromedriver.exe");
<<<<<<< HEAD
=======

>>>>>>> 1375472b003a14ff065aff9fdb2e66a8ef2989ef
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://example.com/login");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Perform login
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            // Validate successful login
            String expectedTitle = "Dashboard";
            String actualTitle = driver.getTitle();

            assertEquals(expectedTitle, actualTitle);

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
