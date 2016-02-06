package com.placemi.core.exceptions;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public class ImageNotFoundException extends Exception {
    public ImageNotFoundException() {
        super("Image not found.");
    }
}
