package com.example.automation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class App {

    @Test
    public void testLogin() {
        App app = new App();

        // Test valid credentials
        Assertions.assertTrue(app.login("tomsmith", "SuperSecretPassword!"), "Login should succeed for valid credentials");

        // Test invalid credentials
        Assertions.assertFalse(app.login("wronguser", "wrongpassword"), "Login should fail for invalid credentials");
    }
}
