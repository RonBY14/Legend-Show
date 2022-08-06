package com.legends.ui.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage load(String name) {
        try {
            return ImageIO.read(ResourceRepository.class.getClassLoader().getResource(name));
            //return ImageIO.read(new File("src/main/resources/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
