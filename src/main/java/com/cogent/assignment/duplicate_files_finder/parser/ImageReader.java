package com.cogent.assignment.duplicate_files_finder.parser;

import com.cogent.assignment.duplicate_files_finder.hash.SHAGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ImageReader {

    public BufferedImage read(String path) throws IOException, NoSuchAlgorithmException {
        File file = new File(path);
        return ImageIO.read(file);
    }
}


