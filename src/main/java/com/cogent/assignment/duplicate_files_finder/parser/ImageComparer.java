package com.cogent.assignment.duplicate_files_finder.parser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ImageComparer {

    private ImageReader imageReader = new ImageReader();

    public boolean compare(String path1, String path2) throws IOException, NoSuchAlgorithmException {
        BufferedImage image1 = imageReader.read(path1);
        BufferedImage image2 = imageReader.read(path2);

        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight())
            return false;

        return comparePixels(image1, image2);
    }

    private boolean comparePixels(BufferedImage image1, BufferedImage image2) {
        for (int currentHeight = 0; currentHeight < image1.getHeight(); currentHeight++) {
            for (int currentWidth = 0; currentWidth < image1.getWidth(); currentWidth++) {

                int imageOnePixel = image1.getRGB(currentWidth, currentHeight);
                Color color1 = new Color(imageOnePixel, true);
                int red1 = color1.getRed();
                int green1 = color1.getGreen();
                int blue1 = color1.getBlue();

                int imageTwoPixel = image2.getRGB(currentWidth, currentHeight);
                Color color2 = new Color(imageTwoPixel, true);
                int red2 = color2.getRed();
                int green2 = color2.getGreen();
                int blue2 = color2.getBlue();

                long currentDifference = Math.abs(red1 - red2) + Math.abs(green1 - green2) + Math.abs(blue1 - blue2);
                if(currentDifference > 0) return false;
            }
        }
        return true;
    }

}