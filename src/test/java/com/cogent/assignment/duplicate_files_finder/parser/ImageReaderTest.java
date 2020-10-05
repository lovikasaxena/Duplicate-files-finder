package com.cogent.assignment.duplicate_files_finder.parser;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageReaderTest {

    private ImageReader imageReader = new ImageReader();

    @Test
    void should_read_given_file_content() throws IOException, NoSuchAlgorithmException {
        String path = this.getClass().getResource("/homeDir/tue.jpg").getPath();

        BufferedImage image = imageReader.read(path);

        assertEquals(250, image.getWidth());
        assertEquals(165, image.getHeight());
    }
}