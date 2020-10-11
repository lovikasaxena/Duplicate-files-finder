package com.cogent.assignment.duplicate_files_finder.parser;

import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by lovikasaxena
 */
class ImageParserTest {

    @Test
    void should_parse_all_pixels_of_given_image_and_create_hash() throws IOException, NoSuchAlgorithmException {
        String imagePath = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();

        SHAGenerator shaGenerator = mock(SHAGenerator.class);
        ImageParser imageParserWithMock = new ImageParser(shaGenerator);
        when(shaGenerator.createSHAFor(anyString())).thenReturn("random-sha");

        String imageSHA = imageParserWithMock.createSHAForImage(imagePath);

        assertEquals("random-sha", imageSHA);
    }


    @Test
    void should_return_same_hash_when_both_images_are_same() throws IOException, NoSuchAlgorithmException {
        String path1 = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/duplicateImages/tue_bnW.jpg").getPath();

        ImageParser imageParser = new ImageParser(new SHAGenerator());
        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertFalse(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_different_hash_when_images_are_same_but_have_color_difference() throws IOException, NoSuchAlgorithmException {
        String path1 = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/duplicateImages/tue_bnW.jpg").getPath();

        ImageParser imageParser = new ImageParser(new SHAGenerator());
        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertFalse(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_different_hash_when_images_are_different() throws IOException, NoSuchAlgorithmException {
        String path1 = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/duplicateImages/barry.JPG").getPath();

        ImageParser imageParser = new ImageParser(new SHAGenerator());
        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertFalse(imageSHA1.equals(imageSHA2));
    }
}