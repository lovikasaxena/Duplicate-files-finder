package com.cogent.assignment.duplicate_files_finder.parser;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DirectoryParserTest {

    private ImageParser imageParser = mock(ImageParser.class);
    private DirectoryParser directoryParser = new DirectoryParser(imageParser);

    @Test
    void should_parse_all_images_in_directory_and_add_new_images_to_parsed_images_map() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        URI directoryPath = this.getClass().getResource("/testDirectory").toURI();
        String path1 = this.getClass().getResource("/testDirectory/camping/tent.jpg").toURI().getPath();
        String path2 = this.getClass().getResource("/testDirectory/camping/summit at dawn.jpg").toURI().getPath();
        String path3 = this.getClass().getResource("/testDirectory/castle_from_drone.jpg").toURI().getPath();

        when(imageParser.createSHAForImage(path1)).thenReturn("1234");
        when(imageParser.createSHAForImage(path2)).thenReturn("5678");
        when(imageParser.createSHAForImage(path3)).thenReturn("1234");

        HashMap<String, List<String>> parsedImageHashes = directoryParser.parseFiles(directoryPath.getPath(), new HashMap());

        assertEquals(parsedImageHashes.get("5678"), List.of(path2));
    }

    @Test
    void should_parse_images_in_subdirectories_of_given_directory() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        URI directoryPath = this.getClass().getResource("/testDirectory").toURI();
        String image1 = this.getClass().getResource("/testDirectory/castle_from_drone.jpg").toURI().getPath();
        String subDirImage1 = this.getClass().getResource("/testDirectory/camping/tent.jpg").toURI().getPath();
        String subDirImage2 = this.getClass().getResource("/testDirectory/camping/summit at dawn.jpg").toURI().getPath();

        when(imageParser.createSHAForImage(subDirImage1)).thenReturn("1234");
        when(imageParser.createSHAForImage(subDirImage2)).thenReturn("5678");
        when(imageParser.createSHAForImage(image1)).thenReturn("1234");

        HashMap<String, List<String>> parsedImageHashes = directoryParser.parseFiles(directoryPath.getPath(), new HashMap());

        assertTrue(parsedImageHashes.get("1234").contains(subDirImage1));
        assertEquals(parsedImageHashes.get("5678"), List.of(subDirImage2));
    }

    @Test
    void should_add_duplicate_file_paths_for_same_SHA() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        URI directoryPath = this.getClass().getResource("/testDirectory").toURI();
        String image1 = this.getClass().getResource("/testDirectory/castle_from_drone.jpg").toURI().getPath();
        String subDirImage1 = this.getClass().getResource("/testDirectory/camping/tent.jpg").toURI().getPath();
        String subDirImage2 = this.getClass().getResource("/testDirectory/camping/summit at dawn.jpg").toURI().getPath();

        when(imageParser.createSHAForImage(subDirImage1)).thenReturn("1234");
        when(imageParser.createSHAForImage(subDirImage2)).thenReturn("5678");
        when(imageParser.createSHAForImage(image1)).thenReturn("1234");

        HashMap<String, List<String>> parsedImageHashes = directoryParser.parseFiles(directoryPath.getPath(), new HashMap());

        assertTrue(parsedImageHashes.get("1234").contains(subDirImage1));
        assertTrue(parsedImageHashes.get("1234").contains(image1));
    }
}