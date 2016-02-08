package com.placemi.core.manager.impl;

import com.placemi.core.dao.impl.ImageDAOImpl;
import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.manager.ImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
@Component
public class ImageManagerImpl implements ImageManager {
    @Autowired
    private ImageDAOImpl imageDAO;

    /**
     * Gets a random image
     *
     * @return The image
     * @throws ImageNotFoundException
     */
    @Override
    public byte[] getImage(int width, int height) throws ImageNotFoundException, IOException {
        return imageDAO.getImage(width, height).getImageByteArray();
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
        return imageDAO.getImage(id);
    }
}
