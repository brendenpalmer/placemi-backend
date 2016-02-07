package com.placemi.core.manager.impl;

import com.placemi.core.dao.impl.ImageDAOImpl;
import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.manager.ImageManager;

import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public class ImageManagerImpl implements ImageManager {
    private ImageDAOImpl imageDAO = null;

    /**
     * Gets a random image
     *
     * @return The image
     * @throws ImageNotFoundException
     */
    @Override
    public byte[] getImage() throws ImageNotFoundException, IOException {
        if (this.imageDAO == null) {
            this.imageDAO = new ImageDAOImpl();
        }

        return this.imageDAO.getImage();
    }

    /**
     * Gets an image based off of its ID
     *
     * @param id The ID of the image to retrieve
     * @return The image
     * @throws ImageNotFoundException
     */
    @Override
    public String getImage(String id) throws ImageNotFoundException {
        if (this.imageDAO == null) {
            this.imageDAO = new ImageDAOImpl();
        }

        return this.imageDAO.getImage(id);
    }
}
