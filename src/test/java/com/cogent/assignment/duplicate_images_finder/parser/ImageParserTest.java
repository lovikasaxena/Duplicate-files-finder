package com.cogent.assignment.duplicate_images_finder.parser;

import com.cogent.assignment.duplicate_images_finder.exceptions.UnsupportedFileFormatException;
import com.cogent.assignment.duplicate_images_finder.hash.SHAGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    private ImageParser imageParser = new ImageParser(new SHAGenerator());

    @Test
    void should_parse_all_pixels_of_given_image_and_create_hash() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String imagePath = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();

        SHAGenerator shaGenerator = mock(SHAGenerator.class);
        ImageParser imageParserWithMock = new ImageParser(shaGenerator);
        when(shaGenerator.createSHAFor(anyString())).thenReturn("random-sha");

        String imageSHA = imageParserWithMock.createSHAForImage(imagePath);

        assertEquals("random-sha", imageSHA);
    }

    @Test
    void should_return_different_hash_when_images_are_same_but_have_color_difference() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/duplicateImages/tue_bnW.jpg").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertFalse(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_different_hash_when_images_are_different() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/duplicateImages/barry.JPG").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertFalse(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_same_hash_for_given_same_jpg_images() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/duplicateImages/tue.jpg").getPath();
        String path2 = this.getClass().getResource("/duplicateImages/tue2.jpg").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertTrue(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_same_hash_for_given_same_png_images() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/image_formats/mario-image.png").getPath();
        String path2 = this.getClass().getResource("/image_formats/duplicates/mario-image.png").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertTrue(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_same_hash_for_given_same_gif_images() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/image_formats/teamFun.gif").getPath();
        String path2 = this.getClass().getResource("/image_formats/duplicates/teamFun.gif").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertTrue(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_same_hash_for_given_same_tiff_images() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/image_formats/file_example_TIFF_1MB.tiff").getPath();
        String path2 = this.getClass().getResource("/image_formats/duplicates/file_example_TIFF_1MB.tiff").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertTrue(imageSHA1.equals(imageSHA2));
    }

    @Test
    void should_return_same_hash_for_given_same_jpeg_images() throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {
        String path1 = this.getClass().getResource("/image_formats/avatar.jpeg").getPath();
        String path2 = this.getClass().getResource("/image_formats/duplicates/avatar.jpeg").getPath();

        String imageSHA1 = imageParser.createSHAForImage(path1);
        String imageSHA2 = imageParser.createSHAForImage(path2);

        assertTrue(imageSHA1.equals(imageSHA2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/image_formats/package-lock.json", "/image_formats/swiggy-order.pdf"})
    void should_throw_format_not_supported_if_file_is_not_an_image(String path) {
        String path1 = this.getClass().getResource(path).getPath();

        assertThrows(UnsupportedFileFormatException.class, () -> imageParser.createSHAForImage(path1));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "/image_formats/avatar.jpeg",
        "/image_formats/file_example_TIFF_1MB.tiff",
        "/image_formats/mario-image.png",
        "/image_formats/teamFun.gif"
    })
    void should_not_throw_format_not_supported_if_file_is_of_supported_format(String path) throws UnsupportedFileFormatException, NoSuchAlgorithmException, IOException {
        String path1 = this.getClass().getResource(path).getPath();

        imageParser.createSHAForImage(path1);
    }

}