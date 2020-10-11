package com.cogent.assignment.duplicate_files_finder.parser;


import com.cogent.assignment.duplicate_files_finder.exceptions.UnsupportedFileFormatException;
import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ImageParser {

    private SHAGenerator shaGenerator;
    private Logger logger = Logger.getLogger("ImageParser");

    public static final List<String> SUPPORTED_FILE_FORMATS = Arrays.asList("jpg", "jpeg", "png", "gif", "tiff");

    public ImageParser(SHAGenerator shaGenerator) {
        this.shaGenerator = shaGenerator;
    }

    String createSHAForImage(String path) throws IOException, NoSuchAlgorithmException, UnsupportedFileFormatException {

        BufferedImage bufferedImage = read(path);

        StringBuffer imagePixels = new StringBuffer();
        for (int currentHeight = 0; currentHeight < bufferedImage.getHeight(); currentHeight++) {
            for (int currentWidth = 0; currentWidth < bufferedImage.getWidth(); currentWidth++) {

                int image1Pixel = bufferedImage.getRGB(currentWidth, currentHeight);
                imagePixels.append(image1Pixel);
            }
        }

        return shaGenerator.createSHAFor(imagePixels.toString());
    }

    private BufferedImage read(String path) throws IOException, UnsupportedFileFormatException {
        logger.info("Reading file: " + path);
        File file = new File(path);
        checkForUnsupportedFileFormat(file);

        return ImageIO.read(file);
    }

    private void checkForUnsupportedFileFormat(File file) throws UnsupportedFileFormatException {
        String fileName = file.getName();
        int formatStartIndex = fileName.lastIndexOf(".") + 1;

        if(formatStartIndex < 0 || !SUPPORTED_FILE_FORMATS.contains(fileName.substring(formatStartIndex).toLowerCase())) {
            throw new UnsupportedFileFormatException(fileName.substring(formatStartIndex));
        }
    }
}


