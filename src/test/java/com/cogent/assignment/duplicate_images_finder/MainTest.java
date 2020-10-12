package com.cogent.assignment.duplicate_images_finder;

import com.cogent.assignment.duplicate_images_finder.exceptions.SearchDirectoryPathRequiredException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by lovikasaxena
 */
class MainTest {

    @Test
    void should_throw_search_directory_path_required_exception_when_path_is_null() {
        assertThrows(
            SearchDirectoryPathRequiredException.class,
            () -> Main.main(null),
            "Application requires a directory path to start searching duplicate files in"
        );
    }

    @Test
    void should_throw_search_directory_path_required_exception_when_path_is_blank() {
        String[] args = new String[1];
        args[0] = "    ";
        assertThrows(
            SearchDirectoryPathRequiredException.class,
            () -> Main.main(args),
            "Application requires a directory path to start searching duplicate files in"
        );
    }
}