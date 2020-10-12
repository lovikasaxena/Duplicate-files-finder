package com.cogent.assignment.duplicate_images_finder.starter;


import com.cogent.assignment.duplicate_images_finder.parser.DirectoryParser;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AppStarter {

    private DirectoryParser directoryParser;

    public AppStarter(DirectoryParser directoryParser) {
        this.directoryParser = directoryParser;
    }

    public List<List<String>> findDuplicates(String path) throws IOException, NoSuchAlgorithmException {
        HashMap<String, List<String>> duplicateImagesMap = directoryParser.parseFiles(path, new HashMap());

        return duplicateImagesMap
            .entrySet().stream()
            .filter(entry -> entry.getValue().size() > 1)
            .map(entry -> entry.getValue())
            .collect(Collectors.toList());
    }
}
