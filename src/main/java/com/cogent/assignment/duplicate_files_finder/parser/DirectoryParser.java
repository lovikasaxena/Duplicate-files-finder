package com.cogent.assignment.duplicate_files_finder.parser;

import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectoryParser {

    private ImageParser imageParser;


    public DirectoryParser(ImageParser imageParser) {
        this.imageParser = imageParser;
    }

    public HashMap<String, List<String>> parseFiles(String directoryPath, HashMap<String, List<String>> imageHashToLocationsMap) throws IOException, NoSuchAlgorithmException {
        File directory = new File(directoryPath);
        File[] directoryFiles = directory.listFiles();

        for(File innerFile: directoryFiles) {
            if(innerFile.isDirectory()) {
                imageHashToLocationsMap = parseFiles(innerFile.getPath(), imageHashToLocationsMap);
                continue;
            }

            String shaForImage = imageParser.createSHAForImage(innerFile.getPath());
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

        return imageHashToLocationsMap;
    }

}
