package com.cogent.assignment.duplicate_files_finder.exceptions;

/**
 * Created by lovikasaxena
 */
public class SearchDirectoryPathRequiredException extends Exception{
    public SearchDirectoryPathRequiredException() {
        super("Application requires a directory path to start searching duplicate files");
    }
}
