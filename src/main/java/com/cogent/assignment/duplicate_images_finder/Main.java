package com.cogent.assignment.duplicate_images_finder;

import com.cogent.assignment.duplicate_images_finder.exceptions.SearchDirectoryPathRequiredException;
import com.cogent.assignment.duplicate_images_finder.hash.SHAGenerator;
import com.cogent.assignment.duplicate_images_finder.parser.DirectoryParser;
import com.cogent.assignment.duplicate_images_finder.parser.ImageParser;
import com.cogent.assignment.duplicate_images_finder.starter.AppStarter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lovikasaxena
 */
public class Main {

    private static Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) throws SearchDirectoryPathRequiredException, NoSuchAlgorithmException, IOException {
        ImageParser imageParser = new ImageParser(new SHAGenerator());
        DirectoryParser directoryParser = new DirectoryParser(imageParser);
        AppStarter appStarter = new AppStarter(directoryParser);

        if(args == null || args.length == 0 || args[0].isBlank()) throw new SearchDirectoryPathRequiredException();

        List<List<String>> duplicates = appStarter.findDuplicates(args[0]);
        if(duplicates.isEmpty()) {
            logger.info("******* NO DUPLICATE IMAGES FOUND *******");
            return;
        }

        logger.info("***** FOUND DUPLICATE IMAGES ***** \n");
        for(int i = 0; i < duplicates.size(); i++) {
            logger.info("Duplicates images set "+ (i+1) +": " + duplicates.get(i) + "\n");
        }
    }
}
