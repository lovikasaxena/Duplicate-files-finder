package com.cogent.assignment.duplicate_files_finder;

import com.cogent.assignment.duplicate_files_finder.exceptions.SearchDirectoryPathRequiredException;
import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;
import com.cogent.assignment.duplicate_files_finder.parser.DirectoryParser;
import com.cogent.assignment.duplicate_files_finder.parser.ImageParser;
import com.cogent.assignment.duplicate_files_finder.starter.AppStarter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lovikasaxena
 */
public class Main {

    public static void main(String[] args) throws SearchDirectoryPathRequiredException, NoSuchAlgorithmException, IOException {
        ImageParser imageParser = new ImageParser(new SHAGenerator());
        DirectoryParser directoryParser = new DirectoryParser(imageParser);
        AppStarter appStarter = new AppStarter(directoryParser);

        if(args == null || args.length == 0 || args[0].isBlank()) throw new SearchDirectoryPathRequiredException();

        appStarter.findDuplicates(args[0]);
    }
}
