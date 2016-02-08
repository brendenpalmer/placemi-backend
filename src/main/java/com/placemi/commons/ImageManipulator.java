package com.placemi.commons;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-07.
 */
public class ImageManipulator {
    public static byte[] resize(String path, int width, int height, int quality, boolean grayscale) {
        BufferedImage initial = null;
        Scalr.Mode mode;

        try {
            initial = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert initial != null;

        final double widthRatio = (double) width / (double) initial.getWidth();
        final double heightRatio = (double) height / (double) initial.getHeight();

        final double ratio = Math.max(widthRatio, heightRatio);

        int finalWidth = (int) Math.ceil(((double) initial.getWidth() * ratio));
        int finalHeight = (int) Math.ceil(((double) initial.getHeight() * ratio));

        if (width >= height) {
            mode = Scalr.Mode.FIT_TO_HEIGHT;
        } else {
            mode = Scalr.Mode.FIT_TO_WIDTH;
        }

        BufferedImage img;

        if (grayscale) {
            img = Scalr.resize(initial, Scalr.Method.ULTRA_QUALITY, mode, finalWidth, finalHeight, Scalr.OP_GRAYSCALE);
        } else {
            img = Scalr.resize(initial, Scalr.Method.ULTRA_QUALITY, mode, finalWidth, finalHeight);
        }

        img = Scalr.crop(img,
                Math.max(((img.getWidth() - width) / 2), 0),
                Math.max(((img.getHeight() - height) / 2), 0),
                width,
                height);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        try {
            ImageIO.write(img, "jpg", bao);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initial.flush();
        img.flush();

        byte[] imageByteArray = bao.toByteArray();

        try {
            bao.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageByteArray;
    }
}
