package com.cogent.assignment.duplicate_files_finder;

import com.cogent.assignment.duplicate_files_finder.parser.DirectoryParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by lovikasaxena
 */
class AppStarterTest {

    private DirectoryParser directoryParser = mock(DirectoryParser.class);
    private AppStarter appStarter = new AppStarter(directoryParser);

    @Test
    void should_start_finding_duplicate_files_using_directory_parser() throws IOException, NoSuchAlgorithmException {
        String path = "/homeDir";

        appStarter.findDuplicates(path);

        verify(directoryParser, times(1)).parseFiles(path, new HashMap());
    }

    @Test
    void should_filter_result_entries_having_more_than_one_file_paths() throws IOException, NoSuchAlgorithmException {
        String path = "/homeDir";
        HashMap<String, List<String>> result = new HashMap<>();
        result.put("1", Arrays.asList("path1", "path2"));
        result.put("2", Arrays.asList("path3", "path4"));
        result.put("3", Arrays.asList("path5"));

        List<List<String>> expectedDuplicateFilePaths = Arrays.asList(
            Arrays.asList("path1", "path2"),
            Arrays.asList("path3", "path4")
        );

        when(directoryParser.parseFiles(anyString(), any())).thenReturn(result);

        List<List<String>> duplicateFiles = appStarter.findDuplicates(path);

        assertEquals(expectedDuplicateFilePaths, duplicateFiles);
    }
}