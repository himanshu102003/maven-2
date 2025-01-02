package com.example.automation;

public class App {
    public static void main(String[] args) {
        System.out.println("Application is running...");
    }

    // Simulate a login method for testing
    public boolean login(String username, String password) {
        String validUsername = "tomsmith";
        String validPassword = "SuperSecretPassword!";
        return username.equals(validUsername) && password.equals(validPassword);
    }
}
