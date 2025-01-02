package com.example.automation;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AppTest {

    @Test
    public void testMain() {
        // Mocking the logger to ensure it was called
        Logger logger = mock(Logger.class);
        App.logger = logger;

        // Running the main method (this will call the logger)
        App.main(new String[]{});

        // Verifying that the logger.info method was called
        verify(logger).info("Welcome to the Automation Testing Project!");
    }
}
