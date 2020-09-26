package com.cogent.assignment.duplicate_files_finder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppStarterTest {
    private AppStarter appStarter = new AppStarter();

    @Test
    void should_return_given_input() {
        String[] input = new String[1];
        input[0] = "Hello World";

        String result = appStarter.main(input);

        Assertions.assertEquals("Hello World", result);
    }
}