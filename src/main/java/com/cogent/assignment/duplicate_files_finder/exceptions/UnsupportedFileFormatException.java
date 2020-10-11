package com.cogent.assignment.duplicate_files_finder.exceptions;

/**
 * Created by lovikasaxena
 */
public class UnsupportedFileFormatException extends Exception {

    public String format;

    public UnsupportedFileFormatException(String format) {
        super(String.format("File format %s not supported", format));
        this.format = format;
    }
}
