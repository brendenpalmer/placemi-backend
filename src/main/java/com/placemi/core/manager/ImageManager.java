package com.placemi.core.manager;

import com.placemi.core.exceptions.ImageNotFoundException;

import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public interface ImageManager {
    byte[] getImage(int width, int height) throws ImageNotFoundException, IOException;

    String getImage(String id) throws ImageNotFoundException;
}
