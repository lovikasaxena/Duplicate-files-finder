package com.cogent.assignment.duplicate_images_finder;

import com.cogent.assignment.duplicate_images_finder.exceptions.SearchDirectoryPathRequiredException;
import com.cogent.assignment.duplicate_images_finder.hash.SHAGenerator;
import com.cogent.assignment.duplicate_images_finder.parser.DirectoryParser;
import com.cogent.assignment.duplicate_images_finder.parser.ImageParser;
import com.cogent.assignment.duplicate_images_finder.starter.AppStarter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppStarterIntegrationTest {

    private ImageParser imageParser = new ImageParser(new SHAGenerator());
    private DirectoryParser directoryParser = new DirectoryParser(imageParser);
    private AppStarter appStarter = new AppStarter(directoryParser);

    @Test
    void should_return_duplicate_images_for_given_initial_path() throws IOException, NoSuchAlgorithmException, SearchDirectoryPathRequiredException {
        String path = this.getClass().getResource("/homeDir").getPath();
        List expectedDuplicates = Arrays.asList(
            Arrays.asList(
                path + "/trip to the sea/s-08712 (turtle).jpg",
                path + "/sea, sand, surf/turtle omg.jpg",
                path + "/sea, sand, surf/s-01324.jpg"
            ),
            Arrays.asList(
                path + "/trip to the sea/s-08913.jpg",
                path + "/sea, sand, surf/matin.jpg"
            ),
            Arrays.asList(
                path + "/random cats I saw/mew.jpg",
                path + "/mew.jpg"
            )
        );

        List<List<String>> result = appStarter.findDuplicates(path);

        assertEquals(expectedDuplicates, result);
    }
}