package com.placemi.core.model;

import com.placemi.commons.ImageDefaults;
import com.placemi.commons.ImageManipulator;
import com.placemi.commons.ImagePathHelper;
import com.placemi.core.exceptions.ImageNotFoundException;

import java.nio.file.Paths;

/**
 * @author Created by brendenpalmer on 2016-02-07.
 */
public class Image {
    private String id = "";
    private String baseImagePath = "";
    private String fileName = "";
    private boolean grayscale = false;
    private int width = ImageDefaults.DEFAULT_IMAGE_WIDTH;
    private int height = ImageDefaults.DEFAULT_IMAGE_HEIGHT;
    private int quality = ImageDefaults.DEFAULT_IMAGE_QUALITY;

    /**
     * The constructor for Image
     *
     * @throws ImageNotFoundException
     */
    public Image() throws ImageNotFoundException {
        this.baseImagePath = ImagePathHelper.getBaseImagePath();
        this.fileName = Paths.get(this.baseImagePath).getFileName().toString();
    }

    /**
     * The constructor for a user uploaded Image
     *
     * @param id       The ID or unique identifier
     * @param fileName The file name retrieved from the DAO
     * @throws ImageNotFoundException
     */
    public Image(String id, String fileName) throws ImageNotFoundException {
        this.baseImagePath = ImagePathHelper.getBaseImagePath(fileName);
        this.fileName = fileName;
    }

    /**
     * Gets the ID
     *
     * @return The ID or unique identifier
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the filename
     *
     * @return The filename
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the base image path
     *
     * @return The base image path
     */
    public String getBaseImagePath() {
        return this.baseImagePath;
    }

    /**
     * Gets whether or not the image should
     * be displayed in black and white
     *
     * @return Whether or not to display in
     * black and white
     */
    public boolean isGrayscale() {
        return this.grayscale;
    }

    /**
     * Gets the width
     *
     * @return The width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height
     *
     * @return The height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets the quality
     *
     * @return The quality
     */
    public int getQuality() {
        return this.quality;
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
     * Sets the filename
     *
     * @param fileName The filename
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets this.grayscale
     *
     * @param grayscale Whether or not to display in
     *                  black and white
     */
    public void setGrayscale(boolean grayscale) {
        this.grayscale = grayscale;
    }

    /**
     * Sets the width
     *
     * @param width The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height
     *
     * @param height The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the quality
     *
     * @param quality The quality
     */
    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * Gets the byte array image representation
     *
     * @return The image byte[]
     */
    public byte[] getImageByteArray() {
        return ImageManipulator.resize(this.getBaseImagePath(),
                this.getWidth(),
                this.getHeight(),
                this.getQuality(),
                this.isGrayscale());
    }
}
