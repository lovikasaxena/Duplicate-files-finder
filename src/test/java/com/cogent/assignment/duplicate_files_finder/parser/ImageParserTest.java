package com.cogent.assignment.duplicate_files_finder.parser;

import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lovikasaxena
 */
class ImageParserTest {

    private SHAGenerator shaGenerator = mock(SHAGenerator.class);
    private ImageParser imageParser = new ImageParser(shaGenerator);


    @Test
    void should_parse_all_pixels_of_image_and_create_hash() throws IOException, NoSuchAlgorithmException {
        String imagePath = this.getClass().getResource("/homeDir/tue.jpg").getPath();

        when(shaGenerator.createSHAFor(anyString())).thenReturn("random-sha");

        String imageSHA = imageParser.createSHAForImage(imagePath);

        assertEquals("random-sha", imageSHA);
    }
}