package com.cogent.assignment.duplicate_files_finder.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class ImageComparerTest {

    private ImageComparer imageComparer = new ImageComparer();

    @Test
    void should_return_false_when_both_images_dimensions_dont_match() throws IOException, NoSuchAlgorithmException {
        String path1 = this.getClass().getResource("/homeDir/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/homeDir/barry.jpg").getPath();

        boolean result = imageComparer.compare(path1, path2);

        assertFalse(result);
    }

    @Test
    void should_return_false_when_images_are_same_but_have_color_difference() throws IOException, NoSuchAlgorithmException {
        String path1 = this.getClass().getResource("/homeDir/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/homeDir/tue_bnW.jpg").getPath();

        boolean result = imageComparer.compare(path1, path2);

        assertFalse(result);
    }

    @Test
    void should_return_true_when_both_images_are_same() throws IOException, NoSuchAlgorithmException {
        String path1 = this.getClass().getResource("/homeDir/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/homeDir/tue2.jpg").getPath();

        boolean result = imageComparer.compare(path1, path2);

        assertTrue(result);
    }
}