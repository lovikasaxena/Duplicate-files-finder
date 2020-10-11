package com.cogent.assignment.duplicate_files_finder.parser;

import com.cogent.assignment.duplicate_files_finder.exceptions.UnsupportedFileFormatException;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryParser {

    private ImageParser imageParser;
    private Logger logger = Logger.getLogger("DirectoryParser");

    public DirectoryParser(ImageParser imageParser) {
        this.imageParser = imageParser;
    }

    public HashMap<String, List<String>> parseFiles(String directoryPath, HashMap<String, List<String>> imageHashToLocationsMap) throws IOException, NoSuchAlgorithmException {
        logger.info("Parsing files in directory: " + directoryPath);
        File directory = new File(directoryPath);
        File[] directoryFiles = directory.listFiles();

        for(File innerFile: directoryFiles) {
            if(innerFile.isDirectory()) {
                imageHashToLocationsMap = parseFiles(innerFile.getPath(), imageHashToLocationsMap);
                continue;
            }

            String shaForImage = null;
            try {
                shaForImage = imageParser.createSHAForImage(innerFile.getPath());
            } catch (UnsupportedFileFormatException e) {
                logger.log(Level.WARNING, String.format("Ignoring file as format %s is unsupported: ", e.format));
                continue;
            }

            addShaToMap(imageHashToLocationsMap, innerFile, shaForImage);
        }

        return imageHashToLocationsMap;
    }

    private void addShaToMap(HashMap<String, List<String>> imageHashToLocationsMap, File innerFile, String shaForImage) {
        if(imageHashToLocationsMap.containsKey(shaForImage)) {
            List<String> duplicateLocations = imageHashToLocationsMap.get(shaForImage);
            duplicateLocations.add(innerFile.getPath());
            imageHashToLocationsMap.put(shaForImage, duplicateLocations);
        } else {
            List<String> locations = new ArrayList();
            locations.add(innerFile.getPath());
            imageHashToLocationsMap.put(shaForImage, locations);
        }
    }

}
