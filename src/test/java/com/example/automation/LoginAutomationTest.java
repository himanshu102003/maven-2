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
    public void testLoginWithValidCredentials() {
        App.main(new String[]{}); // Simulate the app's main method execution

        WebDriver driver = setupDriver();
        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Take a screenshot for debugging before login
            takeScreenshot(driver, "before_login_page");

            // Locate username and password fields and the login button
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.radius[type='submit']")));

            // Enter valid credentials
            usernameField.sendKeys("tomsmith");
            passwordField.sendKeys("SuperSecretPassword!");

            // Click login button
            loginButton.click();

            // Wait for the page to load and verify logout button visibility
            WebElement logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.button.secondary.radius")));
            Assertions.assertTrue(logoutButton.isDisplayed(), "Logout button should be visible after login");

            // Take a screenshot after login attempt
            takeScreenshot(driver, "after_login_page");

        } catch (Exception e) {
            takeScreenshot(driver, "error_screenshot");
            e.printStackTrace();
            Assertions.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        WebDriver driver = setupDriver();
        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Take a screenshot for debugging before login
            takeScreenshot(driver, "before_invalid_login_page");

            // Locate username and password fields and the login button
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.radius[type='submit']")));

            // Enter invalid credentials
            usernameField.sendKeys("invalidUser");
            passwordField.sendKeys("wrongPassword");

            // Click login button
            loginButton.click();

            // Wait for error message to be displayed
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#flash")));
            Assertions.assertTrue(errorMessage.isDisplayed(), "Error message should be visible with invalid login");

            // Take a screenshot after login attempt
            takeScreenshot(driver, "after_invalid_login_page");

        } catch (Exception e) {
            takeScreenshot(driver, "error_invalid_login_screenshot");
            e.printStackTrace();
            Assertions.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
public void testPageLoadErrorHandling() {
    // Set the path for ChromeDriver
    System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");

    // Setup Chrome Options for Headless mode (for CI environments)
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1920x1080");

    WebDriver driver = new ChromeDriver(options);

    try {
        // Navigate to a non-existent page to trigger 404 error
        driver.get("https://the-internet.herokuapp.com/nonexistent_page");

        // Create WebDriverWait instance to wait for elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for the error message to be visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Not Found')]")));

        // Verify that the error message is displayed
        Assertions.assertTrue(errorMessage.isDisplayed(), "404 error page should be displayed");

    } catch (Exception e) {
        e.printStackTrace();
        Assertions.fail("Error occurred: " + e.getMessage());
    } finally {
        driver.quit();
    }
}


    @Test
    public void testLoginAndLogout() {
        WebDriver driver = setupDriver();
        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Locate username and password fields and the login button
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.radius[type='submit']")));

            // Enter valid credentials
            usernameField.sendKeys("tomsmith");
            passwordField.sendKeys("SuperSecretPassword!");

            // Click login button
            loginButton.click();

            // Wait for the page to load and verify logout button visibility
            WebElement logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.button.secondary.radius")));
            Assertions.assertTrue(logoutButton.isDisplayed(), "Logout button should be visible after login");

            // Logout
            logoutButton.click();

            // Verify successful logout
            WebElement loginButtonAfterLogout = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.radius[type='submit']")));
            Assertions.assertTrue(loginButtonAfterLogout.isDisplayed(), "Login button should be visible after logout");

        } catch (Exception e) {
            takeScreenshot(driver, "error_logout_screenshot");
            e.printStackTrace();
            Assertions.fail("Test failed during login/logout: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Helper method to set up ChromeDriver with appropriate options
    private WebDriver setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/himan/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode for CI environments
        options.addArguments("--disable-gpu"); // Disable GPU usage in headless mode
        options.addArguments("--window-size=1920x1080"); // Set a window size for headless mode
        return new ChromeDriver(options);
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
