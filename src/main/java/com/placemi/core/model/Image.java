package com.placemi.core.model;

import com.placemi.commons.ImagePathHelper;
import com.placemi.core.exceptions.ImageNotFoundException;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-07.
 */
public class Image {
    private String id = "";

    /**
     * Gets the ID
     *
     * @return The ID or unique identifier
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID
     *
     * @param id The ID or unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the byte array image representation
     *
     * @return The byte[] image
     */
    public byte[] getImageByteArray() {
        BufferedImage initial = null;
        try {
            initial = ImageIO.read(new FileInputStream(ImagePathHelper.getRandomImagePath(this.getId())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }

        assert initial != null;
        int width = initial.getWidth();
        int height = initial.getHeight();
        Scalr.Mode mode = width >= height ? Scalr.Mode.FIT_TO_HEIGHT : Scalr.Mode.FIT_TO_WIDTH;
        BufferedImage img = Scalr.resize(initial, mode, 1000, 1000);
        width = img.getWidth();
        height = img.getHeight();
        img = Scalr.crop(img, Math.max(width / 2 - 500, 0), Math.max(height / 2 - 500, 0), 1000, 1000);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        try {
            ImageIO.write(img, "jpg", bao);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bao.toByteArray();
    }
}
