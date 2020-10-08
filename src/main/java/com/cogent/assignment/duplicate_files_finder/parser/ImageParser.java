package com.cogent.assignment.duplicate_files_finder.parser;


import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ImageParser {

    private SHAGenerator shaGenerator;

    public ImageParser(SHAGenerator shaGenerator) {
        this.shaGenerator = shaGenerator;
    }

    String createSHAForImage(String path) throws IOException, NoSuchAlgorithmException {
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

    private BufferedImage read(String path) throws IOException {
        File file = new File(path);
        return ImageIO.read(file);
    }
}


